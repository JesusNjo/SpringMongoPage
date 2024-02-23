package MongoDBQuery.MongoDBQuery.controller;

import MongoDBQuery.MongoDBQuery.models.Product;
import MongoDBQuery.MongoDBQuery.pagin.Pagination;
import MongoDBQuery.MongoDBQuery.repository.product.ProductRepository;
import MongoDBQuery.MongoDBQuery.service.product.IProduceService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final IProduceService produceService;

    @GetMapping
    public ResponseEntity<List<Product>> getALlProduct(){
        List<Product> productList = produceService.getAllProduct();
        return !productList.isEmpty()?ResponseEntity.ok(productList): ResponseEntity.noContent().build();
    }
    @GetMapping("/by-client")
    public ResponseEntity<List<?>> getProductByClient(){
        List<?> productBuy = produceService.findProductByPurchases();
        return !productBuy.isEmpty()?ResponseEntity.ok(productBuy):ResponseEntity.noContent().build();
    }

    @GetMapping("/id")
    public ResponseEntity<Product> findById(@RequestParam("_id") String id){
        return produceService.findProductById(id) != null? ResponseEntity.ok(
                produceService.findProductById(id)): ResponseEntity.notFound().build();
    }
    @GetMapping("/count")
    public ResponseEntity<Integer> countProduct(){
        return ResponseEntity.ok(produceService.countTotalProduct());
    }
    @GetMapping("/pageable")
    public ResponseEntity<List<Product>> findAllPagProduct(@NotNull Pagination pagination){
        List<Product> page = produceService.findProductByPagination(pagination);
        return !page.isEmpty()?ResponseEntity.ok(page):ResponseEntity.noContent().build();
    }
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        produceService.createProduct(product);
        return ResponseEntity.ok(product);
    }
    @DeleteMapping("/id")
    public ResponseEntity<String> deleteProduct(@RequestParam("id") String id) {
        Product productToDelete = produceService.findProductById(id);
        if (productToDelete != null) {
            produceService.deleteProductById(id);
            return ResponseEntity.ok(String.format("%s %s", id, "was deleted"));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/name")
    public ResponseEntity<Product> findProductByName(@RequestParam("name") String name){
        Product productByName = produceService.findPByName(name);
        if(productByName != null){
            return ResponseEntity.ok(productByName);
        }else{
            return ResponseEntity.noContent().build();
        }
    }
}
