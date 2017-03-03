## CF Workshop Demo

### Introduction

This is a demo app for CF workshops, written in Spring Boot.
It is intended to demonstrate some of the basic day-in-the-life features for developers in CF

 * Push / bind / scale
 * Accessing CF environment variables
 * Accessing CF service variables
 * Scaling, dynamic routing and load balancing
 * Health management and application restart
 * Binding services and application auto-configuration

### Building, Packaging, and Deploying

#### To get the source code and build the JAR file


    git clone https://github.com/pivotal-cf-workshop/cf-workshop-demo

    mvn clean package


#### To run the application

By default the application uses an embedded H2 database with some sample
Note data.

Run the application locally as a Spring Boot JAR for testing:

    java -jar target/cf-workshop-demo-<version>.jar

Running in CF is as usual too:

    cf push cf-workshop-demo -p target/cf-workshop-demo-<version>.jar

#### Sample demo scenarios

 * In a clean space, push the Boot JAR.  Discuss automatic route mapping, buildpack function, and health management during the process.
 * Display the home page, and point out environment variables displayed.  Discuss configuration via environment variables.
 * Go to the Notes list page, and discuss the embedded database and how we'll switch it to a MySQL database service.
 * Create a MySQL database service instance and bind it to the application.  In the CF CLI, restage the application and point out the auto reconfiguration and Maria DB JDBC driver injected.
 * Go back to the Notes page, and show that there's no sample data (because of Spring Boot behavior) and add a new record.
 * Go to the Environment page, and show the bound service.
 * Click the 'Kill' button, refresh the page, and show the app missing.  Wait ~10 seconds, refresh the page, and show that the app is back.  Discuss health monitoring and management.  Try doing this while tailing logs to show log aggregation too.
 * Scale the application to 3 instances.  Refresh the home page a few times and show the instance variables change due to automatic load balancing in the router.
