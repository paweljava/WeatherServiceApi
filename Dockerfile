FROM openjdk:18.0.2.1-jdk-buster
ADD build/libs/WeatherServiceApi-0.0.1-SNAPSHOT.jar .
EXPOSE 8000
CMD java -jar WeatherServiceApi-0.0.1-SNAPSHOT.jar