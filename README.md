# Library management system
A simple library management system with entities book, patron, and borrow record

# Setup

## Database

Before getting starting with the application we should setup Postgres and run the DDL scrips

1. install postgres either using Docker or installing it according to your system
    1.1 Docker you can follow [this guide](https://www.docker.com/blog/how-to-use-the-postgres-docker-official-image/#How-to-run-Postgres-in-Docker)

    1.2 Ubuntu or any debian based system follow [this guide](https://www.digitalocean.com/community/tutorials/how-to-install-postgresql-on-ubuntu-20-04-quickstart)

    1.3 MacOS follow [this guide](https://www.postgresqltutorial.com/postgresql-getting-started/install-postgresql-macos/)

    1.4 Windows follow [this guide](https://www.postgresqltutorial.com/postgresql-getting-started/install-postgresql/)

2. run the DDL scripts attached with the project, can be found in `<project>/src/main/resources/scripts/schema.sql`

## Running application

1. Using your IDE helpful run button
2. Running maven wrapper attched with the project `mvnw spring-boot:run` or `mvnw.cmd spring-boot:run` for windows

## Tests

1. Using your IDE helpful test run buttons or test panel
2. Running maven wrapper attched with the project `mvnw test` or `mvnw.cmd test` for windows

note: regarding tests it doesn't need postgres to be satup before hand because it uses H2 for ease of testing