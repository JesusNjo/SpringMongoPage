package MongoDBQuery.MongoDBQuery.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {

    @Id
    private String id;

    private String client;

    private String product;

    private Integer quantity;
}
