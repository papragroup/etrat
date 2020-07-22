From openjdk:8
copy ./target/etrat.jar etrat.jar
CMD ["java","-jar","etrat.jar"]
