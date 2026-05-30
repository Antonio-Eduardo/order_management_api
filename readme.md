# Order Management API

![Status do Projeto](https://img.shields.io/badge/status-production-brightgreen)
![Java](https://img.shields.io/badge/Java-25-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.6-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue)
[![Deploy](https://img.shields.io/badge/Heroku-online-blueviolet)](https://order-management-api-d85bb0aa85e8.herokuapp.com/users)
[![Licença MIT](https://img.shields.io/badge/licenca-MIT-green)](https://github.com/Antonio-Eduardo/order_management_api/blob/master/LICENSE)

> API REST para gerenciamento de usuários e controle de pedidos com cálculo automático de subtotal e total. Deploy ativo no Heroku com banco PostgreSQL em produção.

**[→ API em produção](https://order-management-api-d85bb0aa85e8.herokuapp.com/users)**

---

## Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Modelo de Dados](#modelo-de-dados)
- [Endpoints Disponíveis](#endpoints-disponíveis)
- [Como Executar](#como-executar)
- [Decisões Técnicas](#decisões-técnicas)
- [Melhorias Futuras](#melhorias-futuras)

---

## Sobre o Projeto

A **Order Management API** é uma API REST desenvolvida com Spring Boot para gerenciamento de usuários e pedidos. O sistema modela 4 entidades relacionadas — `User`, `Orders`, `OrderItem` e `Product` — com cálculo automático de subtotal por item e total por pedido.

O projeto explora mapeamento ORM com relacionamentos `@OneToMany`, `@ManyToOne` e `@ManyToMany` entre entidades, com foco em baixo acoplamento e separação de responsabilidades entre controllers, services e repositories.

---

## Tecnologias Utilizadas

| Tecnologia | Uso |
|---|---|
| Java 25 | Linguagem principal |
| Spring Boot 4.0.6 | Framework principal |
| Spring Data JPA / Hibernate | Persistência e mapeamento ORM |
| H2 | Banco de dados em memória (perfil test) |
| PostgreSQL | Banco de dados em produção |
| Heroku | Deploy e hospedagem |
| Maven | Gerenciamento de dependências |

---

## Modelo de Dados

```
User (1) ──── (N) Orders (1) ──── (N) OrderItem (N) ──── (1) Product
```

- **User** — dados do cliente (nome, email, telefone, endereço)
- **Orders** — pedido vinculado a um usuário, com status e total calculado
- **OrderItem** — item do pedido com quantidade, preço unitário e subtotal automático
- **Product** — catálogo de produtos com nome, descrição e preço
- **Payment** — entidade criada, integração em desenvolvimento

**Cálculo automático:**
- `OrderItem.getSubTotal()` — `preço × quantidade`
- `Orders.getTotal()` — soma dos subtotais de todos os itens

---

## Endpoints Disponíveis

> Base URL em produção: `https://order-management-api-d85bb0aa85e8.herokuapp.com`

### Usuários — `/users`

| Método | Rota | Descrição |
|---|---|---|
| GET | `/users` | Lista todos os usuários |
| GET | `/users/{id}` | Busca usuário por ID |
| POST | `/users` | Cria novo usuário |
| PUT | `/users/{id}` | Atualiza usuário |
| DELETE | `/users/{id}` | Remove usuário |

**Exemplo:**
```http
POST /users
Content-Type: application/json

{
  "name": "João Silva",
  "email": "joao@email.com",
  "phone": "85999999999",
  "password": "senha123"
}
```

### Pedidos — `/orders`

| Método | Rota | Descrição |
|---|---|---|
| GET | `/orders` | Lista todos os pedidos |
| GET | `/orders/{id}` | Busca pedido por ID com total calculado |

### Produtos — `/products`

| Método | Rota | Descrição |
|---|---|---|
| GET | `/products` | Lista todos os produtos |
| GET | `/products/{id}` | Busca produto por ID |
| POST | `/products` | Cadastra novo produto |

---

## Como Executar

### Pré-requisitos

- Java 25+
- Maven

```bash
git clone https://github.com/Antonio-Eduardo/order_management_api.git
cd order_management_api
```

```bash
./mvnw spring-boot:run
```

A aplicação sobe com perfil `test` usando H2 em memória — sem necessidade de banco externo. A API estará disponível em `http://localhost:8080` e o console H2 em `http://localhost:8080/h2-console`.

---

## Decisões Técnicas

**H2 em dev, PostgreSQL em produção:** O projeto usa H2 em memória no perfil de testes para agilizar o desenvolvimento local sem dependência de banco externo. Em produção no Heroku, o PostgreSQL é injetado automaticamente via variável de ambiente.

**Cálculo de subtotal na entidade:** O `getSubTotal()` foi implementado como método calculado na entidade em vez de campo persistido, evitando inconsistências quando o preço do produto é atualizado após o pedido ser criado.

**Composição no modelo de pedidos:** `OrderItem` é tratado como valor dentro do pedido, não como entidade independente — mantém o cálculo de total encapsulado na própria entidade `Orders`.

---

## Melhorias Futuras

- [ ] Implementação completa dos métodos de pagamento
- [ ] Testes de integração com Testcontainers
- [ ] Documentação com Swagger/OpenAPI
- [ ] Autenticação com Spring Security e JWT
