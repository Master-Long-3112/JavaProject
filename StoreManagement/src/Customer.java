/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MasterLong
 */
public class Customer {
    private int CustomerId;
    private String name;
    private String email;
    
    //Getter setter

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCusttomerId(int CusttomerId) {
        this.CustomerId = CusttomerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    //

    public Customer() {
    }

    public Customer(int CusttomerId, String name, String email) {
        this.CustomerId = CusttomerId;
        this.name = name;
        this.email = email;
    }
    
    public String getInfo(){
        return "CustomerID: " + CustomerId + "; Name: " + name + "; Email: " + email;
    }
}
