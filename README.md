# Marvel API


## Versions

* Java 11
* Maven 3.8.1
* Spring 2.7.3

## Before to start

To start the application is required some information which you will be able to fetch by registering on Developer Marvel. Open the link below and access "Get an API key".

```console
  https://developer.marvel.com/documentation/getting_started
```

## Using Maven

### Run application

Use the public and private key created in the previous step, and replace by <PUT_YOUR_PUBLIC_KEY_HERE> and <PUT_YOUR_PRIVATE_KEY_HERE>.

```console
mvn spring-boot:run -Dspring-boot.run.arguments="--marvel-api.publicKey=<PUT_YOUR_PUBLIC_KEY_HERE> --marvel-api.privateKey=<PUT_YOUR_PRIVATE_KEY_HERE>"
```

## Using Docker

### Configuration

Before to run the application using docker, you must set up values to the variables below on docker-compose-yml file.
Replace by <PUT_YOUR_PUBLIC_KEY_HERE> and <PUT_YOUR_PRIVATE_KEY_HERE> using the values created in "Before to start" step.

* MARVEL_PUBLIC_KEY
* MARVEL_PRIVATE_KEY

### Run application

```console
docker-compose up -d
```

### Swagger

After to start the application, you will be able to access the API documentation using the link below

```console
http://localhost:8080/swagger-ui.html
```

