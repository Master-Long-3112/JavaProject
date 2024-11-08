
import java.io.FileNotFoundException;
import java.util.Scanner;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MasterLong
 */
public class Run {
    public static void main(String[] args) {
        Store store = new Store();
        try {
            //Import data
            store.importData();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
        Scanner scan = new Scanner(System.in);  
        while (true){
            System.out.println("=================================");
            System.out.println("|        STORE MANAGEMENT       |");
            System.out.println("=================================");
            System.out.println("|--> Please choose an option    |");
            System.out.println("|1. Add product to store        |");
            System.out.println("|2. Create an order             |");
            System.out.println("|3. Show all product in store   |");
            System.out.println("|4. Show all order              |");
            System.out.println("|5. Search product by name      |");
            System.out.println("|6. View total revenue          |");
            System.out.println("|7. Exit                        |");
            System.out.println("=================================");
            


            int n = Integer.parseInt(scan.nextLine());
            
            switch (n){
                case 1:
                    Product  p = new Product();
                    store.addProduct(p);
                    break;
                case 2: 
                    Customer customer = new Customer();
                    store.createOrder(customer);
                    break;
                case 3:
                    store.displayStore();
                    break;
                case 4: 
                    store.displayOrder();
                    break;
                case 5: 
                    store.searchProductByName();
                    break;
                case 6: 
                    store.showTotalRevenue();
                    break;
                case 7: 
                    System.exit(0);
            }
        }
    }
}
