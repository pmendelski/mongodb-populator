# MongoDB Populator

Populates MongoDB with sample data.
Use it to test mongo queries on big data sets.

```sh
../gradlew build
java -jar ./build/libs/mongodb-populator.jar <uri> <collection> <documents>
```

## Sample usage

Create 1000000 documents in structures collection.

```sh
../gradlew build
java -jar ./build/libs/mongodb-populator.jar mongodb://localhost/test structures 1000000
```