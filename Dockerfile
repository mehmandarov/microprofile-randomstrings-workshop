FROM maven:3.8.1-jdk-11 as builder
COPY src /home/app/src
COPY pom.xml /home/app
WORKDIR /home/app
RUN mvn package -e -B -DskipLibertyPackage=true

FROM open-liberty:21.0.0.3-kernel-slim-java11-openj9 as runner
COPY --from=builder --chown=1001:0 /home/app/src/main/liberty/config/server.xml /config
RUN features.sh
COPY --from=builder --chown=1001:0 /home/app/target/randomstrings.war /config/dropins
RUN configure.sh