# Challenge II - Microservices Integration

> Use this table of contents to jump to the section you want

| Content                                      | Link                    | Difficulty |
|:---------------------------------------------|:------------------------|:----------:|
| About the challenge                          | [Go](<insert the link>) |     -      |
| What the demands are                         | [Go](<insert the link>) |     -      |
| Project Structure                            | [Go](<insert the link>) |     -      |
| Central microservice                         | [Go](<insert the link>) |    high    |
| Individuals microservice                     | [Go](<insert the link>) |    high    |
| Usage of dockerfiles and docker-compose.yaml | [Go](<insert the link>) |   medium   |
| Unit tests                                   | [Go](<insert the link>) |    high    |
| Usage of OpenFeign                           | [Go](<insert the link>) |   medium   |
| Postman collection                           | [Go](<insert the link>) |    low     |

## About the challenge

Develop one main microservice as `ms-auth-and-auto` and five individual microservices as `ms-scholar-a`.  
Each individual microservice must comunicate with the main microservice

---

## What the demands are

---

> 1. The requirements for the project are listed on this section, just follow along.
> 2. Microservice specific requirements will be listed on their own sections

1.Usage of two fixed branches: main and dev

- `Main` branch will be used for the latest complete project version
- `Dev` branch will be used to assemble every feature developed before merging to main branch

2.Branches' **name pattern**: `[microservice-name]/feature-[feature-name]`

```mermaid
  gitGraph LR:
      commit id:"repository initalized"
      branch dev
      commit id:"dev initialized"
      branch ms-auth-and-auto/feature-rest-controller
      checkout ms-auth-and-auto/feature-rest-controller
      commit id:"feature: rest controller implemented"
      checkout dev
      branch ms-scholar-a
      commit id:"feature: jpa repo"
      checkout dev
      merge ms-auth-and-auto/feature-rest-controller type: HIGHLIGHT tag:"merge"
      merge ms-scholar-a type: HIGHLIGHT tag:"merge"
      checkout main
      merge dev type: HIGHLIGHT tag:"merge"

```

3.**Semantic commits** messages implementation, example below:

```git
 git commit -m "docs: updated README.md with new table of contents"
```

4.**README** as documentation

5.Usage of Docker with **docker-compose.yaml**

6.**Postman** or **Swagger** collection

7.Usage of at least one OneToMany, OneToOne or ManyToMany relation

---

## Project Structure

### Apresentation
- **Controller**: API controllers, responsible for receiving requests and sending responses.

### Application
- **DTOs**: Data Transfer Objects used to pass data between layers.
- **Interfaces**: Contracts for the services.
- **Services**: Contains high-level business logic and calls repository methods.

### Domain
- **Entities/Model**: Domain entities.
- **Interfaces**: Contracts for the Repositories.
- **Enums**: Enumerations used in entities and/or business rules.

### Infra
- **Config**: Project dependency configuration.
- **Constants**
- **Exceptions**: Exception configuration and its handler.

---

## Central microservice

The Central Microservice is designed to simulate the authentication and authorization of a user within a microservice. It consists of three controllers: `MicroserviceController`, `UserController`, and `UserMicroserviceRoleController`. Each controller has its own endpoints.

### MicroserviceController

The `MicroserviceController` has two endpoints:

1. `createMicroservice`: This endpoint is responsible for creating new microservices in the database.
2. `findById`: This endpoint is responsible for fetching a microservice by its ID from the database.

### UserController

The `UserController` has five endpoints:

1. `createUser`: This endpoint is responsible for creating a new user in the database.
2. `findById`: This endpoint is responsible for fetching a user by their ID from the database.
3. `bindUserToMicroserviceId`: This endpoint links a user to a microservice. The required parameters are `userId`, `microserviceId`, and `UserMicroserviceRequest` (a class that contains `emailUser` and `nameMicroservice`).
4. `getUsersAndPermissions`: This endpoint returns a list of users along with their linked microservices and permissions within those microservices.
5. `updateUserRoleInMicroservice`: This endpoint updates the user's role within the microservice. By default, when the user is linked to the microservice, their role is set as USER. With this endpoint, it's possible to change the user's role to ADMIN or from ADMIN to USER by passing the parameters: `userId`, `microserviceId`, and `newRole`.

