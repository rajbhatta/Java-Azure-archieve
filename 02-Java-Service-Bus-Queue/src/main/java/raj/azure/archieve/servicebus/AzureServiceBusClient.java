package raj.azure.archieve.servicebus;

import com.microsoft.azure.servicebus.Message;
import com.microsoft.azure.servicebus.QueueClient;
import com.microsoft.azure.servicebus.ReceiveMode;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import raj.azure.archieve.exception.AzureServiceBusException;

public class AzureServiceBusClient {
  private String serviceBusNamespaceConnectionString;
  private String queueName;
  private QueueClient queueClient;

  // This helps to perform unit testing
  public AzureServiceBusClient(QueueClient queueClient) {
    this.queueClient = queueClient;
  }

  public AzureServiceBusClient(String serviceBusNamespaceConnectionString, String queueName) {
    this.serviceBusNamespaceConnectionString = serviceBusNamespaceConnectionString;
    this.queueName = queueName;
  }

  public QueueClient createQueueClient() throws AzureServiceBusException {
    if (queueClient == null) {
      try {
        queueClient =
            new QueueClient(
                new ConnectionStringBuilder(serviceBusNamespaceConnectionString, queueName),
                ReceiveMode.PEEKLOCK);
      } catch (InterruptedException e) {
        throw new AzureServiceBusException("InterruptedException", e);
      } catch (ServiceBusException e) {
        throw new AzureServiceBusException("ServiceBusException", e);
      }
    }
    return queueClient;
  }

  public void sendMessage(Message message) {
    queueClient.sendAsync(message);
  }

  public void closeConnection() throws AzureServiceBusException {
    if (queueClient != null) {
      try {
        queueClient.close();
      } catch (ServiceBusException e) {
        throw new AzureServiceBusException("Error closing service bus connection", e);
      }
    }
  }
}
