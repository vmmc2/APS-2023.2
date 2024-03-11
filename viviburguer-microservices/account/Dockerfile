FROM public.ecr.aws/amazoncorretto/amazoncorretto:21-al2023-headless
COPY /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-server","-jar","/app.jar"]
