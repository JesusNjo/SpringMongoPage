package MongoDBQuery.MongoDBQuery.controller.querys;

import MongoDBQuery.MongoDBQuery.models.Client;
import MongoDBQuery.MongoDBQuery.models.querys.ClientQuery;
import MongoDBQuery.MongoDBQuery.pagin.Pagination;
import MongoDBQuery.MongoDBQuery.service.client.queryHandle.IClientQueryHandlerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientQueryHandler {

    private final IClientQueryHandlerService clientQueryHandlerService;
    @GetMapping("/query")
    public ResponseEntity<List<Client>> filterClientQuery(@RequestBody ClientQuery query, Pagination pagination){
        Page<Client> page = clientQueryHandlerService.handleQuery(query.getQuery(),pagination);
        return !page.isEmpty()?ResponseEntity.ok(page.getContent()):ResponseEntity.noContent().build();
    }
}
