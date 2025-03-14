import java.util.*;

class Book {
    private static int bookCounter = 1; // Auto-generate book IDs
    private int bookID;
    private String title, author;
    private boolean issued;

    public Book(String title, String author) {
        this.bookID = bookCounter++;
        this.title = title;
        this.author = author;
        this.issued = false;
    }

    public int getBookID() { return bookID; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isIssued() { return issued; }

    public void markAsIssued() { issued = true; }
    public void markAsReturned() { issued = false; }

    @Override
    public String toString() {
        return "Book ID: " + bookID + " | " + title + " by " + author + " | Issued: " + issued;
    }
}

class Library {
    private LinkedList<Book> catalog = new LinkedList<>();

    public void insertBook(String title, String author) {
        Book newBook = new Book(title, author);
        catalog.addFirst(newBook); // Add to the front
        System.out.println("Added: " + newBook);
    }

    public void deleteBook(int id) {
        for (Book book : catalog) {
            if (book.getBookID() == id) {
                catalog.remove(book);
                System.out.println("Removed Book ID: " + id);
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void lendBook(int id) {
        for (Book book : catalog) {
            if (book.getBookID() == id && !book.isIssued()) {
                book.markAsIssued();
                System.out.println("Book issued: " + book);
                return;
            }
        }
        System.out.println("Book unavailable.");
    }

    public void receiveBook(int id) {
        for (Book book : catalog) {
            if (book.getBookID() == id && book.isIssued()) {
                book.markAsReturned();
                System.out.println("Book returned: " + book);
                return;
            }
        }
        System.out.println("Invalid return request.");
    }

    public void displayBooks() {
        if (catalog.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            catalog.forEach(System.out::println);
        }
    }
}

public class SimpleLibrary {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary System");
            System.out.println("1. Insert Book");
            System.out.println("2. Delete Book");
            System.out.println("3. Lend Book");
            System.out.println("4. Receive Book");
            System.out.println("5. Show Books");
            System.out.println("6. Quit");
            System.out.print("Select: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    library.insertBook(title, author);
                    break;
                case 2:
                    System.out.print("Enter Book ID to delete: ");
                    int deleteID = scanner.nextInt();
                    library.deleteBook(deleteID);
                    break;
                case 3:
                    System.out.print("Enter Book ID to lend: ");
                    int lendID = scanner.nextInt();
                    library.lendBook(lendID);
                    break;
                case 4:
                    System.out.print("Enter Book ID to receive: ");
                    int returnID = scanner.nextInt();
                    library.receiveBook(returnID);
                    break;
                case 5:
                    library.displayBooks();
                    break;
                case 6:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid input.");
            }
        }
    }
}
