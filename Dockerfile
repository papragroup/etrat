From openjdk:8
copy ./target/etrat-0.0.1-SNAPSHOT.jar etrat.jar
CMD ["java","-jar","etrat.jar"]
