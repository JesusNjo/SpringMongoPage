package MongoDBQuery.MongoDBQuery.repository.product;

import MongoDBQuery.MongoDBQuery.models.Client;
import MongoDBQuery.MongoDBQuery.models.Product;
import MongoDBQuery.MongoDBQuery.models.aggregates.ProductOrderInfo;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {


    @Aggregation({
            "{$lookup:{from:'orders',localField:'name',foreignField:'product',as:'order_info'}}",
            "{$unwind: '$order_info'}",
            "{$lookup: {from:'clients', localField:'order_info.client',foreignField:'name',as:'client_info'}}",
            "{$unwind: '$client_info'}",
            "{$project: {productName: '$name', clientName: '$client_info.name', quantity: '$order_info.quantity', totalToPay: {$multiply:['$order_info.quantity', '$price']}}}"
    })
    List<ProductOrderInfo> getInfoByProduct();

    @Aggregation({
            "{$match: {_id: ?0}}"
    })
    Product findProductById(String id);

    @Aggregation(pipeline = {
            "{$sort: { ?3 : ?2 }}",
            "{ $skip: ?0 }",
            "{ $limit: ?1 }"
    })
    List<Product> findAllProductsWithPagination(int skip, int limit, int direction, String property);

    @Aggregation({
            "{$count : totalProducts}"
    })
    Integer countTotalProducts();
}