### UserMicroserviceRoleController

The `UserMicroserviceRoleController` has three endpoints:

1. `getUsersByUserIdAndMicroserviceId`: This endpoint is responsible for searching for the relationship between the user and microservice and returns the user and the user's role within that microservice, passing as parameters: userId and microserviceId.
2. `getMicroserviceByMicroserviceId`: This endpoint validates if there is a microservice within UserMicroserviceRole, where the relationship between user and microservice is located. If the microservice exists within this relationship, the endpoint will return the microservice passed by parameter: microserviceId.
3. `getUsersByMicroserviceId`: This endpoint returns a list of users and their role linked to the microservice, parameter: microserviceId.

---

## Individuals microservice

### Commands to generate .jar file
``mvn clean package`` for each module or

``mvn clean package -DskipTests`` to skip all the tests and create a .jar lighter version

- For IntelliJ you can press ``ctrl ctrl`` and a Run Anything menu will pop up on your screen, just select a project within the Execution Context and then run the command above
- A ``target/`` folders will be generated automatically and from there we will copy the .jar file to the docker container

---

## Usage of dockerfiles and docker-compose.yaml
### What is docker
> Docker is an open platform for developing, shipping, and running applications. Docker enables you to separate your applications from your infrastructure, so you can deliver software quickly. [Source](https://docs.docker.com/get-started/overview/)

### Commands to use
``docker-compose build``
- To download and update the containers

``docker-compose up``
- To run the containers

### Dockerfile
> Each project has its own Dockerfile containing instructions to build the images

### docker-compose.yml
> This file sets how each container should act while running with docker
> While in development, we decided to use a specific designed by user netowrk to manage communication between the containers, setting fixed IPs to each microservice

### Containers
#### Individual Microservice container
```mermaid
stateDiagram 
    HARDWARE --> OS

    state OS {
        DOCKER --> appA
        DOCKER --> appB
        DOCKER --> appC
        DOCKER --> appD
        DOCKER --> appE
           

        }
        state appA {
            [*] --> javaCoretoAmazonA
            [*] --> alpinelinuxA

        }          

        state appB {
            [*] --> javaCoretoAmazonB
            [*] --> alpinelinuxB

        }
        state appC {
            [*] --> javaCoretoAmazonC
            [*] --> alpinelinuxC

        }
        state appD {
            [*] --> javaCoretoAmazonD
            [*] --> alpinelinuxD

        }
        state appE {
            [*] --> javaCoretoAmazonE
            [*] --> alpinelinuxE

}
```
#### DB container and Central Microservice container
```mermaid
stateDiagram 
    HARDWARE --> OS

    state OS {
        DOCKER --> db
        DOCKER --> appCentral
       
           
       state db {
           [*] --> postgreSQL
           [*] --> alpinelinuxdb
       }
        state appCentral {
            [*] --> javaCoretoAmazonCentral
            [*] --> alpinelinuxCentral

        }
        
}
```
---

## Unit tests

1. First test

---

## Usage of OpenFeign
### What is OpenFeign
> One great thing about using Feign is that we don’t have to write any code for calling the service, other than an interface definition. [Source](https://www.baeldung.com/spring-cloud-openfeign)

### How does it work
```mermaid 
    flowchart LR
        id1(Client Service) -. request.-> OpenFeign -. redirect.-> Server
        Server -. response.-> OpenFeign -.redirect.-> id1
```
> In this project's microservices OpenFeign is used to redirect a request using a RestTemplate to theCentral Microservice
> 
> The Central App's response is mapped to 
```mermaid 
    flowchart LR
        id1(Microservice A) -. request.-> OpenFeign -. redirect.-> id2(Central App)
        id2 -. response.-> OpenFeign -.redirect.-> id1
```

### Problems faced and how we solved it
1. Microservices could not communicate with the other containers
> For this the solution was to use a dynamic URL for the `@FeignClient(name = "ms-auth-and-auth", url = "${client.post.baseUrl}")`
> 
> Also, we set three application.yml. A standard, an a`pplication-DOCKER.yml` and an `application-LOCAL.yml`.
> 
> Disregarding the standard .yml file, there are different `{client.post.baseUrl}` instructions
>  1. DOCKER: fixed IP adress at `172.23.0.2`
>  2. LOCAL: standard `localhost`
> 
> While using outside docker environment, you should set LOCAL as the active profile 
> 
> While inside docker environment, you should set DOCKER as the active profile
> 
> **But, remember to do that before building the images and the containers**


---

## Postman collection
You can download the Postman Collection as a .json file [here](<link>)

| Central Microservice            | Name                            | Http method | Link                                                                                                                | Body / Query                                                         | 
|---------------------------------|---------------------------------|-------------|---------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------|
| user-controller                 | findById                        | GET         | [Go](http://localhost:8080/ms-auth-and-auto/api/users/{id})                                                         |                                                                      |
| user-controller                 | getUsersAndPermissions          | GET         | [Go](http://localhost:8080/ms-auth-and-auto/api/users)                                                              |                                                                      | 
| user-controller                 | createUser                      | POST        | [Go](http://localhost:8080/ms-auth-and-auto/api/users)                                                              | { "name": "name", "email": "email", "password": "password" }         |
| user-controller                 | bindUserToMicroservice          | PUT         | [Go](http://localhost:8080/ms-auth-and-auto/api/users/{userId}/microservice/{microserviceId})                       | { "emailUser": "emailUser", "nameMicroservice": "nameMicroservice" } |
| user-controller                 | updateUserRoleInMicroservice    | PUT         | [Go](http://localhost:8080/ms-auth-and-auto/api/users/{userId}/microservice/{microserviceId}/update-role)           | { "key": "newRole", "value": "USER"}                                 |
| microservice-controller         | createMicroservice              | POST        | [Go](http://localhost:8080/ms-auth-and-auto/api/microservices)                                                      | { "name": "nameMicroservice"}                                        |
| microservice-controller         | findById                        | GET         | [Go](http://localhost:8080/ms-auth-and-auto/api/microservices/{id})                                                 |                                                                      |
| userMicroserviceRole-controller | getUserMicroserviceRole         | GET         | [Go](http://localhost:8080/ms-auth-and-auto/api/userMicroserviceRole/user/{id-user}/microservice/{id-microservice}) |                                                                      |
| userMicroserviceRole-controller | getMicroserviceByMicroserviceId | GET         | [Go](http://localhost:8080/ms-auth-and-auto/api/userMicroserviceRole/{microserviceId})                              |                                                                      |
| userMicroserviceRole-controller | getUsersByMicroserviceId        | GET         | [Go](http://localhost:8080/ms-auth-and-auto/api/userMicroserviceRole/{microserviceId}/users)                        |                                                                      |
> For each individual microservice, we recommend changing some values as:
> - Individual microservice A
>   - Port: 8081
>   - Context path: /ms-a
> - Individual microservice B
>   - Port: 8082
>   - Context path: /ms-b
> - Individual microservice C
>   - Port: 8083
>   - Context path: /ms-c
> - Individual microservice D
>   - Port: 8084
>   - Context path: /ms-d
> - Individual microservice E
>   - Port: 8085
>   - Context path: /ms-e

| Related Controller      | Name                                   | Http method | Link                                                                                |
|-------------------------|----------------------------------------|-------------|-------------------------------------------------------------------------------------|
| user-controller         | findUserByIdAndMicroserviceId          | GET         | [Go](http://localhost:8081/ms-a/api/scholar/{userId}/microservice/{microserviceId}) |
| microservice-controller | getMicroserviceByIdAndUsersPermissions | GET         | [Go](http://localhost:8081/ms-a/api/scholar/microservice/{microserviceId})          |







---
