FROM maven:3-jdk-8-slim as builder
WORKDIR /app/test

COPY ./pom.xml /app/test/pom.xml

RUN mvn dependency:go-offline -B

COPY ./ /app/test


RUN mvn clean package

FROM openjdk:8-jre-slim
COPY --from=builder /app/test/target/test.jar test.jar
RUN  apt-get update \
  && apt-get install -y wget \
  && rm -rf /var/lib/apt/lists/*
RUN wget -O elastic-agent.jar 'http://repo1.maven.org/maven2/co/elastic/apm/elastic-apm-agent/1.6.0/elastic-apm-agent-1.6.0.jar'

EXPOSE 8051
#ENTRYPOINT ["java","-jar","communication-service.jar"]
#ENTRYPOINT ["java","-jar","communication-service.jar"]
ENTRYPOINT exec java $JAVA_OPTS -javaagent:elastic-agent.jar -jar test.jar
