Implementation for the https://bitbucket.org/Insomnium/netflix-oss-homework/src/master/ with additional features

## Startup
To generate docker images, run ***./mvnw clean package jib:dockerBuild*** or you can run ***./gradlew clean build jibDockerBuild*** 
To run this example, run ***docker-compose up -d***. To stop all running services, run ***docker-compose stop***.

> [!NOTE]
> Sometimes elk cannot start due to lack of CPU time while all other services are starting. You can simply start the elk ***docker start elk_5601*** when everything else will be ready.

> [!WARNING]
> hosts for the services now is 10.0.2.15, you can change it in the ***.env** files in the **./env** folder

## Links

### Services

#### Business services
Workspaces Api №1 - ***http://localhost:9090/manage/mappings*** 
Workspaces Api №2 - ***http://localhost:9091/manage/mappings***
Employees Api - ***http://localhost:9093/manage/mappings***

#### Support Services
Api Gateway - ***http://localhost:9094/manage/mappings***
Eureka - ***http://localhost:8282/***
ConfigServer - ***http://localhost:8888/manage/health***

### Infrastructure
Kibana - ******
Elasticsearch - ******
Logstash - ******
PostgreSQL - ******
Zookeeper - ******
Kafka - ******
Hystrix Dashboard - ******
Zipkin Dashboard - ******

