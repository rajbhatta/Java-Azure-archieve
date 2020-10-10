package raj.azure.archieve.servicebus;

import raj.azure.archieve.exception.AzureServiceBusException;
import raj.azure.archieve.exception.ServiceBusHandlerException;

public interface QueueService<T> {
  void sendMessage(T t) throws AzureServiceBusException, ServiceBusHandlerException;
}
