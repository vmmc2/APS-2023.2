# ViviBurguer BackEnd

This is the backend application for ViviBurguer built with Spring Boot.

## How to build

To build this application, you will need maven `1.2x` installed in your machine. After the installation, you can simply run the following command to generate the `jar` artifacts for this application:

```bash
mvn clean package
```

After, you will have the builded artifact in the `target/` folder.

## How to run

Currently, we need to mock the creditcard application to work properly. You can configure the endpoint of the mocked credit card backend at the `application.properties` file.

After the credit card mock endpoint set, you can simply run the builded artifact at the `target/` folder using the following command:

```bash
java -jar projeto-0.0.1-SNAPSHOT.jar
```

### Available endpoints

> TODO