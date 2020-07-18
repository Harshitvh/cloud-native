from java:8-jdk-alpine
ENV spring.profiles.active = mssql
COPY /target/cloud-native.jar /usr/app/
WORKDIR /usr/app
RUN sh -c 'ls'
RUN sh -c 'touch cloud-native.jar'
ENTRYPOINT java -jar -Dserver.port=80 cloud-native.jar