package raj.azure.archieve.queuesetting;

public class MultipleQueueSetting implements QueueSetting {
  @Override
  public String provideServiceBusString() {
    return System.getenv("SERVICE-BUS-CONNECTION-STRING");
  }

  @Override
  public String provideFirstQueName() {
    return System.getenv("FIRST-QUEUE-NAME");
  }

  @Override
  public String provideSecondQueueName() {
    return System.getenv("SECOND-QUEUE-NAME");
  }
}
