package MongoDBQuery.MongoDBQuery.controller;

import MongoDBQuery.MongoDBQuery.models.Client;
import MongoDBQuery.MongoDBQuery.pagin.Pagination;
import MongoDBQuery.MongoDBQuery.service.client.IClientService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final IClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> findAllClient(){
        List<Client> clientList = clientService.getAllClient();
        return !clientList.isEmpty()?ResponseEntity.ok(clientList):ResponseEntity.noContent().build();
    }
    @GetMapping("/by-product")
    public ResponseEntity<List<?>> getByOrder(){
        List<?> clientBuy = clientService.findClientByPurchases();
        return !clientBuy.isEmpty()?ResponseEntity.ok(clientBuy):ResponseEntity.noContent().build();
    }
    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client){
        clientService.createClient(client);
        return ResponseEntity.ok(client);
    }
    @GetMapping("/id")
    public ResponseEntity<Client> findById(@RequestParam("_id") String clientId){
        return clientService.findClientById(clientId) != null? ResponseEntity.ok(
                clientService.findClientById(clientId)
        ): ResponseEntity.notFound().build();
    }

    @GetMapping("/pageable")
    public ResponseEntity<List<Client>> findAllPagClient(@NotNull Pagination pageable) {
        List<Client> page = clientService.findClientByPagination(pageable);
        return !page.isEmpty()?ResponseEntity.ok(page): ResponseEntity.noContent().build();
    }
    @DeleteMapping("/id")
    public ResponseEntity<String> deleteClient(@RequestParam("id") String clientId){
        Client clientToDelete = clientService.findClientById(clientId);
        if(clientToDelete !=null){
            clientService.deleteClientById(clientToDelete.getId());
            return ResponseEntity.ok(clientId + " was deleted");
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/name")
    public ResponseEntity<Client> findClientByNameC(@RequestParam String name){
        Client clientByName = clientService.findClientByName(name);
        if(clientByName != null){
            return ResponseEntity.ok(clientByName);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/count")
    public ResponseEntity<Integer> clientCount(){
        return ResponseEntity.ok(clientService.countTotalClient());
    }
}
