package raj.azure.archieve.azurefunctionservice;

import com.microsoft.azure.functions.ExecutionContext;
import raj.azure.archieve.exception.AzureServiceBusException;
import raj.azure.archieve.exception.ServiceBusHandlerException;
import raj.azure.archieve.model.Student;
import raj.azure.archieve.queuesetting.QueueSetting;
import raj.azure.archieve.servicebus.AzureServiceBusClient;
import raj.azure.archieve.servicebus.QueueService;
import raj.azure.archieve.servicebus.QueueServiceImpl;

public class HttpTriggerFunctionService {
  ExecutionContext executionContext;
  QueueSetting queueSetting;

  public HttpTriggerFunctionService(
      ExecutionContext executionContext, QueueSetting queueSetting) {
    this.executionContext = executionContext;
    this.queueSetting = queueSetting;
  }

  public void sendMessageToQueue(Student student) throws AzureServiceBusException, ServiceBusHandlerException {
    String queueName = student.getQueueName();
    QueueService<Student> studentQueueService = provideStudentQueueService(queueName);
    studentQueueService.sendMessage(student);
  }

  private AzureServiceBusClient azureServiceBusClient(String queueName) {
    return new AzureServiceBusClient(queueSetting.provideServiceBusString(), queueName);
  }

  private QueueService<Student> provideStudentQueueService(String queueName) {
    return new QueueServiceImpl<>(azureServiceBusClient(queueName));
  }
}
