package MongoDBQuery.MongoDBQuery.repository.order;

import MongoDBQuery.MongoDBQuery.models.Order;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order,String> {

    @Aggregation({
            "{$match : {_id: ?0}}"
    })
    Order getOrderById(String _id);

    @Aggregation({
            "{$addFields: { client0Id: { $toObjectId: ?0 } }}",
            "{$lookup:{from:'clients', localField:'client0id', foreignField:'name', as: client_info}",
            "{unwind: '$client_info'}"
    })
    Order lookingForByParameter(String param);
}
