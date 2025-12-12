// Script de inicialização do MongoDB
// Criação do banco e usuário específico para a aplicação

db = db.getSiblingDB("blog_db");

// Criar usuário específico para a aplicação
db.createUser({
  user: "blog_user",
  pwd: "blog_password",
  roles: [
    {
      role: "readWrite",
      db: "blog_db",
    },
  ],
});

// Criar coleções iniciais se necessário
db.createCollection("posts");
db.createCollection("comments");

print("MongoDB inicializado com sucesso para o Blog Service");
