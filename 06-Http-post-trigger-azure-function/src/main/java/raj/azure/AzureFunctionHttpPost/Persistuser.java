package raj.azure.AzureFunctionHttpPost;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;

import raj.azure.models.User;

import com.microsoft.azure.functions.*;

/**
 * Azure Functions with HTTP Trigger.
 */
public class Persistuser {
    /**
     * This function listens at endpoint "/api/HttpExample". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpExample
     * 2. curl "{your host}/api/HttpExample?name=HTTP%20Query"
     */
    @FunctionName("storeuser")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<User>> request,
            @TableOutput(name="userrecord", partitionKey="partitionKey", rowKey="rowKey", tableName="storeuser", connection="AzureWebJobsStorage") OutputBinding<User> userOutput,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request to create a user");

        User user=request.getBody().get();
        userOutput.setValue(user);
        
        
       if(user == null) {
    	 return  request.createResponseBuilder(HttpStatus.BAD_REQUEST)
    		   .body("Please pass a user")
    		   .build();
       }else
       {
    	  return request.createResponseBuilder(HttpStatus.OK)
    	   .header("Content-Type","application/json")
		   .body("Hello"+user)
		   .build();
       }
    }
}
