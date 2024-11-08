
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author MasterLong
 */
public class Store {

    private List<Product> products = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    Scanner scan = new Scanner(System.in);

    /**
     * *
     *
     * Import Data From File
     *
     * @throws java.io.FileNotFoundException
     */
    public void importData() throws FileNotFoundException {
        File file1 = new File("Product.txt");
        File file2 = new File("Order.txt");
        Scanner productsFile = new Scanner(file1);
        Scanner ordersFile = new Scanner(file2);
        productsFile.useDelimiter("[; \\s]+");
        ordersFile.useDelimiter("[;\\s]+");
        //Import product
        while (productsFile.hasNext()) {
            int productId = Integer.parseInt(productsFile.next());
            String name = productsFile.next();
            double price = Double.parseDouble(productsFile.next());
            int quantity = Integer.parseInt(productsFile.next());
            double fileSise = Double.parseDouble(productsFile.next());
            Product p;
            if (fileSise == 0) {
                p = new Product(productId, name, price, quantity);
            } else {
                p = new DigitalProduct(productId, name, price, quantity, fileSise);
            }
            //Them  san pham vao cua hang
            products.add(p);
        }
        //Import Order
        while (ordersFile.hasNext()) {
            Customer customer;
            Order order;
            int orderId = Integer.parseInt(ordersFile.next());
            int CustomerId = Integer.parseInt(ordersFile.next());
            String name = ordersFile.next();
            String email = ordersFile.next();
            //Dong goi customer
            customer = new Customer(CustomerId, name, email);
            //Dong goi order
            order = new Order(orderId, customer);

            int n = Integer.parseInt(ordersFile.next());
            for (int i = 1; i <= n; i++) {
                int x = Integer.parseInt(ordersFile.next());
                int count = Integer.parseInt(ordersFile.next());
                //Them san pham vao 
                for (Product p : products) {
                    if (p.getProductId() == x) {
                        order.addProduct(p, count);
                        break;
                    }
                }
                //Dong goi order
                orders.add(order);

            }
        }

    }

    public void addProduct(Product product) {
        System.out.println("Number of product to add ?");
        int n = Integer.parseInt(scan.nextLine());
        for (int i = 1; i <= n; i++) {
            int k = 0;
            System.out.println("-> Adding product " + i);
            //Nhap du lieu
            System.out.println("Product ID: ");
            int productId = Integer.parseInt(scan.nextLine());
            System.out.println("Product name: ");
            String name = scan.nextLine();
            System.out.println("Product price: ");
            double price = Double.parseDouble(scan.nextLine());
            System.out.println("Product quantity: ");
            int quantity = Integer.parseInt(scan.nextLine());
            System.out.println("Product filesize: ");
            double fileSize = Double.parseDouble(scan.nextLine());
            //
            //Check id de xem co trong cua hang chua
            for (Product p : products) {
                if (p.getProductId() == productId) {
                    p.updateQuantity(quantity);
                    k = 1;
                }
            }

            //Neu san pham chua co trong cua hang : k==0
            if (k == 0) {
                //Dong goi san pham
                if (fileSize == 0) {
                    product = new Product(productId, name, price, quantity);
                } else {
                    product = new DigitalProduct(productId, name, price, quantity, fileSize);
                }
                //Dua san pham vao cua hang
                products.add(product);
            }
        }
        //Them vao file
        try {
            FileWriter fw = new FileWriter("Product.txt");
            BufferedWriter bw = new BufferedWriter(fw);

            String productList = "";
            for (Product p : products) {
                productList += p.toString() + "\n";
            }
            bw.write(productList);
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println("No such directory");
        }
    }
    
    public void createOrder(Customer customer) {

        System.out.println("Customer Id");
        int CustomerId = Integer.parseInt(scan.nextLine());
        System.out.println("Customer Name");
        String name = scan.nextLine();
        System.out.println("Customer Email");
        String email = scan.nextLine();

        //Dong goi customer
        customer = new Customer(CustomerId, name, email);
        Order order = new Order(orders.size() + 1, customer);
        //Lua chon cua nguoi dung
        while (true) {
            System.out.println("2.1. Choose product (1)");
            System.out.println("2.2. Finish choosing(2)");
            int n = Integer.parseInt(scan.nextLine());
            switch (n) {
                case 1:
                    displayStore();
                    System.out.println("Enter product ID");
                    int id = Integer.parseInt(scan.nextLine());
                    System.out.println("Enter amount of product");
                    int amount;
                    while (true) {
                        amount = Integer.parseInt(scan.nextLine());
                        if (amount > 0) break;
                        System.out.println("Please type again");
                    }
                    //Di tim xem co san pham hay khong
                    int i;
                    for (i = 0; i <= products.size() - 1; i++) {
                        //Neu co san pham thi lieu co du hay khong
                        if (products.get(i).getProductId() == id) {
                            if (amount > products.get(i).getQuantity()) {
                                System.out.println("Not enough product");
                                break;
                            } else {
                                order.addProduct(products.get(i), amount);
                                products.get(i).updateQuantity(-amount);
                                //Cap nhat cua hang 
                                try {
                                    FileWriter fw = new FileWriter("Product.txt");
                                    BufferedWriter bw = new BufferedWriter(fw);

                                    String productList = "";
                                    for (Product p : products) {
                                        productList += p.toString() + "\n";
                                    }
                                    bw.write(productList);
                                    bw.close();
                                    fw.close();
                                } catch (Exception e) {
                                    System.out.println("No such directory");
                                }
                                //Thoat vong lap
                                break;
                            }
                        }
                    }
                    if (i == products.size()) {
                        System.out.println("--> No product found");
                    }
                    break;
                case 2:
                    break;
            }
            //Check dieu kien thoat
            if (n == 2) {
                break;
            }
        }
        //Them vao order
        if (order.checkProduct()) {
            orders.add(order);
        }
        //Ghi vao file
        try {
            FileWriter fw = new FileWriter("Order.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            String orderList = "";
            for (Order o : orders) {
                orderList += o.getOrderDetails() + "\n";
            }
            bw.write(orderList);
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println("No such directory");
        }
        //

    }

    public void displayStore() {
        if (products.isEmpty())
            System.out.println("No products in the store");
        else {
            for (Product p : products) {
                System.out.println(p.getInfo());
            }
        }
        
    }

    public void displayOrder() {
        if (orders.isEmpty()){
            System.out.println("No order yet!");
        } else {
            for (Order o : orders) {
                o.showOrder();
            } 
        }
        
    }
    
    public void searchProductByName(){
        System.out.println("Type name of product to search");
        String x = scan.nextLine();
        for (Product p : products){
            if (p.getName().toLowerCase().contains(x.toLowerCase())){
                System.out.println(p.getInfo());
            }
        }
    }
    
    public void showTotalRevenue(){
        double totalRevenue = 0;
        for (Order c : orders){
            totalRevenue += c.caculateTotal();
        }
        System.out.println(" ==> TOTAL REVENUE: " + totalRevenue);
    }
}
