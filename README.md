<h1 align="center">📚 Biblioteca API - Sistema de Gestão de Biblioteca</h1>

<p align="center">
  <a href="#-tecnologias">Tecnologias</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-projeto">Projeto</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-como-executar">Como executar</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-documentacao">Documentação</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-endpoints">Endpoints</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-autor">Autor</a>
</p>

<p align="center">
  <img alt="Java" src="https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk">
  <img alt="Spring Boot" src="https://img.shields.io/badge/Spring_Boot-3.x-brightgreen?style=for-the-badge&logo=spring-boot">
  <img alt="PostgreSQL" src="https://img.shields.io/badge/PostgreSQL-blue?style=for-the-badge&logo=postgresql">
  <img alt="OpenAPI" src="https://img.shields.io/badge/OpenAPI-3.1-green?style=for-the-badge&logo=openapi-initiative">
  <img alt="Status" src="https://img.shields.io/badge/status-em%20desenvolvimento-yellow?style=for-the-badge">
</p>

<br>

> ⚠️ **Projeto em desenvolvimento.** Este README reflete o estado atual do backend e será atualizado conforme novas funcionalidades forem implementadas.

## ✨ Tecnologias

Este projeto foi desenvolvido utilizando as seguintes tecnologias:

* Java 17
* Spring Boot 3.x
* Spring Data JPA
* PostgreSQL
* Maven
* OpenAPI 3.1 (Swagger / springdoc)
* REST API
* DTO Pattern + Mapper
* Bean Validation (`jakarta.validation`)
* Paginação com Spring Data

---

## 💻 Projeto

O **Biblioteca API** é uma solução backend desenvolvida para centralizar e gerenciar as informações de uma biblioteca.

A aplicação permite o cadastro e gerenciamento de:

* Autores
* Categorias
* Livros
* Usuários
* Empréstimos (vínculo entre usuários e livros)

A API foi construída seguindo boas práticas de desenvolvimento backend utilizando Spring Boot, DTOs para transferência de dados, tratamento centralizado de exceções e documentação automática através do OpenAPI.

---

<a name="-como-executar"></a>

## 🚀 Como Executar

### Pré-requisitos

Antes de iniciar o projeto, certifique-se de possuir:

* JDK 17
* Maven 3.9+
* PostgreSQL
* Git

### 1. Clonar o repositório

```bash
git clone <url-do-repositorio>

cd biblioteca_backend
```

### 2. Configurar o banco de dados

Configure a conexão com o PostgreSQL em `application.properties` / `application.yml` (URL, usuário e senha).

### 3. Compilar o projeto

```bash
./mvnw clean package
```

ou

```bash
mvn clean package
```

### 4. Acessar a aplicação

A aplicação roda com o context-path `/biblioteca-service`. Por isso, acessar apenas `http://localhost:8080` retorna **404 - Not Found** (é esperado, não é erro). A API estará disponível em:

```text
http://localhost:8080/biblioteca-service
```

---

<a name="-documentacao"></a>

## 📖 Documentação

A documentação da API é gerada automaticamente utilizando OpenAPI 3.1.

### Swagger UI

```text
http://localhost:8080/biblioteca-service/swagger-ui/index.html#/
```

### OpenAPI JSON

```text
http://localhost:8080/biblioteca-service/v3/api-docs
```

---

<a name="-endpoints"></a>

# 📚 Endpoints

Todos os recursos seguem o mesmo padrão CRUD, com listagem paginada.

## 👤 Autor

| Método | Endpoint            | Descrição                                             |
| ------ | -------------------- | -------------------------------------------------------- |
| GET    | `/biblioteca-service/autor`      | Lista autores paginados                                 |
| GET    | `/biblioteca-service/autor/{id}` | Busca um autor por ID                                    |
| POST   | `/biblioteca-service/autor`      | Cadastra um novo autor                                   |
| PUT    | `/biblioteca-service/autor/{id}` | Atualiza um autor                                         |
| DELETE | `/biblioteca-service/autor/{id}` | Remove um autor. Não pode ser excluído se possuir livros vinculados |

---

## 🏷️ Categoria

| Método | Endpoint                | Descrição                                             |
| ------ | -------------------------- | -------------------------------------------------------- |
| GET    | `/biblioteca-service/categoria`      | Lista categorias paginadas                               |
| GET    | `/biblioteca-service/categoria/{id}` | Busca uma categoria por ID                                |
| POST   | `/biblioteca-service/categoria`      | Cadastra uma nova categoria                               |
| PUT    | `/biblioteca-service/categoria/{id}` | Atualiza uma categoria                                     |
| DELETE | `/biblioteca-service/categoria/{id}` | Remove uma categoria                                       |

---

## 📖 Livro

| Método | Endpoint             | Descrição                                             |
| ------ | ---------------------- | -------------------------------------------------------- |
| GET    | `/biblioteca-service/livro`      | Lista livros paginados                                   |
| GET    | `/biblioteca-service/livro/{id}` | Busca um livro por ID                                      |
| POST   | `/biblioteca-service/livro`      | Cadastra um novo livro                                     |
| PUT    | `/biblioteca-service/livro/{id}` | Atualiza um livro                                           |
| DELETE | `/biblioteca-service/livro/{id}` | Remove um livro                                             |

