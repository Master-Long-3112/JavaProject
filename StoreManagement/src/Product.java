/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MasterLong
 */
public class Product {
   private int productId;
   private String name;
   private double price;
   private int quantity;

    public Product() {
    }

    public Product(int productId, String name, double price, int quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

   
   
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
   
   public void updateQuantity(int quantity){
       this.quantity = this.quantity + quantity;
   }
   public String getInfo(){
       return "Product ID: " + productId +  "; Name: " + name +  "; Price: " + price + "; Quantity: " + quantity;
   }

    @Override
    public String toString() {
        return productId + "; " + name + "; " + price + "; " + quantity + "; " + 0;
    }
   
   
}
