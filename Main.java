import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Book> bookList;

    static {
        bookList = new ArrayList<>(List.of(
                new HistoryBook("B1", "Sejarah Indonesia", "Pramoedya Ananta Toer", "History", 5),
                new StoryBook("B2", "Laskar Pelangi", "Andrea Hirata", "Story", 3),
                new TextBook("B3", "Pemrograman Java", "Budi Raharjo", "Text", 2)
        ));
    }

    public static List<Book> getBookList() {
        return bookList;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin();
        String choice;

        do {
            System.out.println("==== Library System ====");
            System.out.println("1. Login as Student");
            System.out.println("2. Login as Admin");
            System.out.println("3. Exit");
            System.out.print("Choose option (1-3): ");
            choice = scanner.next();

            switch (choice) {
                case "1":
                    System.out.print("Enter your NIM: ");
                    String nim = scanner.next();

                    if (nim.length() == 15) {
                        System.out.println("Login successful for student with NIM " + nim);
                        Student student = new Student("", nim, "", "");
                        student.menuStudent();
                    } else {
                        System.out.println("Invalid NIM. NIM must be 15 digits.");
                    }
                    break;
                case "2":
                    System.out.print("Enter your username(admin): ");
                    String username = scanner.next();
                    System.out.print("Enter your password(admin): ");
                    String password = scanner.next();

                    if (admin.isAdmin(username, password)) {
                        System.out.println("Login successful as admin.");
                        admin.menuAdmin();
                    } else {
                        System.out.println("Invalid admin credentials.");
                    }
                    break;
                case "3":
                    System.out.println("Thank you. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (!choice.equals("3"));

        // objek child class
        HistoryBook historyBook1 = new HistoryBook("H1", "Sejarah Dunia", "John Doe", "History", 5);
        HistoryBook historyBook2 = new HistoryBook("H2", "Perang Dunia 2", "Jane Smith", "History", 3);
        StoryBook storyBook1 = new StoryBook("S1", "Dongeng Anak", "Mark Johnson", "Story", 7);
        StoryBook storyBook2 = new StoryBook("S2", "Cerita Rakyat", "Emily Davis", "Story", 4);
        TextBook textBook1 = new TextBook("T1", "Buku Matematika", "Michael Brown", "Text", 9);
        TextBook textBook2 = new TextBook("T2", "Buku Fisika", "Sarah Wilson", "Text", 6);

        // method pada masing-masing objek
        historyBook1.borrowBook();
        historyBook2.setStock(7);
        storyBook1.returnBook();
        storyBook2.setDuration(14);
        textBook1.borrowBook();
        textBook2.returnBook();

        System.out.println("History Book 1 stock: " + historyBook1.getStock());
        System.out.println("History Book 2 stock: " + historyBook2.getStock());
        System.out.println("Story Book 1 stock: " + storyBook1.getStock());
        System.out.println("Story Book 2 duration: " + storyBook2.getDuration() + " days");
        System.out.println("Text Book 1 stock: " + textBook1.getStock());
        System.out.println("Text Book 2 stock: " + textBook2.getStock());
    }
}