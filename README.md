# BankingAccount
Web application for emulation banking transactions

## Getting started
This instructions will help you to run this application on your machine.

### Prerequisites
For installing this application you should have:

```
java 1.8
or
docker
```

### Installing
There is two ways to run this application.

### Java
To run it using java you should just run the .jar file
```
java -jar target/agileengine-1.0.jar
```

### Docker
If you want to run it using docker just wollow this steps:
```
docker build -t [image_name] .
docker run -it --name [container_name] -p 8080:8080 [image_name]
```

## Running
After running you will be able to use your
```
localhost:8080
```
as the web page for your transaction history

API for this application available [here](https://agileengine.bitbucket.io/mfsTEedVjvSVjNYm/api/#/)

Example of request
```
GET localhost:8080/transactions
```
and response from server
```
{
    "statusCode": 200,
    "errorText": null,
    "transactionList": [
        {
            "id": "12345789",
            "type": "credit",
            "amount": 30,
            "effectiveDate": "2018-05-25T11:16:22.516Z"
        },
        {
            "id": "12345979",
            "type": "debit",
            "amount": 20,
            "effectiveDate": "2018-05-25T11:16:22.516Z"
        }
    ]
}
```

## Built With
* [Java 1.8](https://java.com/ru/) - Programming language
* [Spring](https://spring.io/) - Java EE Framework
* [Maven](https://maven.apache.org/) - Dependency Management
* [Thymeleaf](https://www.thymeleaf.org/) - Server-side Java template engine
