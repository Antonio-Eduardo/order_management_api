# Order Management API

![Status do Projeto](https://img.shields.io/badge/status-production-brightgreen)
![Java](https://img.shields.io/badge/Java-25-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.6-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue)
[![Deploy](https://img.shields.io/badge/Railway-online-blueviolet)](https://ordermanagementapi-production-a567.up.railway.app/swagger-ui/index.html)
[![Licença MIT](https://img.shields.io/badge/licenca-MIT-green)](https://github.com/Antonio-Eduardo/order_management_api/blob/master/LICENSE)

> API REST para gerenciamento de usuários e controle de pedidos com cálculo automático de subtotal e total. Deploy ativo no Railway com banco PostgreSQL em produção.

**[→ API em produção](https://ordermanagementapi-production-a567.up.railway.app/swagger-ui/index.html)**

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

A **Order Management API** é uma API REST desenvolvida com Spring Boot para gerenciamento de usuários e pedidos. O sistema modela 5 entidades relacionadas — `User`, `Orders`, `OrderItem`, `Product` e `Category` — com cálculo automático de subtotal por item e total por pedido.

O projeto explora mapeamento ORM com relacionamentos `@OneToMany`, `@ManyToOne` e `@ManyToMany` entre entidades, com foco em baixo acoplamento e separação de responsabilidades entre controllers, services e repositories. A documentação interativa está disponível via Swagger/OpenAPI.

---

## Tecnologias Utilizadas

| Tecnologia | Uso |
|---|---|
| Java 25 | Linguagem principal |
| Spring Boot 4.0.6 | Framework principal |
| Spring Data JPA / Hibernate | Persistência e mapeamento ORM |
| SpringDoc OpenAPI (Swagger) | Documentação interativa da API |
| H2 | Banco de dados em memória (perfil test) |
| PostgreSQL | Banco de dados em produção |
| Railway | Deploy e hospedagem |
| Maven | Gerenciamento de dependências |

---

## Modelo de Dados

```
User (1) ──── (N) Orders (1) ──── (N) OrderItem (N) ──── (1) Product
                                                                 │
                                                         (N) ───┤
                                                                 │
                                                           Category (N)
```

- **User** — dados do cliente (nome, email, telefone, senha)
- **Orders** — pedido vinculado a um usuário, com status e total calculado
- **OrderItem** — item do pedido com quantidade, preço unitário e subtotal automático
- **Product** — catálogo de produtos com nome, descrição, preço e imagem
- **Category** — categorias associadas aos produtos via `@ManyToMany`
- **Payment** — entidade criada, integração em desenvolvimento

**Cálculo automático:**
- `OrderItem.getSubTotal()` — `preço × quantidade`
- `Orders.getTotal()` — soma dos subtotais de todos os itens

---

## Endpoints Disponíveis

> Base URL em produção: `https://ordermanagementapi-production-a567.up.railway.app`
>
> Documentação interativa: [/swagger-ui/index.html](https://ordermanagementapi-production-a567.up.railway.app/swagger-ui/index.html)

### Usuários — `/users`

| Método | Rota | Descrição |
|---|---|---|
| GET | `/users` | Lista todos os usuários |
| GET | `/users/{id}` | Busca usuário por ID |
| POST | `/users/insert` | Cria novo usuário |
| PUT | `/users/{id}` | Atualiza usuário |
| DELETE | `/users/delete/{id}` | Remove usuário |

**Exemplo:**
```http
POST /users/insert
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
| POST | `/orders/insert/{id}` | Cria pedido para o usuário com o ID informado |

**Exemplo:**
```http
POST /orders/insert/1
Content-Type: application/json

{
  "orderStatus": 1,
  "items": [
    { "productId": 2, "quantity": 3 }
  ]
}
```

### Produtos — `/product`

| Método | Rota | Descrição |
|---|---|---|
| GET | `/product` | Lista todos os produtos |
| GET | `/product/{id}` | Busca produto por ID |
| POST | `/product/insert` | Cadastra novo produto com categorias |

### Categorias — `/category`

| Método | Rota | Descrição |
|---|---|---|
| GET | `/category` | Lista todas as categorias |
| GET | `/category/{id}` | Busca categoria por ID |
| POST | `/category/insert` | Cadastra nova categoria |

### Itens de Pedido — `/order-item`

| Método | Rota | Descrição |
|---|---|---|
| GET | `/order-item` | Lista todos os itens de pedidos |
| GET | `/order-item/order-items/{orderId}/{productId}` | Busca item por chave composta |

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

A aplicação sobe com perfil `test` usando H2 em memória — sem necessidade de banco externo. A API estará disponível em `http://localhost:8080` e a documentação Swagger em `http://localhost:8080/swagger-ui/index.html`.

> A rota raiz `/` redireciona automaticamente para o Swagger UI.

---

## Decisões Técnicas

**H2 em dev, PostgreSQL em produção:** O projeto usa H2 em memória no perfil de testes para agilizar o desenvolvimento local sem dependência de banco externo. Em produção no Railway, o PostgreSQL é injetado automaticamente via variável de ambiente.

**Cálculo de subtotal na entidade:** O `getSubTotal()` foi implementado como método calculado na entidade em vez de campo persistido, evitando inconsistências quando o preço do produto é atualizado após o pedido ser criado.

**Composição no modelo de pedidos:** `OrderItem` é tratado como valor dentro do pedido usando chave primária composta (`@EmbeddedId`), mantendo o cálculo de total encapsulado na própria entidade `Orders`.

**DTOs em todas as camadas:** Requests e responses usam DTOs dedicados, desacoplando a representação externa das entidades JPA e evitando expor dados sensíveis como senha.

**CORS configurado:** A configuração de CORS permite origem da URL de produção no Railway, além do ambiente local.

---

## Melhorias Futuras

- [ ] Implementação completa dos métodos de pagamento
- [ ] Testes de integração com Testcontainers
- [ ] Autenticação com Spring Security e JWT
- [ ] Validação de campos com Bean Validation (`@NotBlank`, `@Email`, etc.)
