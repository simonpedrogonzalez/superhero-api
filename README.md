# Superhero API

Superhero API made using Spring Boot. It implements a basic CRUD of superheroes.

## Features
1. Uses liquibase and in memory H2. Schema and data are automatically added at the start. You can access /h2-console to see database contents.
2. Unit testing at service level.
3. Basic caching at service level.
4. Custom TimedAPICall annotation that logs the time it takes to fulfill a request.
5. Has only a basic security configuration, as no details were given on the security requirements.
6. API documentation is provided in swagger-ui through http://localhost:8888/swagger-ui/index.html
7. Centralized exception handling.
8. Dockerized.
9. Basic Intellij configuration files in case you want to setup a dev environment.

## Instructions
1. `git clone https://github.com/simonpedrogonzalez/superhero-api.git`
2. `cd superhero-api`
3. `docker-compose up -d` (all env variables have defaults so no configuration required)
4. enjoy ;)

## Requirements

Docker & DockerCompose.