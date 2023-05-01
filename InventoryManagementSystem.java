import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JTextArea;

public class InventoryManagementSystem {
    private static final String USER_FILE = "users.txt";
    private static final String INVENTORY_FILE = "inventory.txt";
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "password";
    
    private Map<String, Integer> inventory;
    private Scanner scanner;

    public InventoryManagementSystem() {
        scanner = new Scanner(System.in);
        inventory = new HashMap<>();
        loadInventoryData();
    }

    private void loadInventoryData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(INVENTORY_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                String itemName = parts[0];
                int quantity = Integer.parseInt(parts[1]);
                inventory.put(itemName, quantity);
            }
        } catch (IOException e) {
            System.out.println("Error loading inventory data.");
        }
    }

    private void saveInventoryData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(INVENTORY_FILE))) {
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                String itemName = entry.getKey();
                int quantity = entry.getValue();
                writer.write(itemName + ":" + quantity + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving inventory data.");
        }
    }

    public void login() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            showAdminPage();
        } else {
            showUserPage(username);
        }
    }

    private void showAdminPage() {
        while (true) {
            System.out.println("Admin options:");
            System.out.println("1. View inventory");
            System.out.println("2. Add item to inventory");
            System.out.println("3. Remove item from inventory");
            System.out.println("4. Logout");
            System.out.print("Enter option number:");
            String input = scanner.nextLine();
    
            switch (input) {
                case "1":
                    viewInventory();
                    break;
                case "2":
                    addItem();
                    break;
                case "3":
                    System.out.println("Enter item name:");
                    String itemName = scanner.nextLine();
                    removeItem(itemName);
                    break;
                case "4":
                    saveInventoryData();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
    
    public void viewInventory() {
        System.out.println("Current inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(itemName + ": " + quantity);
        }
    }

    private void addItem() {
        System.out.println("Enter item name:");
        String itemName = scanner.nextLine();
        System.out.println("Enter quantity:");
        int quantity = Integer.parseInt(scanner.nextLine());

        if (inventory.containsKey(itemName)) {
            int currentQuantity = inventory.get(itemName);
            inventory.put(itemName, currentQuantity + quantity);
        } else {
            inventory.put(itemName, quantity);
        }

        System.out.println("Item added to inventory.");
    }

    public void removeItem(String itemName)
    {
        if (inventory.containsKey(itemName)) {
        System.out.println("Enter quantity:");
        int quantity = Integer.parseInt(scanner.nextLine());
        int currentQuantity = inventory.get(itemName);
        if (quantity > currentQuantity) {
        System.out.println("Error: Not enough " + itemName + " in inventory.");
        } else {
        inventory.put(itemName, currentQuantity - quantity);
        System.out.println(quantity + " " + itemName + "(s) removed from inventory.");
        }
        } else {
        System.out.println("Error: " + itemName + " not found in inventory.");
        }
        }
        
        public void showUserPage(String username) {
        System.out.println("Welcome, " + username + "!");
        System.out.println("Your options:");
        System.out.println("1. View inventory");
        System.out.println("2. Order item");
        System.out.println("3. Logout");
        System.out.print("Enter option number:");
        String input = scanner.nextLine();

        switch (input) {
            case "1":
                viewInventory();
                break;
            case "2":
                System.out.println("Enter item name:");
                String itemName = scanner.nextLine();
                orderItem(itemName);
                break;
            case "3":
                return;
            default:
                System.out.println("Invalid option.");
        }
        }
        
        private void orderItem(String itemName) {
        if (inventory.containsKey(itemName)) {
        System.out.println("Enter quantity:");
        int quantity = Integer.parseInt(scanner.nextLine());
        int currentQuantity = inventory.get(itemName);
        if (quantity > currentQuantity) {
        System.out.println("Error: Not enough " + itemName + " in inventory.");
        } else {
        inventory.put(itemName, currentQuantity - quantity);
        System.out.println(quantity + " " + itemName + "(s) ordered.");
        }
        } else {
        System.out.println("Error: " + itemName + " not found in inventory.");
        }
        }
    
        public void printInventory(JTextArea textArea) {
        textArea.setText("Current inventory:\n");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
        String itemName = entry.getKey();
        int quantity = entry.getValue();
        textArea.append(itemName + ": " + quantity + "\n");
        }
        }
        public static void main(String[] args) {
            InventoryManagementSystem ims = new InventoryManagementSystem();
            ims.login();
        }
    }
        
    