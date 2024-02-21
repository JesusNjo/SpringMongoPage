package MongoDBQuery.MongoDBQuery.models.aggregates;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientOrderInfo {

    private String clientName;
    private String productName;
    private Integer quantity;
    private Double totalToPay;
}
