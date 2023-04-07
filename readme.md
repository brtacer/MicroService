## RabbitMQ DOCKER

    docker run -d --hostname my-rabbit --name some-rabbit -p 15672:15672 -p 5672:5672 -e RABBITMQ_DEFAULT_USER=user -e RABBITMQ_DEFAULT_PASS=root rabbitmq:3-management

## Docker İmage Oluşturmak
    
    docker build --build-arg JAR_FILE=ConfigServerGit/build/libs/ConfigServerGit-v.0.1.jar -t brtacer/java6configservergit:v.0.1 .
    
    docker build --build-arg JAR_FILE=AuthMicroService/build/libs/AuthMicroService-v.0.1.jar -t brtacer/java6auth:v.0.1 .
    docker build --build-arg JAR_FILE=UserProfile/build/libs/UserProfile-v.0.1.jar -t brtacer/java6user:v.0.1 .
    docker build --build-arg JAR_FILE=ApiGatewayService/build/libs/ApiGatewayService-v.0.1.jar -t brtacer/java6gateway:v.0.1 .

    