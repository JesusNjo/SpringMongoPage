package MongoDBQuery.MongoDBQuery.repository.client;

import MongoDBQuery.MongoDBQuery.models.Client;
import MongoDBQuery.MongoDBQuery.models.aggregates.ClientOrderInfo;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ClientRepository extends MongoRepository<Client,String> {

    @Aggregation({
            "{$lookup: {from: 'orders', localField: 'name', foreignField: 'client', as: 'order_info'}}",
            "{$unwind: '$order_info'}",
            "{$lookup: {from: 'products', localField: 'order_info.product', foreignField: 'name', as: 'product_info'}}",
            "{$unwind: '$product_info'}",
            "{$project: {clientName: $name, productName: '$product_info.name', quantity: '$order_info.quantity', totalToPay: {$multiply: ['$order_info.quantity', '$product_info.price']}}}"
    })
    List<ClientOrderInfo> getInfoByProduct();

    @Aggregation({
            "{$match: {_id: ?0}}"
    })
    Client getClientById(String clientId);


    @Aggregation(pipeline = {
            "{$sort: { ?3 : ?2 }}",
            "{ $skip: ?0 }",
            "{ $limit: ?1 }"
    })
    List<Client> findAllClientsWithPagination(int skip, int limit, int direction, String property);


    @Aggregation(pipeline = {
            "{$count: 'totalClients'}"

    })
    Integer countTotalClients();



}
