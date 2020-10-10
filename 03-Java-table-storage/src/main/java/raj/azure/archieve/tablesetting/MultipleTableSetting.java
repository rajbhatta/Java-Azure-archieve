package raj.azure.archieve.tablesetting;

public class MultipleTableSetting implements TableSetting {
  @Override
  public String provideTableStorageConnectionString() {
    return System.getenv("TABLE-STORAGE-CONNECTION-STRING");
  }

  @Override
  public String provideFirstTableName() {
    return System.getenv("FIRST-TABLE-NAME");
  }

  @Override
  public String provideSecondTableName() {
    return System.getenv("SECOND-TABLE-NAME");
  }
}
