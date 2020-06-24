FROM openjdk:14

ENV ENVIRONMENT=prod

MAINTAINER Name <email@mail.de>

ADD project-planning/target/app.jar app.jar

CMD [ "sh", "-c", "java -Dserver.port=$PORT -jar /app.jar" ]