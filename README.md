# Prova de Conceito - Arquitetura de Software - PUC Minas
Este repositório mantém os códigos fontes da prova de conceito elaborada para o trabalho de conclusão de curso da pós graduação em Arquitetura de Sistemas Distribuídos.

## Escopo do projeto
O escopo escolhido para o TCC foi o de Sistema de controle de vendas com a modalidade Dropshipping.

## Técnologias utilizadas

### Backend

* [Java](https://java.com/en/download/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [H2 batabase](http://www.h2database.com/html/main.html)
* [Flyway](https://flywaydb.org/)
* [PostgreSQL](https://www.postgresql.org/download/)
* [Maven](https://maven.apache.org/)

### Frontend

* [Angular](https://angular.io/)
* [Angular Cli](https://cli.angular.io/)
* [Bootstrap](https://getbootstrap.com/)
* [Font Awsome](https://fontawesome.com/)
* [Nodejs](https://nodejs.org/en/)
* [Npm](https://www.npmjs.com/)


## Executando a aplicação

1. [Docker](https://www.docker.com/) Pré-requisito
2. Baixar o arquico docker-compose.yml deste repositório e executar o comando: docker-compose up
3. A aplicação estará disponível na URL: http://localhost:4200/
4. Para o primeiro acesso ao sistema,o usuário de Administrador do sistema já é criado:
  * Usuário: admin@pidecor.com.br
  * Senha: 123456

## Projetos do repositório
* admin-module-backend: É o projeto em backend que contém todas as API's utilizadas na aplicação.
* providers-mock: É a mock de um sistema de terceiros que se comunica via protocolo SOAP. A aplicação tem uma forte integração com este projeto.
* admin-module-frontend: Projeto frontend da aplicação.
