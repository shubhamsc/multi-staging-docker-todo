FROM gradle:jre-alpine as packagingstage

WORKDIR /app

COPY ./src build.gradle  ./

CMD ["gradle", "clean","build"]


FROM java:jre-alpine

WORKDIR /application

COPY --from=packagingstage /app/build/libs/apiserver-0.0.1-SNAPSHOT.jar .

ENTRYPOINT ["java","-jar", "apiserver-0.0.1-SNAPSHOT.jar"]

