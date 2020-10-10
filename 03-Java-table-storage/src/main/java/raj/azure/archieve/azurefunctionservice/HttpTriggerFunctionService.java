package raj.azure.archieve.azurefunctionservice;

import com.microsoft.azure.functions.ExecutionContext;
import raj.azure.archieve.exception.AzureServiceBusException;
import raj.azure.archieve.exception.ServiceBusHandlerException;
import raj.azure.archieve.model.Student;
import raj.azure.archieve.tablesetting.TableSetting;

public class HttpTriggerFunctionService {
  ExecutionContext executionContext;
  TableSetting tableSetting;

  public HttpTriggerFunctionService(
      ExecutionContext executionContext, TableSetting tableSetting) {
    this.executionContext = executionContext;
    this.tableSetting = tableSetting;
  }

  public void sendMessageToQueue(Student student) throws AzureServiceBusException, ServiceBusHandlerException {
    String queueName = student.getQueueName();
    QueueService<Student> studentQueueService = provideStudentQueueService(queueName);
    studentQueueService.sendMessage(student);
  }

  private AzureServiceBusClient azureServiceBusClient(String queueName) {
    return new AzureServiceBusClient(tableSetting.provideServiceBusString(), queueName);
  }

  private QueueService<Student> provideStudentQueueService(String queueName) {
    return new QueueServiceImpl<>(azureServiceBusClient(queueName));
  }
}
