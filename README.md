# Biblioteca API - Spring Boot

## 📌 Sobre o Projeto
Este projeto consiste em uma API REST para gerenciar um sistema de biblioteca, permitindo operações CRUD (Create, Read, Update e Delete) em entidades como Livros, Autores e Usuários. A API foi desenvolvida utilizando **Spring Boot** e **JPA (Hibernate)** para a persistência dos dados.

## 🛠️ Tecnologias Utilizadas
- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **H2 Database** (para testes)
- **PostgreSQL/MySQL** (para produção)
- **Spring Web** (para construção da API REST)
- **Spring Boot DevTools** (para facilitar o desenvolvimento)

## 🏗️ Arquitetura e Relacionamentos das Entidades
O projeto possui as seguintes entidades principais:

### 📚 Livro (`Livro`)
- `id` (Long, chave primária)
- `titulo` (String)
- `isbn` (String, único)
- `autor` (Relacionamento ManyToOne com `Autor`)

### ✍️ Autor (`Autor`)
- `id` (Long, chave primária)
- `nome` (String)
- `nacionalidade` (String)
- `livros` (Relacionamento OneToMany com `Livro`)

### 👤 Usuário (`Usuario`)
- `id` (Long, chave primária)
- `nome` (String)
- `email` (String, único)
- `livrosEmprestados` (Relacionamento ManyToMany com `Livro`)

## 🔧 Configuração do Banco de Dados
O projeto está configurado para usar um banco de dados H2 em memória para testes, mas pode ser facilmente alterado para PostgreSQL ou MySQL.

Exemplo de configuração para **H2** (`application.properties`):
```properties
spring.datasource.url=jdbc:h2:mem:biblioteca
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

Exemplo de configuração para **MySQL**:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/biblioteca?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update

```

## 🚀 Endpoints da API
### 📌 Livro
- `GET /livros` → Lista todos os livros
- `GET /livros/{id}` → Busca um livro pelo ID
- `POST /livros` → Adiciona um novo livro
- `PUT /livros/{id}` → Atualiza um livro
- `DELETE /livros/{id}` → Remove um livro

### 📌 Autor
- `GET /autores` → Lista todos os autores
- `GET /autores/{id}` → Busca um autor pelo ID
- `POST /autores` → Adiciona um novo autor
- `PUT /autores/{id}` → Atualiza um autor
- `DELETE /autores/{id}` → Remove um autor

### 📌 Usuário
- `GET /usuarios` → Lista todos os usuários
- `GET /usuarios/{id}` → Busca um usuário pelo ID
- `POST /usuarios` → Adiciona um novo usuário
- `PUT /usuarios/{id}` → Atualiza um usuário
- `DELETE /usuarios/{id}` → Remove um usuário

## ▶️ Como Rodar o Projeto
### ✅ Pré-requisitos
- Ter o **Java 17** instalado
- Ter o **Maven** instalado

### 🚀 Passos para Rodar
1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/biblioteca-api.git
   ```
2. Entre na pasta do projeto:
   ```bash
   cd biblioteca-api
   ```
3. Execute o seguinte comando para rodar a aplicação:
   ```bash
   mvn spring-boot:run
   ```
4. A API estará disponível em: `http://localhost:8080`

## 📝 Melhorias Futuras
- Implementação de autenticação com Spring Security
- Paginação e ordenação nas listagens
- Documentação da API com Swagger
- Testes unitários e de integração

---

📌 **Desenvolvido por Kauã** 🚀

