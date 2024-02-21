package MongoDBQuery.MongoDBQuery.service.product;

import MongoDBQuery.MongoDBQuery.models.Product;
import MongoDBQuery.MongoDBQuery.pagin.Pagination;
import MongoDBQuery.MongoDBQuery.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("ProductServiceImpl")
@RequiredArgsConstructor
public class ProductServiceImpl implements IProduceService {
    private final ProductRepository productRepository;
    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<?> findProductByPurchases() {
        return productRepository.getInfoByProduct();
    }

    @Override
    public List<Product> findProductByPagination(Pagination pagination) {
        return productRepository.findAllProductsWithPagination(
                pagination.getPage(), pagination.getSize(),
                pagination.getDirection() == Sort.Direction.ASC ?1:-1,
                pagination.getProperties()[0]
        );
    }

    @Override
    public Product findProductById(String _id) {
        return productRepository.findProductById(_id);
    }

    @Override
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProductById(String _id) {
        productRepository.delete(findProductById(_id));
    }

    @Override
    public Integer countTotalProduct() {
        return productRepository.countTotalProducts();
    }
}
