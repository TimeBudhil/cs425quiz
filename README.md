# Maven Project

## Prerequisites

The following runtime tools are required:

* Java 25 (JDK 25)
* Apache Maven 3.9+

Verify installations:

```bash
java -version
mvn -version
```

## Build the Project

To clean and package the application:

```bash
mvn clean package
```

The generated artifact will be available in the `target/` directory.

## Run the Application

If the build produces an executable JAR file, run it with:

```bash
java -jar target/cs425quiz1-1.0-SNAPSHOT.jar
```

