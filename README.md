# Projeto SKYRESERVE

- Este documento demonstra como configurar e testar uma aplicação reativa em Java com o PostgreSQL Local e Kafka utilizando Docker.
- Antes de iniciar utilize o docker-compose para subir o ambiente, KafkaDrop para ver os tópicos, ZooKeeper, PostgreSQL e Redis. Todos dentro do container skyreserve.
- O Kafka Drop utilizamos para visualizar os tópicos pelo navegador. (http://localhost:9000/)
- URL para utilizar no postman e startar a aplicação (POST e o BODY): http://localhost:8080/
- Na pasta Collections estão os dados para fazer as requisições no postman (collection/SkyReserve.postman_collection.json). 
- As variáveis de ambiente estão na pasta env.
- Ao iniciar a aplicação, não se esqueça de verificar se existe o tópico (teste) no KafkaDrop: http://localhost:9000
- Obs: a variável de ambiente Local (localizada no .env) cria alguns registros iniciais como: assento, aeronave, passageiro, etc. Para facilitar na hora de gerar uma reserva por exemplo.
- A pasta initial_files é apenas um backup para a criação das tabelas do banco de dados. Isso já é feito ao iniciar a aplicação.

## Pré-requisitos

- [Docker](https://docs.docker.com/get-docker/) baixar o Docker desktop.
- Java 11 ou superior instalado
- Para iniciar execute o comando docker-compose up no diretório do arquivo docker-compose.yaml
- Para baixar diversos tipos de imagens: https://hub.docker.com/
- Download do DBeaver para acessar o banco de dados: https://dbeaver.io/download/
- Download do Postman para executar as requisições: https://www.postman.com/downloads/
- Download do Redis Insight para visualizar dados do Cache do Redis: https://redis.io/docs/latest/operate/redisinsight/install/install-on-desktop/

>------------------------------------------------------------------------------------------------------------------------------
### ALGUNS COMANDOS PARA TESTES (Kafka):

### Acesse a conexão do Kafka primeiro:
docker exec -it kafka bash

### Listar tópicos:
kafka-topics --list --bootstrap-server localhost:9092

### Criar tópico:
kafka-topics --create --topic test-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1

------------------------------------------------------------------------------------------------------------------------------

Última atualização: 18/10/2024
