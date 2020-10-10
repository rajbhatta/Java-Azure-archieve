## How to handle Azure functions ##

### 1. To Deploy to Azure portal ###
- mvn clean install package azure-functions:deploy

### 1. To run locally ###
- mvn package azure-functions:run

### 1. To debug Azure function locally ###
- mvn package azure-functions:run - DenableDebug

## Running project locally using IntelliJ IDE ##
- goto terminal and type mvn package azure-functions:run
- You will have link as given below:
<img src="img/run-function.png"/>

## Sending data from postman to Azure function ##
<img src="img/http-post.png"/>