package raj.azure.archieve.servicebus;

import com.microsoft.azure.servicebus.Message;
import com.microsoft.azure.servicebus.QueueClient;

import java.time.Duration;
import java.util.UUID;

public class ServiceBusQueueHandler<T> implements ServiceBusQueueService<T> {

  QueueClient queueClient;

  public void sendMessage() {}

  private Message prepareMessage() {
    Message message = new Message("Insert your message here");
    message.setContentType("test/plain");
    message.setLabel(UUID.randomUUID().toString());
    message.setMessageId(UUID.randomUUID().toString());
    message.setTimeToLive(Duration.ofMinutes(10));
    return message;
  }
}
