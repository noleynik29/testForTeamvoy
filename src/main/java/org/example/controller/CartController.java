package org.example.controller;

import org.example.component.OrderCleanupScheduler;
import org.example.model.Cart;
import org.example.model.CartItem;
import org.example.model.Order;
import org.example.repository.CartRepository;
import org.example.repository.OrderRepository;
import org.example.repository.ProductRepository;
import org.example.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    OrderCleanupScheduler orderCleanupScheduler;

    @GetMapping("/cart")
    public String cart(Model model) {
        Cart cart = cartService.getCart();
        List<CartItem> cartItems = cart.getCartItems();
        model.addAttribute("cart", cart);
        model.addAttribute("cartItems", cartItems);
        return "shoppingCart";
    }

    @PostMapping("/cart/{id}")
    public String updateCartItem(
            @PathVariable("id") Long id,
            @ModelAttribute("item") CartItem item,
            @RequestParam(value = "decrement", required = false) String decrement,
            @RequestParam(value = "increment", required = false) String increment,
            RedirectAttributes redirectAttributes) {
        if (decrement != null) {
            cartService.decrementCartItemQuantity(id);
        }
        if (increment != null) {
            cartService.incrementCartItemQuantity(id);
        }
        return "redirect:/cart";
    }

    @GetMapping("/remove/{id}")
    public String deleteCartItem(@PathVariable Long id){
        cartService.deleteFromCart(id);
        return "redirect:/cart";
    }

    @PostMapping("/checkout")
    public String viewCheckout(Model model) {
        Cart cart = cartService.getCart();
        cartRepository.save(cart);
        Order order = new Order(cart);
        orderRepository.save(order);
        model.addAttribute("order", order);
        orderCleanupScheduler.cleanupNotPaidOrders();
        return "checkout";
    }

    @PostMapping("/pay/{id}")
    public String orderPayed(@PathVariable Long id){
        Order order = orderRepository.getById(id);
        order.setPaid(true);
        return "payedOrder";
    }
}