package main;

import java.util.*;

import model.*;
import file.*;
import search.*;
import sort.*;
import cart.*;
import order.*;
import hashing.*;
import util.*;

public class MainApp {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        ArrayList<Product> products = FileHandler.loadProducts("products.txt");
        HashUtil.HashTable map = HashUtil.createMap(products);
        
     Sort srt = new Sort();
     products = srt.autoSortById(products);

        Cart cart = new Cart();
        OrderQueue order = new OrderQueue();

        while (true) {

            System.out.println("\n========= WELCOME TO SHOPIFY =========");
            System.out.println("1. View All Products");
            System.out.println("2. Search Products (Name / ID)");
            System.out.println("3. Sort Products");
            System.out.println("4. Add Item to Cart");
            System.out.println("5. Remove Item from Cart");
            System.out.println("6. Undo Last Action");
            System.out.println("7. Redo Last Action");
            System.out.println("8. View cart");
            System.out.println("9. Place Order & Generate Bill");
            System.out.println("10.Exit");
            System.out.println("==========================================");
            System.out.print("Enter your choice: ");

            int ch = sc.nextInt();

            switch (ch) {
                //Display available products 
                case 1:
                    System.out.println("\nAvailable Products:");
                    products.forEach(System.out::println);
                    break;
                //Searching
                case 2:
                	products = srt.autoSortById(products);
                    System.out.println("\nSearch Options:");
                    System.out.println("1. Search by Name (Binary Search)");
                    System.out.println("2. Search by ID (Linear Search)");
                    System.out.print("Choose option: ");
                    int s = sc.nextInt();

                    if (s == 1) {
                        products.sort(Comparator.comparing(p -> p.name));
                        System.out.print("Enter product name: ");
                        Product res = Search.binaryByName(products, sc.next());

                        if (res != null)
                            System.out.println("Found: " + res);
                        else
                            System.out.println("Product not found");

                    } else if (s == 2) {
                        System.out.print("Enter product ID: ");
                        Product res = Search.linearById(products, sc.nextInt());

                        if (res != null)
                            System.out.println("Found: " + res);
                        else
                            System.out.println("Product not found");
                    } else {
                        System.out.println("Invalid option");
                    }
                    break;
                //sorting
                case 3:
                    Product[] arr = products.toArray(new Product[0]);
                  
                    System.out.println("\nSort Options:");
                    System.out.println("1. Price Ascending (Quick Sort)");
                    System.out.println("2. Price Descending (Merge Sort)");
                    System.out.println("3. Name (Selection Sort)");
                    System.out.print("Choose option: ");

                    int so = sc.nextInt();

                    if (so == 1) {
                        srt.sortByAscending(arr, 0, arr.length - 1);
                        System.out.println("Sorted by Price (Ascending)");

                    } else if (so == 2) {
                        srt.sortByDescending(arr, 0, arr.length - 1);
                        System.out.println("Sorted by Price (Descending)");

                    } else if (so == 3) {
                        srt.sortByName(arr);
                        System.out.println("Sorted by Name");

                    } else {
                        System.out.println("Invalid option");
                        break;
                    }

                    // Convert back to ArrayList
                    products = new ArrayList<>(Arrays.asList(arr));

                    // Display sorted products
                    products.forEach(System.out::println);

                    break;
                 // Add product to cart using HashMap
                case 4:
                    System.out.print("Enter product ID: ");
                    int addId = sc.nextInt();
                    products = srt.autoSortById(products);

                    Product addP = map.get(addId);

                    if (addP != null) {

                        System.out.print("Enter quantity: ");
                        int qty = sc.nextInt();

                        if (addP.stock >= qty) {

                            for (int i = 0; i < qty; i++) {
                                cart.add(addP);
                            }

                            System.out.println(qty + " item(s) added to cart");

                        } else {
                            System.out.println("Only " + addP.stock + " item(s) available");
                        }

                    } else {
                        System.out.println("Invalid product ID");
                    }
                    break;
                 // Remove product from cart
                case 5:
                	products = srt.autoSortById(products);
                    System.out.print("Enter product ID: ");
                    int remId = sc.nextInt();

                    Product remP = map.get(remId);

                    if (remP != null) {

                        System.out.print("Enter quantity to remove: ");
                        int qty = sc.nextInt();

                        for (int i = 0; i < qty; i++) {
                            cart.remove(remP);
                        }

                        System.out.println("Item(s) removed from cart");

                    } else {
                        System.out.println("Invalid product ID");
                    }
                    break;
                // Undo last cart operation using stack
                case 6:
                    cart.undo();
                    break;
                 // Redo last undone operation using stack
                case 7:
                    cart.redo();
                    break;
                 // Display cart items
                case 8:
                    System.out.println("\nYour Cart:");
                    cart.show();
                    System.out.println("Total: " + cart.total());
                    break;
                // Place order with discount and queue processing
                case 9:

                    if (cart.cart.isEmpty()) {
                        System.out.println("Cart is empty");
                        break;
                    }

                    System.out.println("\nYour Cart:");
                    cart.show();

                    double total = cart.total();
                    System.out.println("Total Amount: " + total);

                    System.out.print("\nConfirm Order? (yes/no): ");
                    String confirm = sc.next();

                    if (!confirm.equalsIgnoreCase("yes")) {
                        System.out.println("Order Cancelled");
                        break;
                    }

                    // PAYMENT OPTIONS
                    System.out.println("\nSelect Payment Method:");
                    System.out.println("1. UPI");
                    System.out.println("2. Card");
                    System.out.println("3. Cash on Delivery");

                    int pay = sc.nextInt();

                    switch (pay) {
                        case 1:
                            System.out.println("Processing UPI Payment...");
                            break;
                        case 2:
                            System.out.println("Processing Card Payment...");
                            break;
                        case 3:
                            System.out.println("Cash on Delivery Selected");
                            break;
                        default:
                            System.out.println("Invalid Payment Option");
                            break;
                    }

                    System.out.println("Payment Successful ✅");

                    double finalAmt = Discount.apply(total);

                    order.placeOrder(cart.cart);
                    order.process();

                    System.out.println("Final Bill after Discount: " + finalAmt);

                    cart.cart.clear();
                    break;
                    
               //exiting  
                case 10:
                    System.out.println("Thank you for shopping");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}