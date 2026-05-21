# Order Management API
![Status do Projeto](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)
[![Static Badge](https://img.shields.io/badge/licenca-MIT-green)](https://github.com/Antonio-Eduardo/order_management_api/blob/master/LICENSE) 
[![Deploy](https://img.shields.io/badge/Heroku-online-blueviolet)](https://order-management-api-d85bb0aa85e8.herokuapp.com/users)

> API REST para gerenciamento de usuários e controle de pedidos.

## Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Funcionalidades](#funcionalidades)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Como Executar o Projeto](#como-executar-o-projeto)

## Sobre o Projeto

A **Order Management API** é uma aplicação Backend com API REST e persistência de dados em banco de dados relacional. O sistema é composto pelas entidades `User`, `Orders`, `OrdersItem` 
e cada item do pedido está associado a um `Product`, permitindo um controle detalhado dos produtos em cada pedido realizado.

> *Nota de desenvolvimento:* Durante o projeto, trabalhei intensivamente com ORM (Object-Relational Mapping), visando compreender profundamente o relacionamento entre as entidades
> e suas dependências, sempre priorizando o baixo acoplamento entre as classes. A arquitetura foi baseada na separação estrita de responsabilidades, utilizando camadas de controllers,
> services e repositories para garantir a organização, escalabilidade e facilidade de manutenção do código.

## Funcionalidades

- [x] **Gerenciamento de Clientes:** Criar, consultar, atualizar e deletar usuários.
- [x] **Controle de Pedidos:** Criar, consultar, atualizar e deletar pedidos associados aos respectivos clientes.
- [x] **Controle de Produtos:** Criar, consultar produtos. 
- [x] **Cálculo Automatizado:** Montagem de pedidos com cálculo automático de `SubTotal` (por item) e `Total` (do pedido).
- [x] **Métodos de Pagamento:** Integração e registro do método de pagamento escolhido para o fechamento do pedido(Entidade já criada mas **Ainda em Desenvolvimento**.

## Tecnologias Utilizadas

As principais ferramentas, linguagens e frameworks usados no desenvolvimento deste ecossistema:

- **Java** (JDK 17+)
- **Spring Boot** (Spring Web, Spring Data JPA)
- **JPA / Hibernate**
- **Bancos de Dados Relacionais** (MySQL para ambiente local / PostgreSQL para produção)
- **Postman** (Para validação e testes dos endpoints)
- **Heroku** (Cloud Platform para deploy e hospedagem)

- ## Como Executar o Projeto

1. **Clone o repositório:**
   ```bash
   git clone [https://github.com/Antonio-Eduardo/order_management_api.git](https://github.com/Antonio-Eduardo/order_management_api.git)
2. **Acesse a pasta do projeto:**
   ```bash
   cd order_management_api
3. **Execute o projeto usando o Maven Wrapper:**

**No Windows:**
  ```markdown
mvnw.cmd spring-boot:run
```
**No Linux/Mac:**
 ```markdown
./mvnw spring-boot:run
```
