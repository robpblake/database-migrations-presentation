# Modern Database Migrations

This Git repository contains source code and example configurations for local development of database migrations using
[Flyway](https://flywaydb.org). It additionally contains Kustomize definitions for deploying a small microservice to a
Kubernetes cluster. For the sake of simplicity, it is recommended you use Minikube as your K8S cluster.

## Setup

This demo is designed to be operated on a Unix based system e.g. Ubuntu, MacOS, Fedora etc. It has not been tested on
Windows.

### Install Docker & Docker Compose

You will need to install [Docker](https://docker.com) and [Docker Compose](https://docs.docker.com/compose/) for your O/S. The most recent of either will be fine.

### Install MiniKube

Follow the instructions to install [MiniKube](https://minikube.sigs.k8s.io/docs/start/) for your environment. In addition, you will need the following commands
to ensure there is sufficient memory/cpu for Minikube and also that you have the correct addons installed:

```shell script
minikube config set cpu 4
minikube config set memory 8192
minikube start

minikube addons enable dashboard
minikube addons enable ingress
```

### Install Kustomize

Follow the instructions to install [Kustomize](https://kubernetes-sigs.github.io/kustomize/installation/) for your O/S.

### Add alias for api.local to your /etc/hosts file

Finally, add an alias in `/etc/hosts` for your Minikube install with the following:

```shell script
echo "$(minikube ip) api.local" >> /etc/hosts
```

### Java

If you wish to work with or extend the Microservice deployed as part of this example, then you will additionally
require Java 11 to be installed. 
 
## Local Development of Database Migrations

To write new migrations, simply place them into the `sql/migrations` directory using the naming structures supported by
FlyWay.

Once you have a new migration to test, first bring the database on the local machine using:

```shell script
docker-compose up -d db
```

If this is the first time you are running the command, Docker will first pull the MariaDB container image and then start
the database.

To instruct FlyWay to run your migration, use the following command:

```shell script
docker-compose run --rm migrate
```

You can then connect to the database using the following command to see the results of your migration:

```shell script
docker-compose exec db mysql -uroot -ppassword database-migrations
```

This will provide you with a terminal connection to the database using a MariaDB client.

If you wish to start again, simply execute `docker-compose down -v` to completely destroy the database and start the
process again.

## Running the CI pipeline

This example repo uses [GitHub Actions](https://github.com/features/actions) to execute a very simple CI pipeline that verifies any new database migrations are
working as expected.

The CI pipeline is defined within the [.github](.github) directory of this repo. By forking this repository and then
raising a PR against your fork, you will be able to see the CI pipeline in action (as long as the repository remains public).

Each time your raise a PR, the CI pipeline will:

* Label the pr `database-migration`
* Add the user `robpblake` as a DBA reviewer
* Execute the new migration against a clean database


## Running the Microservice

There are three branches on this repository, supporting various updates to the microservice. The branches are:

* `main` - v1 of the microservice
* `apiv2` - v2 of the microservice
* `apiv3` - v3 of the microservice

Each branch builds upon the previous version by adding features to the microservice in terms of how the data model is
handled. However `v2` and `v3` are backwards compatible with `v1` meaning that it is possible to roll the service back
without losing data.

Firstly you should deploy the database to your minikube cluster:

```
kustomize build k8s/overlays/db | kubectl apply -f -
```

This will create the namespace `my-app` and in that deploy a MariaDB instance. You can connect to the database and
ensure that it is running correctly with the following:

```
kubectl exec -it deploy/db -n my-app -- mysql -uroot -ppassword
```

To deploy `v1` of the API, run the following from `main` branch of this repo:

```shell script
kustomize build k8s/overlays/apiv1 | kubectl apply -f -
```

You will then be able to hit the API at [http://api.local/v1/people]()

For `v2`, use the following:

```shell script
git checkout apiv2
kustomize build k8s/overlays/apiv2 | kubectl apply -f -
```

You can then hit `v2` of the API at [http://api.local/v2/people]()

Finally, for `v3`, use the following:

```shell script
git checkout apiv3
kustomize build k8s/overlays/apiv3 | kubectl apply -f -
```

You will then be able to hit `v3` of the API at [http://api.local/v3/people]()







