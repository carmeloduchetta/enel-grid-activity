#!/bin/bash
echo "executing build_script.sh"

echo "Who Am I:"
whoami

echo "I'm in"
echo "pwd"
pwd

echo "what is inside the folder:"
ls -alp

#mvn package -f src/docker_helloworld
#cp src/docker_helloworld/target/HelloWorld-1.0.jar container_orchestration/containers/docker_helloworld

mvn package -f src/dossier -DskipTests
cp src/dossier/target/ga_dossier-0.0.1-SNAPSHOT.jar container_orchestration/containers/dossier

#Add more container to build here
#mvn package -f src/docker_helloworld2
#cp src/docker_helloworld2/target/HelloWorld-1.0.jar container_orchestration/containers/docker_helloworld2