package org.example.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<CartItem> cartItems = new ArrayList<>();
    private double total;

    public Cart() {
    }

    public Cart(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void addCartItem(CartItem cartItem){
        cartItems.add(cartItem);
    }

    public void deleteCartItem(CartItem cartItem) { cartItems.remove(cartItem);}

    public double getTotal() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getTotal();
        }
        return total;
    }

    public CartItem getCartItemById(Long id){
        CartItem cartItem = new CartItem();
        for (CartItem item: cartItems) {
            if(item.getId().equals(id)){
                cartItem = item;
            }
        };
        return cartItem;
    }

    public boolean hasProductInCart(Product product){
        CartItem cartItem = new CartItem();
        for (CartItem item: cartItems) {
            if(item.getProductName().equals(product.getName())){
                return true;
            }
        };
        return false;
    }
}
