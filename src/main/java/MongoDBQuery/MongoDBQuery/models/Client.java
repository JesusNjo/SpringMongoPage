package MongoDBQuery.MongoDBQuery.models;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clients")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client {

    @Id
    private String id;
    @Size(max = 15)
    private String name;

    private Long age;

    private String city;
}