---

## 🙋 Usuário

| Método | Endpoint                | Descrição                                             |
| ------ | -------------------------- | -------------------------------------------------------- |
| GET    | `/biblioteca-service/usuario`      | Lista usuários paginados                                 |
| GET    | `/biblioteca-service/usuario/{id}` | Busca um usuário por ID                                    |
| POST   | `/biblioteca-service/usuario`      | Cadastra um novo usuário                                   |
| PUT    | `/biblioteca-service/usuario/{id}` | Atualiza um usuário                                         |
| DELETE | `/biblioteca-service/usuario/{id}` | Remove um usuário                                           |

---

## 🔄 Empréstimo

| Método | Endpoint                   | Descrição                                             |
| ------ | ----------------------------- | -------------------------------------------------------- |
| GET    | `/biblioteca-service/emprestimo`      | Lista empréstimos paginados                              |
| GET    | `/biblioteca-service/emprestimo/{id}` | Busca um empréstimo por ID                                 |
| POST   | `/biblioteca-service/emprestimo`      | Cadastra um novo empréstimo (vincula usuário e livros)     |
| PUT    | `/biblioteca-service/emprestimo/{id}` | Atualiza um empréstimo (usuário, livros, datas, status)    |
| DELETE | `/biblioteca-service/emprestimo/{id}` | Remove um empréstimo                                       |

**Parâmetros de consulta (`GET` de todos os recursos acima):**

| Parâmetro | Tipo     | Obrigatório | Descrição                                                  |
| --------- | -------- | ------------ | -------------------------------------------------------------- |
| `page`/`size`/`sort` | Pageable | Não | Parâmetros padrão de paginação do Spring Data (default: `size=10`) |

---

## 🗂️ Estrutura de DTOs

### Autor

* AutorCreateRequestDTO
* AutorUpdateRequestDTO
* AutorResponseDTO

### Categoria

* CategoriaCreateRequestDTO
* CategoriaUpdateRequestDTO
* CategoriaResponseDTO

### Livro

* LivroCreateRequestDTO
* LivroUpdateRequestDTO
* LivroResponseDTO

### Usuário

* UsuarioCreateRequestDTO
* UsuarioUpdateRequestDTO
* UsuarioResponseDTO

### Empréstimo

* EmprestimoCreateRequestDTO
* EmprestimoUpdateRequestDTO
* EmprestimoResponseDTO

### Paginação

* Page (Spring Data)

---

## 📋 Regras de Negócio

### Autor

* `nome` e `nacionalidade` são normalizados (trim + maiúsculas) antes de salvar.
* Um autor **não pode ser excluído** se houver livros vinculados a ele.

### Categoria

* `nome` e `descricao` são únicos no sistema; tentativas de duplicidade retornam conflito.
* Textos são normalizados (trim + maiúsculas) antes de salvar.

### Livro

* `titulo` e `isbn` são únicos no sistema; o ISBN é limpo (mantém apenas dígitos) antes de ser validado e salvo.
* Autores (`idsAutores`) e categoria (`idCategoria`) informados precisam existir; caso contrário, retorna erro de não encontrado.
* Um livro pode ter um ou mais autores vinculados (relação N:N).
* `titulo` e `editora` são normalizados (trim + maiúsculas) antes de salvar.

### Usuário

* `cpf` e `email` são únicos no sistema; tentativas de duplicidade retornam conflito.
* O CPF é limpo (mantém apenas dígitos) antes de ser validado e salvo.
* `nome` é normalizado (trim + maiúsculas) antes de salvar.

### Empréstimo

* Exige um usuário (`idUsuario`) e ao menos um livro (`idsLivro`) válidos para ser criado.
* Um empréstimo pode conter um ou mais livros vinculados (relação N:N).
* Datas (`dataEmprestimo`, `dataPrevisaoDevolucao`, `dataDevolucaoReal`) e `status` podem ser atualizados de forma independente.

---

## 🏗️ Arquitetura

O projeto segue uma arquitetura baseada em:

* Controllers
* Services
* Repositories
* DTOs (Request / Response)
* Entities (Model)
* Mapper
* Tratamento de exceções (`NotFoundException`, `ConflictException`)
* OpenAPI (springdoc)
* Spring Data JPA
* Paginação de resultados

---

## 📈 Funcionalidades

✅ Cadastro de Autores

✅ Cadastro de Categorias

✅ Cadastro de Livros

✅ Cadastro de Usuários

✅ Cadastro de Empréstimos (vínculo entre usuário e livros)

✅ Consulta por ID

✅ Atualização de registros

✅ Exclusão de registros com validação de vínculos (ex: autor com livros associados)

✅ Paginação de resultados

✅ Documentação automática via Swagger

🚧 Autenticação e autorização

🚧 Testes automatizados

🚧 Filtros de busca avançados nas listagens

🚧 Controle de disponibilidade de exemplares no empréstimo

🚧 Dockerização do projeto

---

<a name="-autor"></a>

## 🧑‍💼 Autor

**Jonatha Maciel Vianna**

Desenvolvimento Backend • Modelagem da API • Documentação

---

## 📄 Licença

Projeto pessoal, desenvolvido para fins de estudo e portfólio.