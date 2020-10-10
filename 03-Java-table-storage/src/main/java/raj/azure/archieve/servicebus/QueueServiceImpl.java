package raj.azure.archieve.servicebus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.servicebus.Message;
import com.microsoft.azure.servicebus.QueueClient;
import raj.azure.archieve.exception.AzureServiceBusException;
import raj.azure.archieve.exception.ServiceBusHandlerException;

import java.time.Duration;
import java.util.UUID;

public class QueueServiceImpl<T> implements QueueService<T> {

  AzureServiceBusClient azureServiceBusClient;

  public QueueServiceImpl(AzureServiceBusClient azureServiceBusClient) {
    this.azureServiceBusClient = azureServiceBusClient;
  }

  @Override
  public void sendMessage(T t) throws AzureServiceBusException, ServiceBusHandlerException {
    QueueClient queueClient = azureServiceBusClient.createQueueClient();
    Message message = prepareMessage(t);
    queueClient.sendAsync(message);
    azureServiceBusClient.closeConnection();
  }

  private Message prepareMessage(T t) throws ServiceBusHandlerException {
    try {
      String stringMessageBody = provideObjectMapper().writeValueAsString(t);
      Message message = new Message(stringMessageBody);
      message.setContentType("test/plain");
      message.setLabel(UUID.randomUUID().toString());
      message.setMessageId(UUID.randomUUID().toString());
      message.setTimeToLive(Duration.ofMinutes(10));
      return message;
    } catch (JsonProcessingException e) {
      throw new ServiceBusHandlerException("JsonProcessingException", e);
    }
  }

  private ObjectMapper provideObjectMapper() {
    return new ObjectMapper();
  }
}
