#!/usr/bin/env bash

#
# Sets the docker daemon context to be that of the minikube instance and then pulls
# all required images.
#
eval $(minikube -p minikube docker-env)
for i in v1 v2 v3; do
  docker pull quay.io/rblake/microservice:$i
  docker pull quay.io/rblake/microservice-db:$i
done