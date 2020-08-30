import com.azure.cosmos.CosmosContainer;
import com.azure.cosmos.models.CosmosItemRequestOptions;
import com.azure.cosmos.models.CosmosItemResponse;
import com.azure.cosmos.models.PartitionKey;
import cosmos.db.util.CosmosDbHandler;
import cosmos.db.util.CosmosDbProperties;
import model.Address;
import model.Customer;

import java.util.UUID;

public class CosmosDbNexusController {
    public static void main(String[] args) throws Exception {

        CosmosItemRequestOptions cosmosItemRequestOptions = new CosmosItemRequestOptions();

        //Get Cosmos DB container by injecting hostname and key
        CosmosDbHandler cosmosDbHandler=new CosmosDbHandler(CosmosDbProperties.AZURE_HOST_NAME,CosmosDbProperties.AZURE_KEY);
        CosmosContainer cosmosContainer= cosmosDbHandler.getCosmosContainer();


        //Set Customer
        Address address=new Address();
        address.setStreetName("101 St Nw. Edmonton");
        address.setPostalCode("M1ZM1KLLM");

        Customer customer=new Customer();
        customer.setFirstName(UUID.randomUUID().toString());
        customer.setAddress(address);



        CosmosItemResponse<Customer> item = cosmosContainer.createItem(customer, new PartitionKey(customer.getId()), cosmosItemRequestOptions);

        System.out.println(String.format("Created item with request charge of %.2f within" +
                        " duration %s",
                item.getRequestCharge(), item.getDuration()));

    }
}
