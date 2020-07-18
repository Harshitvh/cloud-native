This application is a LIFO Implemantation which stores the data into the persistent store,
i.e an MS SQL Database or the Postgres SQL Database

An user is provided with an option to select the database in the runtime

Instruction to run the application:
Step 1.
mvn clean install

Step 2. Run with JAR:
java -jar -Dspring.profiles.active=mssql cloud-native.jar  ##For MS-SQL as the persistent store

java -jar -Dspring.profiles.active=mssql cloud-native.jar   ##For MS-SQL as the persistent store

OR

Step 2. Run using Dockerfile
docker build .
docker run -e 'spring.profiles.active=mssql' build_id  ##For MS-SQL as the persistent store
docker run -e 'spring.profiles.active=pgsql' build_id  ##For MS-SQL as the persistent store