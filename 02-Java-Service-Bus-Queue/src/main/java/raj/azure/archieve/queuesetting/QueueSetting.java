package raj.azure.archieve.queuesetting;

public interface QueueSetting {
  String provideServiceBusString();

  String provideFirstQueName();

  String provideSecondQueueName();
}
