## How to handle Azure functions ##

### 1. To Deploy to Azure portal ###
- mvn clean install package azure-functions:deploy

### 2. To run locally ###
- mvn package azure-functions:run

### 3. To debug Azure function locally ###
- mvn package azure-functions:run - DenableDebug

## Running project locally using IntelliJ IDE ##
- goto terminal and type mvn package azure-functions:run
- You will have link as given below:
<img src="img/run-function.png"/>

## Sending data from postman to Azure function ##
<img src="img/http-post.png"/>


## How to create Azure Service Bus Namespace and get connection string? ##
- Goto Azure portal
- Opened resource group that you created. If you don't have resource group create one
- Click on Add -> search for service bus and click on create
- Once Servicebus namespace is created, click on shared access policies as given below:
<img src="img/create-namespace.png"/>

## How to create queue inside Azure Service Bus Namespace ##
- Go inside namespace that you created in above step
- click on add then you will have option as given below:
<img src="img/create-queue"/>