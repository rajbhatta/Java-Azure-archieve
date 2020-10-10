package raj.azure.archieve.functions;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import raj.azure.archieve.model.Student;

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
      final ExecutionContext context) {
    context.getLogger().info("Java HTTP trigger processed a request to create a user");

    Student student = request.getBody().get();

    if (student == null) {
      return request
          .createResponseBuilder(HttpStatus.BAD_REQUEST)
          .body("Please provider student info")
          .build();
    } else {
      return request
          .createResponseBuilder(HttpStatus.OK)
          .body("Hello: \t" + student.getFirstName())
          .build();
    }
  }
}
