
if [ -z "$DOCKER_ACCOUNT" ]; then
    DOCKER_ACCOUNT=dockerhub.airamatrix.in:50000
fi;
docker build --tag=auth-server AuthServer
docker tag auth-server $DOCKER_ACCOUNT/auth-server:latest
docker push $DOCKER_ACCOUNT/auth-server

docker build --tag=discovery-service discovery-service
docker tag discovery-service $DOCKER_ACCOUNT/discovery-service:latest
docker push $DOCKER_ACCOUNT/discovery-service

docker build --tag=config-server config-server
docker tag config-server $DOCKER_ACCOUNT/config-server:latest
docker push $DOCKER_ACCOUNT/config-server

docker build --tag=organ-service organ-service
docker tag organ-service $DOCKER_ACCOUNT/organ-service:latest
docker push $DOCKER_ACCOUNT/organ-service

docker build --tag=slide-service slide-service
docker tag slide-service $DOCKER_ACCOUNT/slide-service:latest
docker push $DOCKER_ACCOUNT/slide-service



