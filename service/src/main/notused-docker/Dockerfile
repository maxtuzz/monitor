FROM rbygrave/java11-base

#VOLUME /var/log/java
USER app:app

WORKDIR /app
COPY lib /app/lib
COPY app.jar /app/app.jar

#COPY target/lib /app/lib
#COPY target/app.jar /app/app.jar

# For local docker build
#COPY application.yml /app/application.yml
#EXPOSE 8090

ENV JAVA_OPTS="-Dwas2=her2e"

ENTRYPOINT /sbin/tini -- java ${JAVA_OPTS} -jar app.jar

