package MongoDBQuery.MongoDBQuery.repository.order;

import MongoDBQuery.MongoDBQuery.models.Order;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface OrderRepository extends MongoRepository<Order,String> {

    @Aggregation({
            "{$match : {_id: ?0}}"
    })
    Order getOrderById(String _id);

    @Aggregation(pipeline = {
            "{$addFields: {clientName: {$toString: '$client'}}}",
            "{$lookup: {from:'clients', localField:'clientName', foreignField: 'name',as:'client_info'}}",
            "{$unwind: '$client_info'}",
            "{$project: {clientName: '$client_info.name'}}"
    })
    Set<String> clientWithOrders();
}
