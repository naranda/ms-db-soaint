# Start with a base image containing Java runtime
FROM redhat-openjdk-18/openjdk18-openshift

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=target/ms-db-soaint-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} ms-db-soaint-0.0.1-SNAPSHOT.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/ms-db-soaint-0.0.1-SNAPSHOT.jar"]
