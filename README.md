# POLITICAL SPEECH API

___
### Spring Boot Application

---
This project processing statistics about political speeches.


### Prerequisites

---
- Maven
- Docker

### Run & Build
There are 2 ways of run & build the application.

1-Maven:

Install maven dependencies with command below

```sh
mvn clean install
```

After that for running the application use the command below

```sh
mvn spring-boot:run
```

Api documentation: 
http://localhost:8080/swagger-ui/index.html

Database connection:
http://localhost:8080/h2-console/

user:user

password:password

2-Docker

Note: There is an issue on Docker side. For now, you can run application with maven. 
Issue: BeanCreationException: Error creating bean with name 'politicalSpeechService': Invocation of init method failed


Run below command within the project path with given sequence

```sh
docker-compose up --build
```


APi documentation: 
http://localhost:9095/swagger-ui/index.html

Database connection:
http://localhost:8080/h2-console/

user:user

password:password






### TechStacks

---
- Java 17
- Spring Boot
- Kotlin
- H2 DB
- Restful API
- Maven
- JUnit 5
- Sonarlint (local)
- Spotify (https://open.spotify.com/playlist/7yDx2tF46QYAfMnGxEcY2h?si=8e61d071ec4941b7)


