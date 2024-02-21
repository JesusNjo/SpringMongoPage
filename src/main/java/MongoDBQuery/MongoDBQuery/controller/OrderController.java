package MongoDBQuery.MongoDBQuery.controller;

import MongoDBQuery.MongoDBQuery.models.Order;
import MongoDBQuery.MongoDBQuery.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderRepository orderRepository;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrder(){
        List<Order> orderList = orderRepository.findAll();
        return !orderList.isEmpty()?ResponseEntity.ok(orderList):ResponseEntity.noContent().build();
    }
}
