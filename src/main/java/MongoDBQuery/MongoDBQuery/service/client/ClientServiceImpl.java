package MongoDBQuery.MongoDBQuery.service.client;

import MongoDBQuery.MongoDBQuery.models.Client;
import MongoDBQuery.MongoDBQuery.pagin.Pagination;
import MongoDBQuery.MongoDBQuery.repository.client.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ClientServiceImpl")
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {
    private final ClientRepository clientRepository;
    @Override
    public List<Client> getAllClient() {
        return clientRepository.findAll();
    }

    @Override
    public Client findClientById(String _id) {
        return clientRepository.getClientById(_id);
    }

    @Override
    public void deleteClientById(String _id) {
        clientRepository.delete(findClientById(_id));
    }

    @Override
    public List<?> findClientByPurchases() {
        return clientRepository.getInfoByProduct();
    }

    @Override
    public List<Client> findClientByPagination(Pagination pagination) {
        return clientRepository.findAllClientsWithPagination(
                pagination.getPage(), pagination.getSize(),
                pagination.getDirection() == Sort.Direction.ASC? 1 : -1,
                pagination.getProperties()[0]);
    }
    @Override
    public Integer countTotalClient() {
        return clientRepository.countTotalClients();
    }

    @Override
    public void createClient(Client client) {
        client = Client.builder()
                .name(client.getName())
                .age(client.getAge())
                .city(client.getCity())
                .build();
        clientRepository.save(client);
    }
    @Override
    public Client findClientByName(String name) {
        return clientRepository.findClientName(name);
    }
}
