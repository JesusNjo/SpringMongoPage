package MongoDBQuery.MongoDBQuery.service.client.queryHandle;

import MongoDBQuery.MongoDBQuery.controller.querys.ClientQueryHandler;
import MongoDBQuery.MongoDBQuery.models.Client;
import MongoDBQuery.MongoDBQuery.models.querys.ClientQuery;
import MongoDBQuery.MongoDBQuery.pagin.Pagination;
import MongoDBQuery.MongoDBQuery.repository.client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("ClientQueryHandlerServiceImpl")
public class ClientQueryHandlerServiceImpl implements IClientQueryHandlerService{

    @Autowired
    private ClientRepository clientRepository;
    @Override
    public Page<Client> handleQuery(String query, Pagination pagination) {
        List<Client> clients = clientRepository.searchClientByQuery(query,
                pagination.getProperties()[0],
                pagination.getDirection() == Sort.Direction.ASC ?1 : -1,
                pagination.getPage(),
                pagination.getSize()
        );
        return new PageImpl<>(clients, PageRequest.of(pagination.getPage(), pagination.getSize()),clients.size());
    }
}
