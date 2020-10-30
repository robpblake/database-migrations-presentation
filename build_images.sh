#!/usr/bin/env bash
set -ex

mvn clean package -Dquarkus.package.type=fast-jar
docker build -f src/main/docker/Dockerfile.fast-jar -t quay.io/rblake/microservice:$1 .
docker push quay.io/rblake/microservice:$1

docker build -f sql/Dockerfile -t quay.io/rblake/microservice-db:$1 sql/
docker push quay.io/rblake/microservice-db:$1
