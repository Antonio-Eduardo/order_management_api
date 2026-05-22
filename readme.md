# Order Management API

![Status do Projeto](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)
[![Static Badge](https://img.shields.io/badge/licenca-MIT-green)](https://github.com/Antonio-Eduardo/order_management_api/blob/master/LICENSE)
[![Deploy](https://img.shields.io/badge/Heroku-online-blueviolet)](https://order-management-api-d85bb0aa85e8.herokuapp.com/users)

> API REST para gerenciamento de usuários e controle de pedidos.

## Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Funcionalidades](#funcionalidades)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Como Executar o Projeto](#como-executar-o-projeto)
- [Melhorias Futuras](#melhorias-futuras)

---

## Sobre o Projeto

A **Order Management API** é uma aplicação backend com API REST e persistência de dados em banco de dados relacional. O sistema é composto pelas entidades `User`, `Orders`, `OrderItem` e `Product`, permitindo um controle detalhado dos produtos em cada pedido realizado.

> *Nota de desenvolvimento:* Durante o projeto, trabalhei intensivamente com ORM (Object-Relational Mapping), visando compreender profundamente o relacionamento entre as entidades e suas dependências, sempre priorizando o baixo acoplamento entre as classes. A arquitetura foi baseada na separação estrita de responsabilidades, utilizando camadas de controllers, services e repositories para garantir organização, escalabilidade e facilidade de manutenção do código.

---

## Funcionalidades

- [x] **Gerenciamento de Usuários:** Criar, consultar, atualizar e deletar usuários
- [x] **Controle de Pedidos:** Criar, consultar, atualizar e deletar pedidos associados aos respectivos clientes
- [x] **Controle de Produtos:** Criar e consultar produtos
- [x] **Cálculo Automatizado:** Montagem de pedidos com cálculo automático de `SubTotal` (por item) e `Total` (do pedido)
- [ ] **Métodos de Pagamento:** Integração e registro do método de pagamento — entidade criada, **ainda em desenvolvimento**

---

## Tecnologias Utilizadas

- **Java** (JDK 17+)
- **Spring Boot** (Spring Web, Spring Data JPA)
- **JPA / Hibernate**
- **MySQL** (ambiente local) / **PostgreSQL** (produção)
- **Postman** (validação e testes dos endpoints)
- **Heroku** (deploy e hospedagem)
- **Maven**
- **Git / GitHub**

---

## Estrutura do Projeto

```
src/
└── main/
    └── java/
        ├── controllers/    → Endpoints e rotas da API
        ├── services/       → Regras de negócio
        ├── repositories/   → Acesso a dados com Spring Data JPA
        └── entities/       → Mapeamento objeto-relacional (User, Orders, OrderItem, Product)
```

---

## Como Executar o Projeto

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/Antonio-Eduardo/order_management_api.git
   ```

2. **Acesse a pasta do projeto:**
   ```bash
   cd order_management_api
   ```

3. **Execute o projeto usando o Maven Wrapper:**

   No Windows:
   ```bash
   mvnw.cmd spring-boot:run
   ```

   No Linux/Mac:
   ```bash
   ./mvnw spring-boot:run
   ```


---

## Melhorias Futuras

- [ ] Implementação completa dos métodos de pagamento
- [ ] Testes unitários e de integração
- [ ] Documentação com Swagger/OpenAPI
