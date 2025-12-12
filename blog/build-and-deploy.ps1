# Script para compilar e fazer deploy do Blog Service
# Uso: .\build-and-deploy.ps1

param(
    [Parameter(Mandatory=$false)]
    [switch]$SkipTests = $true,
    
    [Parameter(Mandatory=$false)]
    [switch]$Push = $false,
    
    [Parameter(Mandatory=$false)]
    [string]$Tag = "latest"
)

$ImageName = "betofrasson/blog-service:$Tag"

Write-Host "=== Blog Service - Build e Deploy ===" -ForegroundColor Cyan
Write-Host "Imagem: $ImageName" -ForegroundColor Yellow

# Step 1: Compilar aplicação
Write-Host "`n1. Compilando aplicação Spring Boot..." -ForegroundColor Green
if ($SkipTests) {
    .\mvnw clean package -DskipTests
} else {
    .\mvnw clean package
}

if ($LASTEXITCODE -ne 0) {
    Write-Host "Erro na compilação!" -ForegroundColor Red
    exit 1
}

# Step 2: Construir imagem Docker
Write-Host "`n2. Construindo imagem Docker..." -ForegroundColor Green
docker build -t $ImageName .

if ($LASTEXITCODE -ne 0) {
    Write-Host "Erro na construção da imagem Docker!" -ForegroundColor Red
    exit 1
}

Write-Host "Imagem $ImageName criada com sucesso!" -ForegroundColor Green

# Step 3: Push para registry (se solicitado)
if ($Push) {
    Write-Host "`n3. Fazendo push para Docker Hub..." -ForegroundColor Yellow
    docker push $ImageName
    
    if ($LASTEXITCODE -ne 0) {
        Write-Host "Erro no push da imagem!" -ForegroundColor Red
        exit 1
    }
    
    Write-Host "Push da imagem $ImageName concluído!" -ForegroundColor Green
}

# Step 4: Mostrar informações
Write-Host "`n=== Resumo ===" -ForegroundColor Cyan
Write-Host "✅ Aplicação compilada" -ForegroundColor Green
Write-Host "✅ Imagem Docker criada: $ImageName" -ForegroundColor Green

if ($Push) {
    Write-Host "✅ Push para Docker Hub concluído" -ForegroundColor Green
} else {
    Write-Host "ℹ️  Para fazer push: .\build-and-deploy.ps1 -Push" -ForegroundColor Yellow
}

Write-Host "`n=== Próximos passos ===" -ForegroundColor Cyan
Write-Host "Para testar localmente:" -ForegroundColor White
Write-Host "  .\manage-blog.ps1 start" -ForegroundColor Gray

Write-Host "`nPara fazer push:" -ForegroundColor White
Write-Host "  .\build-and-deploy.ps1 -Push" -ForegroundColor Gray

Write-Host "`nPara usar em Kubernetes:" -ForegroundColor White
Write-Host "  kubectl apply -f ../../infra/blog-service/" -ForegroundColor Gray
Write-Host "  kubectl set image deployment/blog-service blog-service=$ImageName" -ForegroundColor Gray