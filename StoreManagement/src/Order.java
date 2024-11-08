
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MasterLong
 */
public class Order {
    private int orderId;
    private Customer customer;
    private List<Product> products = new ArrayList<>();
    private double totalAmount;

    
    
    public Order() {
    }
    
    public Order(int orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
    
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public void addProduct (Product p, int quantity){
        Product product;
        if (p instanceof DigitalProduct){
            product  = new DigitalProduct(p.getProductId(), p.getName(), p.getPrice(), quantity, ( (DigitalProduct)p).getFileSize() );
        } else {
            product  = new Product(p.getProductId(), p.getName(), p.getPrice(), quantity);
        }
        
        products.add(product);
    }
    
    public double caculateTotal(){
        totalAmount = 0;
        for ( Product p : products){
            totalAmount = totalAmount + p.getPrice()*p.getQuantity();
        }
        return totalAmount;
    }
    
    public String getOrderDetails(){
        String productList = "";
        for (Product p : products){
            productList += p.getProductId() +"; " + p.getQuantity() +"; ";
        }
        return orderId+ "; " +customer.getCustomerId()  + "; " + customer.getEmail()  + "; " +customer.getName()  +"; "  +  products.size()+ "; "
                + productList;
    }
    
    public void showOrder(){
        
        System.out.println("OrderID: " + orderId + "; " + customer.getInfo());
        for (Product p : products){
            System.out.println(" - "+p.getInfo());
        }
        System.out.println(" => Total Amount: " + caculateTotal());
    }
    
    public boolean checkProduct(){
        return !products.isEmpty();
    }
}
