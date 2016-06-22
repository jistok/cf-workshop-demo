## CF Workshop Demo

### Introduction

This is demo app for CF workshops, written in Spring Boot.
It is intended to demonstrate some of the basic functionality of Pivotal
CF:

 * Pivotal CF target, login, and push
 * Pivotal CF environment variables
 * Pivotal CF service variables
 * Scaling, router and load balancing
 * Health management and application restart
 * RDBMS services and application auto-configuration

### Building, Packaging, and Deploying

#### To get the source code and build the JAR file


    git clone https://github.com/pivotal-cf-workshop/cf-workshop-demo

    mvn clean package

#### To run the application

The application is set to use an embedded H2 database in non-PaaS environments,
and to take advantage of Pivotal CF's auto-configuration for services.  To use
a MySQL Dev service in PCF, simply create and bind a service to the app and 
restage the app.  No additional configuration is necessary when running locally 
or in Pivotal CF.

In Pivotal CF, it is assumed that a MySQL service will be used.

