# Deployment

In the development and deployment apache-servicemix-7.0.1.zip version is used, and you can install it via the following link:

http://www.apache.org/dyn/closer.lua/servicemix/servicemix-7/7.0.1/apache-servicemix-7.0.1.zip

later on you can run the servicemix via the command "/apache-servicemix-7.0.1/bin/servicemix.bat". 

![alt text](test/img/servicemix-startup.png)

When it is initially started there are couple of additional features that needs to be installed that are ready to be installed but doesnt come as installed in the servicemix version. You can install them as follows;


Running on Karaf command line:

    feature:install webconsole
    feature:install camel-swagger-java
    feature:install camel-jackson
    feature:install camel-jetty9


Click on:
      
http://localhost:8181/system/console/bundles
       
This is the main bundle list view and where the stats-api is going to be installed. After a "mvn clean install" the jar file created should under your local maven directory as stated on the command line. 

Ex: 
      
      nl/stats/camel/stats-camel-api/1.0.0-SNAPSHOT/stats-camel-api-1.0.0-SNAPSHOT.jar

      
You can install the bundle via the the Install/Update button.

![alt text](test/img/bundle-install.png)


Later on you can check the deployment of the bundle being successful or checking the logs by "log:display" on karaf command line, also if the deployment is successful and the stats-api is up and running you can check the swagger documentation for the api via the link:

http://localhost:8585/stats-osgi/rest/api-docs/statsApi

You can copy paste the content of the swagger definition above and by pasting it on the online swagger editor you can have a more clear picture of the endpoints being implemented:

https://editor.swagger.io
      
![alt text](test/img/swagger-stats-def.png)
      
      
      
      
# Testing

There are implemented soapui tests under "/test/soap/" directory.
      