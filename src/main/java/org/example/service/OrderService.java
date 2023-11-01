package org.example.service;

import org.example.model.Order;
import org.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findOlderThanTenMinutes() {
        LocalDateTime tenMinutesAgo = LocalDateTime.now().minusMinutes(1);
        return orderRepository.findByCreatedAtBefore(tenMinutesAgo);
    }

    public void deleteOrder(Order order) {
        orderRepository.delete(order);
    }
}
