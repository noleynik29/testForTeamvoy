package org.example.controller;

import jakarta.validation.Valid;
import org.example.model.CartItem;
import org.example.model.Product;
import org.example.repository.ProductRepository;
import org.example.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartService cartService;

    @GetMapping("/products")
    public String listProducts(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @PostMapping("/add-to-cart/{productId}")
    public String addToCart(
            @PathVariable Long productId,
            RedirectAttributes redirectAttributes
    ) {
        Product product = productRepository.getById(productId);

        if (product != null) {
            cartService.addToCart(product);
            redirectAttributes.addFlashAttribute("success", "Product added to cart");
        } else {
            redirectAttributes.addFlashAttribute("error", "Product not found");
        }

        return "redirect:/products";
    }

    @GetMapping("/addProduct")
    public String viewAddProduct() {
        return "addProduct";
    }

    @PostMapping("/addProduct")
    public String addProduct(@Valid @ModelAttribute("product") Product product, Model model) {
        productRepository.save(product);
        model.addAttribute("addProduct");
        return "addProduct";
    }
}
