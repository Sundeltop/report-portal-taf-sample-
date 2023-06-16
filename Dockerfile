FROM gradle:jdk17-alpine

RUN mkdir -p /opt/app
COPY . /opt/app

WORKDIR /opt/app

RUN gradle -no-daemon clean

CMD ["gradle","--no-daemon","test","-Dbase.url=http://192.168.0.4:8080","--tests","com.epam.api.tests.*"]