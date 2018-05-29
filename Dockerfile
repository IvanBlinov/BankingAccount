FROM maven:3.5-jdk-8-onbuild
CMD ["java","-jar","target/agileengine-1.0.jar"]