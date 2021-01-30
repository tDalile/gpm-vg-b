FROM camunda/camunda-bpm-platform:tomcat-latest
## timezone
ENV TZ=Europe/Berlin

## Remove Tomcat example applications
## see also https://tomcat.apache.org/tomcat-9.0-doc/security-howto.html
RUN rm -fr /camunda/webapps/camunda-invoice
#           /camunda/webapps/examples \
#            /camunda/webapps/ROOT \
#            /camunda/webapps/docs \
#            /camunda/webapps/host-manager \
#            /camunda/webapps/manager \
# remove Camunda example applications
#           /camunda/webapps/camunda-welcome \
#           /camunda/webapps/engine-rest \
#           /camunda/webapps/h2


#USER camunda
ADD target/vgb.war  /camunda/webapps/vgb.war
ADD tomcat/tomcat-users.xml camunda/conf/tomcat-users.xml
ADD tomcat/context.xml  camunda/webapps/manager/META-INF/context.xml
#EXPOSE 8080:8080

#CMD ["./camunda.sh"]
#ENTRYPOINT ["./camunda.sh"]
