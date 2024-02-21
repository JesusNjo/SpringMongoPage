package MongoDBQuery.MongoDBQuery.models.aggregates;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductOrderInfo {


    private String productName;
    private String clientName;
    private Integer quantity;
    private Double totalToPay;
}
