package MongoDBQuery.MongoDBQuery.controller;

import MongoDBQuery.MongoDBQuery.models.Order;
import MongoDBQuery.MongoDBQuery.repository.order.OrderRepository;
import MongoDBQuery.MongoDBQuery.service.order.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final IOrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrder(){
        List<Order> orderList = orderService.findAllOrder();
        return !orderList.isEmpty()?ResponseEntity.ok(orderList):ResponseEntity.noContent().build();
    }
    @GetMapping("/id")
    public ResponseEntity<Order> findOrderById(@RequestParam("id")String id){
        Order orderFound = orderService.findOrderById(id);
        if(orderFound != null){
            return ResponseEntity.ok(orderFound);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/param")
    public ResponseEntity<List<String>> findByParameter(){
        Set<String> orderByParam = orderService.lookByParam();
        List<String> orderBy = orderByParam.stream().sorted().toList();
        if(!orderBy.isEmpty()){
            return ResponseEntity.ok(orderBy);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping()
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        orderService.createOrder(order);
        return ResponseEntity.ok(order);
    }
}
