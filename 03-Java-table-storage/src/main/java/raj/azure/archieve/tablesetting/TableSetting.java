package raj.azure.archieve.tablesetting;

public interface TableSetting {
  String provideTableStorageConnectionString();

  String provideFirstTableName();

  String provideSecondTableName();
}
