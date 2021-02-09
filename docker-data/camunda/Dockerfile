## Create intermediate maven image to run mvn pipeline
FROM maven:3.6.3-openjdk-15 as maven

# Copy necessary project data
COPY pom.xml ./pom.xml
COPY src ./src

# Run mvn pipeline
RUN mvn clean
RUN mvn compile
RUN mvn package

## Pull camunda image
FROM camunda/camunda-bpm-platform:tomcat-latest

# Set timezone
ENV TZ=Europe/Berlin

# Copy .war file created befor
COPY --from=maven target/vgb.war /camunda/webapps/vgb.war

#
COPY docker-data/camunda/tomcat/tomcat-users.xml camunda/conf/tomcat-users.xml
COPY docker-data/camunda/tomcat/context.xml camunda/webapps/manager/META-INF/context.xml

# Remove Tomcat example applications
RUN rm -fr /camunda/webapps/camunda-invoice \
           /camunda/webapps/examples \
            /camunda/webapps/ROOT \
            /camunda/webapps/docs \
            /camunda/webapps/host-manager \
            /camunda/webapps/manager \
# remove Camunda example applications
           /camunda/webapps/camunda-welcome \
#           /camunda/webapps/engine-rest \
           /camunda/webapps/h2


