package MongoDBQuery.MongoDBQuery.service.order;

import MongoDBQuery.MongoDBQuery.models.Order;

import java.util.List;
import java.util.Set;

public interface IOrderService {

    public List<Order> findAllOrder();
    public Order findOrderById(String _id);
    public void createOrder(Order order);
    public void deleteOrderById(String id);
    public Set<String> lookByParam();
}
