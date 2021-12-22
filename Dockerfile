FROM adoptopenjdk/openjdk11:debian-slim
RUN mkdir /opt/app
COPY ./build/libs/application.jar /opt/application.jar
CMD ["java", "-jar", "/opt/application.jar"]
