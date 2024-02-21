package MongoDBQuery.MongoDBQuery.service.client.queryHandle;

import MongoDBQuery.MongoDBQuery.models.Client;
import MongoDBQuery.MongoDBQuery.models.querys.ClientQuery;
import MongoDBQuery.MongoDBQuery.pagin.Pagination;
import org.springframework.data.domain.Page;


public interface IClientQueryHandlerService {

    public Page<Client> handleQuery(String query, Pagination pagination);
}
