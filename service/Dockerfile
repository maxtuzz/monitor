FROM rbygrave/java11-base

#VOLUME /var/log/java
USER app:app

WORKDIR /app
ADD target/lib /app/lib
ADD target/app.jar /app/app.jar

# For local docker build
#COPY application.yml /app/application.yml
#EXPOSE 8090

ENV JAVA_OPTS="-Dwas=here"

ENTRYPOINT /sbin/tini -- java ${JAVA_OPTS} -jar app.jar

