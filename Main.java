import java.util.*;

class Stock {
    String name;
    int quantity;
    double price;

    public Stock(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void display() {
        System.out.println(name + " " + quantity + " " + price);
    }
}

class StockTrading {
    ArrayList<Stock> market_data = new ArrayList<>();
    ArrayList<Stock> user_data = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    double balance = 10000.0;

    public StockTrading() {
        stocks(); 
    }

    public void stocks() {
        market_data.add(new Stock("SAMSUNG", 10, 200.0));
        market_data.add(new Stock("Android", 7, 100.0));
    }

    public void marketData() {
        for (Stock s : market_data) {
            s.display();
        }
    }

    public void buy() {
        System.out.println("Enter company name:");
        String name = sc.next();
        System.out.println("Enter quantity:");
        int quantity = sc.nextInt();
        
        double price = -1; 


        for (Stock s1 : market_data) {
            if (s1.getName().equals(name)) {

                if (s1.getQuantity() <= quantity) {
                    price = s1.getPrice();
                    double total = quantity * price;
                    balance -= total;
                    user_data.add(new Stock(name, quantity, price));
                    System.out.println("Purchase successful!");
                    return;
                } else {
                    System.out.println("Stock sold out");
                    return;
                }
            }
        }

        user_data.add(new Stock(name,quantity,price));
    }

    private void sell() {
        System.out.println("Enter product name:");
        String name = sc.next();
        System.out.println("Enter quantity:");
        int quantity = sc.nextInt();
        for (Stock s2 : user_data) {
            if (s2.getName().equals(name)) {
                int q = s2.getQuantity();
                if (quantity <= q) {
                    double price = s2.getPrice();
                    double total = price * quantity;
                    balance += total;
                    System.out.println("Stock sold successfully!");
                    s2.quantity-=quantity;
                    return;
                } else {
                    System.out.println("Not enough stock available");
                    return;
                }
            }
        }
    }

    private void viewPortfolio() {
        double portfolio = 0;
        for (Stock s3 : user_data) {
            portfolio += s3.getQuantity() * s3.getPrice();
        }
        System.out.println("Portfolio value: " + portfolio);
    }

    public void start() {
        while (true) {
            System.out.println("\nWelcome to Stock Trading!");
            System.out.println("1. View Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            int choice = sc.nextInt();
            
            switch (choice) {
                case 1:
                    marketData();
                    break;
                case 2:
                    buy();
                    break;
                case 3:
                    sell();
                    break;
                case 4:
                    viewPortfolio();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
public class Main {
    public static void main(String[] args) {
        StockTrading st = new StockTrading();
        st.start();
    }
}
