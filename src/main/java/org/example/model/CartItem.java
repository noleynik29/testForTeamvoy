package org.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cartItem")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="vendor_ID", unique = true, nullable = false)
    private Long id;

    @OneToOne
    private Product product;
    private int quantity;

    public CartItem() {
    }

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return product.getPrice() * quantity;
    }

    public String getProductName(){
        return this.product.getName();
    }

    public double getProductPrice(){
        return this.product.getPrice();
    }

    public int getProductQuantity() { return this.product.getQuantity(); }
}
