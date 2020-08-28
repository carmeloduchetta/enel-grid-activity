FROM amazonlinux:latest
RUN echo 'proxy=http://proxy-aws.risorse.enel:8080' >> /etc/yum.conf
RUN yum -y install java-1.8.0
EXPOSE 8090
ADD ga_modelentity-0.0.1-SNAPSHOT.jar ga_modelentity-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","ga_modelentity-0.0.1-SNAPSHOT.jar"]
