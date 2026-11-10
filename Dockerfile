FROM maven:3-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -B -ntp clean package

FROM tomcat:10.1-jdk17-temurin
RUN rm -rf /usr/local/tomcat/webapps/* \
    && sed -i 's/port="8080"/port="${http.port}"/' /usr/local/tomcat/conf/server.xml
COPY --from=build /app/target/chapter04-web.war /usr/local/tomcat/webapps/ROOT.war

ENV CATALINA_OPTS="-Xms64m -Xmx256m"
EXPOSE 8080
CMD ["sh", "-c", "export CATALINA_OPTS=\"$CATALINA_OPTS -Dhttp.port=${PORT:-8080}\"; exec catalina.sh run"]
