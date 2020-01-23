# Use Windows Services to ensure that the Rabbitmq service is started!

start /D microservice-parent  mvn -q spring-boot:run
start /D config-server		  mvn -q spring-boot:run
start /D discovery-service 	  mvn -q spring-boot:run
start /D AuthServer 		  mvn -q spring-boot:run
start /D organ-service 		  mvn -q spring-boot:run
start /D slide-service 		  mvn -q spring-boot:run
 