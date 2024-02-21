package MongoDBQuery.MongoDBQuery.pagin;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@Data
@NoArgsConstructor
public class Pagination {

    private int size;
    private int page;
    private Sort.Direction direction;
    private String[] properties;
 }
