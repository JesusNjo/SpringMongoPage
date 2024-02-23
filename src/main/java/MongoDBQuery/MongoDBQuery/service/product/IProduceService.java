package MongoDBQuery.MongoDBQuery.service.product;

import MongoDBQuery.MongoDBQuery.models.Product;
import MongoDBQuery.MongoDBQuery.pagin.Pagination;

import java.util.List;

public interface IProduceService {

    public List<Product> getAllProduct();
    public List<?> findProductByPurchases();
    public List<Product> findProductByPagination(Pagination pagination);
    public Product findProductById(String _id);
    public void createProduct(Product product);
    public void deleteProductById(String _id);
    public Integer countTotalProduct();
    public Product findPByName(String name);
}
