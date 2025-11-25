#! /bin/sh

cd F:\\36\\36_microservices\\Euraka-Service
mvn compile jib:dockerBuild

cd F:\\36\\36_microservices\\accounts
mvn compile jib:dockerBuild

cd F:\\36\\36_microservices\\loans
mvn compile jib:dockerBuild

cd F:\\36\\36_microservices\\cards
mvn compile jib:dockerBuild

cd F:\\36\\36_microservices\\gatewayserver
mvn compile jib:dockerBuild
