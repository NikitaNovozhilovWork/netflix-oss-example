Implementation for the https://bitbucket.org/Insomnium/netflix-oss-homework/src/master/ with additional features

Startup
-------

To generate docker images, run ***./mvnw clean package jib:dockerBuild*** or you can run ***./gradlew clean build jibDockerBuild*** 

To run this example, run ***docker-compose up -d***. To stop all running services, run ***docker-compose stop***.

> [!NOTE]
Sometimes elk cannot start due to lack of CPU time while all other services are starting. You can simply start the elk ***docker start elk_5601*** when everything else will be ready.

> [!WARNING]
hosts for the services now is 10.0.2.15, you can change it in the ***.env** files in the **./env** folder

Links
-------

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

Kibana - ***http://localhost:5601/app/kibana#/home?_g=()***

Elasticsearch - ***http://localhost:9200/***

Logstash - ***localhost:5044***

PostgreSQL - ***localhost:5432***

Zookeeper - ***localhost:2181***

Kafka - ***localhost:9092***

Hystrix Dashboard - ***http://localhost:7979/hystrix-dashboard/***

Zipkin Dashboard - ***http://localhost:9411/zipkin/***

### Service links

#### Employees Api

Get full employee info - *curl -X GET http://localhost:9093/employees/0000001*

Get employee info - *curl -X GET -H "Content-Type: application/json " http://localhost:9093/integration/employees/0000001*

Add new employee - *curl -X POST -H "Content-Type: application/json" -d '{"id":"1000001","firstName":"FirstName","lastName":"LastName","email":"email@email.com","workspaceId":"1000001"}' http://localhost:9093/integration/employees/* or *curl -X PUT -H "Content-Type: application/json" -d '{"id":"1000001","firstName":"FirstName","lastName":"LastName","email":"email@email.com","workspaceId":"1000001"}' http://localhost:9093/integration/employees/1000001*

Manually refresh info about employees workplaces from csv files in */data/employees/* directory - *curl -X GET -H "Content-Type: application/json" http://lhost:9093/integration/employees/refresh*

#### Workspaces Api 

Get workspace info - *http://localhost:9091/workspaces/0000001 or http://localhost:9090/workspaces/0000001*

Add new workspace - *curl -X POST -H "Content-Type: application/json" -d '{"id":"1000001","unit":100,"seat":100,"serialNumber":"Serial #","osFamily":"LINUX"}' http://localhost:9091/workspaces* or *curl -X PUT -H "Content-Type: application/json" -d '{"id":"1000001","unit":100,"seat":100,"serialNumber":"Serial #","osFamily":"LINUX"}' http://localhost:9090/workspaces/1000001*

#### Api Gateway

Call workspaces api through gateway - ___http://localhost:9094/workspaces-service/___workspaces/0000001

Call employees api through gateway - ___http://localhost:9094/employees-service/___employees/0000001