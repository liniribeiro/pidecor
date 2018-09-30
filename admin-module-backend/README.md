# PIDECOR | ADMIN

## Objetivo

Projeto de uma loja na modalidade Dropshipping (empresa que não trabalha com estoque), tem como objetivo trazer soluções para atender a demanda de um segmento diferenciado.

## Módulos

## Implantação
Para implantar a Pidecor | Admin, é necessário ter um docker instalado e então deve ser realizados os seguintes comandos:
* Para iniciar o container do banco de dados PostgreSQL:
 ```docker run -d --name docker-postgres -e POSTGRES_DB=db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres postgres:10.4```
* Para iniciar a aplicação:
``` docker run -it --link docker-postgres --name="pi-docker-postgres" -p 8080:8080 aliniribeiroo/pidecor-admin:latest```

## Integração Contínua

[![Build Status](https://travis-ci.org/aliniribeiroo/pidecor-admin-backend.svg?branch=master)](https://travis-ci.org/aliniribeiroo/pidecor-admin-backend)
