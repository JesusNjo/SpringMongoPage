package MongoDBQuery.MongoDBQuery.service.client;

import MongoDBQuery.MongoDBQuery.models.Client;
import MongoDBQuery.MongoDBQuery.pagin.Pagination;

import java.util.List;

public interface IClientService {

    public List<Client> getAllClient();
    public Client findClientById(String _id);
    public void deleteClientById(String _id);
    public List<?> findClientByPurchases();
    public List<Client> findClientByPagination(Pagination pagination);
    public Integer countTotalClient();
    public void createClient(Client client);
}
