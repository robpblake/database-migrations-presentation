# Modern Database Migrations

This Git repository contains source code and example configurations for local development of database migrations using
[Flyway](https://flyway.org). It additionally contains Kustomize definitions for deploying a small microservice to a
Kubernetes cluster. For the sake of simplicity, it is recommended you use Minikube as your K8S cluster.

## Setup

This demo is designed to be operated on a Unix based system e.g. Ubuntu, MacOS, Fedora etc. It has not been tested on
Windows.

### Install Docker & Docker Compose

You will need to install Docker and Docker Compose for your O/S. The most recent of either will be fine.

### Install MiniKube

Follow the instructions to install MiniKube for your environment. In addition, you will need the following commands
to ensure there is sufficient memory/cpu for Minikube and also that you have the correct addons installed:

```shell script
minikube config set cpu 4
minikube config set memory 8192
minikube start

minikube addons enable dashboard
minikube addons enable ingress
```

### Install Kustomize

Follow the instructions to install Kustomize for your O/S.

### Add alias for api.local to your /etc/hosts file

Finally, add an alias in `/etc/hosts` for your Minikube install with the following:

```shell script
echo "$(minikube ip) api.local" >> /etc/hosts
```
 




