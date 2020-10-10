package raj.azure.archieve.servicebus;

import com.microsoft.azure.servicebus.QueueClient;
import com.microsoft.azure.servicebus.ReceiveMode;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;

public class AzureServiceBusClient {
  private String serviceBusNamespaceConnectionString;
  private String queueName;
  private QueueClient queueClient;

  public AzureServiceBusClient(QueueClient queueClient) {
    this.queueClient = queueClient;
  }

  public AzureServiceBusClient(String serviceBusNamespaceConnectionString, String queueName) {
    this.serviceBusNamespaceConnectionString = serviceBusNamespaceConnectionString;
    this.queueName = queueName;
  }

  // Handle ServiceBusException and InterruptedException inside class where you need to call
  // createQueueClient
  public QueueClient createQueueClient() throws ServiceBusException, InterruptedException {

    if (queueClient == null) {
      queueClient =
          new QueueClient(
              new ConnectionStringBuilder(serviceBusNamespaceConnectionString, queueName),
              ReceiveMode.PEEKLOCK);
    }
    return queueClient;
  }
}
