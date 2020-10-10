## How to debug Azure function using IntelliJ ##
## Commands to Run Azure Functions ##

### Command to deploy to Azure portal ###
- mvn install package azure-functions:deploy
    
### Command to run Azure function locally    
- mvn package azure-functions:run

### Command to debug locally ###
- mvn package azure-functions:run -DenableDebug
    
    
    

## References ##
Link: https://stackoverflow.com/questions/21114066/attach-intellij-idea-debugger-to-a-running-java-process