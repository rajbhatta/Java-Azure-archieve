package cosmos.db.util;

import com.azure.cosmos.*;
import com.azure.cosmos.models.CosmosContainerProperties;
import com.azure.cosmos.models.CosmosContainerResponse;
import com.azure.cosmos.models.CosmosDatabaseResponse;
import com.azure.cosmos.models.ThroughputProperties;

import java.util.Collections;

public class CosmosDbHandler {

    private CosmosClient client=null;

    private final String databaseName = "SampleDb";
    private final String containerName = "SampleContainer";

    private CosmosDatabase database=null;
    private CosmosContainer container=null;

    private String hostName;
    private String hostKey;

    public CosmosDbHandler(String hostName, String hostKey) {
        this.hostName = hostName;
        this.hostKey = hostKey;
    }

    private void createClient() throws Exception {
        System.out.println("Using Azure Cosmos DB endpoint: " + hostName);

        client = new CosmosClientBuilder()
                .endpoint(hostName)
                .key(hostKey)
                .preferredRegions(Collections.singletonList("Canada Central"))
                .consistencyLevel(ConsistencyLevel.EVENTUAL)
                .buildClient();


        createDatabaseIfNotExists();
        createContainerIfNotExists();

    }

    private void createDatabaseIfNotExists() throws Exception {
        System.out.println("Create database " + databaseName + " if not exists.");
        CosmosDatabaseResponse cosmosDatabaseResponse = client.createDatabaseIfNotExists(databaseName);
        database = client.getDatabase(cosmosDatabaseResponse.getProperties().getId());
        System.out.println("Checking database " + database.getId() + " completed!\n");
    }

    private void createContainerIfNotExists() throws Exception {
        System.out.println("Create container " + containerName + " if not exists.");
        CosmosContainerProperties containerProperties =
                new CosmosContainerProperties(containerName, "/lastName");

        CosmosContainerResponse cosmosContainerResponse =
                database.createContainerIfNotExists(containerProperties, ThroughputProperties.createManualThroughput(400));
        container = database.getContainer(cosmosContainerResponse.getProperties().getId());

        System.out.println("Checking container " + container.getId() + " completed!\n");
    }

    public CosmosContainer getCosmosContainer() throws Exception {
        if(container==null){
            createClient();
            createContainerIfNotExists();
        }
        return container;
    }
}
