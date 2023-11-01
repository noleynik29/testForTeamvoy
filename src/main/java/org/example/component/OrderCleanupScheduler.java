package org.example.component;

import org.example.model.Order;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class OrderCleanupScheduler {

    @Autowired
    private OrderService orderService;

    @Scheduled(fixedRate = 1)
    public void cleanupNotPaidOrders() {
        LocalDateTime tenMinutesAgo = LocalDateTime.now().minus(1, ChronoUnit.MINUTES);
        List<Order> notPaidOrders = orderService.findOlderThanTenMinutes();
        for (Order order : notPaidOrders) {
            orderService.deleteOrder(order);
        }
    }
}
