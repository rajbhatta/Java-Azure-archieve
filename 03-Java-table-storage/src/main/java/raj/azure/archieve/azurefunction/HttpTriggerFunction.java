package raj.azure.archieve.azurefunction;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import raj.azure.archieve.azurefunctionservice.HttpTriggerFunctionService;
import raj.azure.archieve.exception.AzureServiceBusException;
import raj.azure.archieve.exception.ServiceBusHandlerException;
import raj.azure.archieve.model.Student;
import raj.azure.archieve.queuesetting.MultipleQueueSetting;
import raj.azure.archieve.queuesetting.QueueSetting;

import java.util.Optional;

/** Azure Functions with HTTP Trigger. */
public class HttpTriggerFunction {
  /**
   * This function listens at endpoint "/api/HttpExample". Two ways to invoke it using "curl"
   * command in bash: 1. curl -d "HTTP Body" {your host}/api/HttpExample 2. curl "{your
   * host}/api/HttpExample?name=HTTP%20Query"
   */
  @FunctionName("createuser")
  public HttpResponseMessage run(
      @HttpTrigger(
              name = "req",
              methods = {HttpMethod.POST},
              authLevel = AuthorizationLevel.ANONYMOUS)
          HttpRequestMessage<Optional<Student>> request,
      final ExecutionContext context) throws AzureServiceBusException, ServiceBusHandlerException {
    context.getLogger().info("Java HTTP trigger processed a request to create a user");

    Student student = request.getBody().get();

    if (student == null) {
      return request
          .createResponseBuilder(HttpStatus.BAD_REQUEST)
          .body("Please provider student info")
          .build();
    } else {

      HttpTriggerFunctionService httpTriggerFunctionService= provideHttpTriggerFunctionService(context, student);
      httpTriggerFunctionService.sendMessageToQueue(student);

      return request
          .createResponseBuilder(HttpStatus.OK)
          .body("Hello: \t" + student.getFirstName())
          .build();
    }
  }

  private HttpTriggerFunctionService provideHttpTriggerFunctionService(
      ExecutionContext executionContext, Student student) {
    return new HttpTriggerFunctionService(executionContext,provideQueueSetting());
  }

  private QueueSetting provideQueueSetting() {
    return new MultipleQueueSetting();
  }
}
