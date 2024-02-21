package MongoDBQuery.MongoDBQuery.models.querys;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor

public class ClientQuery {

    @Getter @Setter
    private String query;
}
