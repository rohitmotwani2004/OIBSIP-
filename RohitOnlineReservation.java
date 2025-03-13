import java.util.*;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public boolean checkPassword(String password) { return this.password.equals(password); }
}

class Reservation {
    private int seatNumber;
    private String trainName;
    private String passengerName;

    public Reservation(String trainName, String passengerName, int seatNumber) {
        this.trainName = trainName;
        this.passengerName = passengerName;
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return "Train: " + trainName + " | Passenger: " + passengerName + " | Seat No: " + seatNumber;
    }
}

class ReservationSystem {
    private HashMap<String, User> users = new HashMap<>();
    private HashMap<Integer, Reservation> reservations = new HashMap<>();
    private Random random = new Random();
    private int nextTicketId = 1;

    public void registerUser(String username, String password) {
        if (users.containsKey(username)) {
            System.out.println("Username already exists! Choose another.");
        } else {
            users.put(username, new User(username, password));
            System.out.println("User registered successfully!");
        }
    }

    public boolean loginUser(String username, String password) {
        if (users.containsKey(username) && users.get(username).checkPassword(password)) {
            System.out.println("Login successful! Welcome, " + username + "!");
            return true;
        }
        System.out.println("Invalid username or password.");
        return false;
    }

    public void bookTicket(String trainName, String passengerName) {
        int seatNumber = random.nextInt(100) + 1; // Assigns a random seat from 1 to 100
        reservations.put(nextTicketId, new Reservation(trainName, passengerName, seatNumber));
        System.out.println("Ticket booked successfully! Your Ticket ID: " + nextTicketId);
        nextTicketId++;
    }

    public void cancelTicket(int ticketId) {
        if (reservations.containsKey(ticketId)) {
            reservations.remove(ticketId);
            System.out.println("Ticket " + ticketId + " canceled successfully!");
        } else {
            System.out.println("Invalid Ticket ID! No such reservation exists.");
        }
    }

    public void viewBookings() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            for (Map.Entry<Integer, Reservation> entry : reservations.entrySet()) {
                System.out.println("Ticket ID: " + entry.getKey() + " | " + entry.getValue());
            }
        }
    }
}

public class RohitOnlineReservation {
    public static void main(String[] args) {
        ReservationSystem system = new ReservationSystem();
        Scanner scanner = new Scanner(System.in);
        String loggedInUser = null;

        while (true) {
            System.out.println("\nOnline Reservation System");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Book Ticket");
            System.out.println("4. Cancel Ticket");
            System.out.println("5. View Reservations");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter a username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter a password: ");
                    String password = scanner.nextLine();
                    system.registerUser(username, password);
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    password = scanner.nextLine();
                    if (system.loginUser(username, password)) {
                        loggedInUser = username;
                    }
                    break;
                case 3:
                    if (loggedInUser == null) {
                        System.out.println("Please login first.");
                        break;
                    }
                    System.out.print("Enter Train Name: ");
                    String train = scanner.nextLine();
                    System.out.print("Enter Passenger Name: ");
                    String passenger = scanner.nextLine();
                    system.bookTicket(train, passenger);
                    break;
                case 4:
                    if (loggedInUser == null) {
                        System.out.println("Please login first.");
                        break;
                    }
                    System.out.print("Enter Ticket ID to cancel: ");
                    int ticketId = scanner.nextInt();
                    system.cancelTicket(ticketId);
                    break;
                case 5:
                    system.viewBookings();
                    break;
                case 6:
                    System.out.println("Exiting... Thank you!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option! Try again.");
            }
        }
    }
}
