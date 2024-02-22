package MongoDBQuery.MongoDBQuery.service.order;

import MongoDBQuery.MongoDBQuery.models.Client;
import MongoDBQuery.MongoDBQuery.models.Order;
import MongoDBQuery.MongoDBQuery.models.Product;
import MongoDBQuery.MongoDBQuery.repository.client.ClientRepository;
import MongoDBQuery.MongoDBQuery.repository.order.OrderRepository;
import MongoDBQuery.MongoDBQuery.repository.product.ProductRepository;
import MongoDBQuery.MongoDBQuery.service.client.IClientService;
import MongoDBQuery.MongoDBQuery.service.product.IProduceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("OrderServiceImpl")
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements IOrderService{

    public final OrderRepository orderRepository;
    private final IClientService clientService;
    private final IProduceService produceService;
    @Override
    public List<Order> findAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public Order findOrderById(String _id) {
        return orderRepository.getOrderById(_id);
    }

    @Override
    public void createOrder(Order order) {
        Client clientTo = clientService.findClientById(order.getClient());
        Product productTo = produceService.findProductById(order.getProduct());
        order = Order.builder()
                .client(clientTo.getId())
                .product(productTo.getId())
                .quantity(order.getQuantity())
                .build();
        if(clientTo.getName() != null && productTo.getName() != null){
            orderRepository.save(order);
        }
    }

    @Override
    public void deleteOrderById(String id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void lookByParam(String param) {
        orderRepository.lookingForByParameter(param);
    }
}
