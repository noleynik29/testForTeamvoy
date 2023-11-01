package org.example.service;

import org.example.model.Cart;
import org.example.model.CartItem;
import org.example.model.Product;
import org.example.repository.CartItemRepository;
import org.example.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;
    private Cart cart = new Cart();

    public Cart getCart() {
        return cart;
    }

    public void addToCart(Product product) {
        if(!cart.hasProductInCart(product)) {
            CartItem cartItem = new CartItem(product, 1);
            cartItemRepository.save(cartItem);
            cart.addCartItem(cartItem);
        }
    }

    public void deleteFromCart(Long id) {
        CartItem cartItem = cart.getCartItemById(id);
        cartItemRepository.delete(cartItem);
        cart.deleteCartItem(cartItem);
    }

    public void updateCartItemQuantity(Long id, int newQuantity) {
        CartItem cartItem = cart.getCartItemById(id);
        cartItem.setQuantity(newQuantity);
    }

    public void decrementCartItemQuantity(Long id) {
        CartItem cartItem = cart.getCartItems().get(Math.toIntExact(id-1));
        int currentQuantity = cartItem.getQuantity();
        if (currentQuantity > 1) {
            updateCartItemQuantity(id, currentQuantity - 1);
        }
    }

    public void incrementCartItemQuantity(Long id) {
        CartItem cartItem = cart.getCartItemById(id);
        int currentQuantity = cartItem.getQuantity();
        int availableQuantity = cartItem.getProductQuantity();
        if (currentQuantity < availableQuantity) {
            updateCartItemQuantity(id, currentQuantity + 1);
        }
    }
}

