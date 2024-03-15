import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Book[] bookList;
    private List<Student> userStudent;
    private Scanner scanner;

    public Main() {
        scanner = new Scanner(System.in);
        // Inisialisasi daftar buku
        bookList = new Book[]{
                new Book("b1", "Matdis", "briyan", "Author1", 4),
                new Book("b2", "Aljabar", "alexander", "Author2", 6),
                new Book("b3", "Sejarah Indonesia", "axolso", "Author3", 12),
                new Book("b4", "Bahasa Indonesia", "Max", "Author3", 5)
        };

        // Inisialisasi daftar user student
        userStudent = new ArrayList<>();
    }

    static String[][] users = {{"admin", "admin"}};

    // Fungsi untuk login user mahasiswa
    public static void mahasiswaLogin() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your NIM (input 99 untuk back): ");
        String nim = input.nextLine();
        if (nim.equals("99")) {
            // Kembali ke menu utama
            return;
        }
        if (nim.length() == 15) {
            System.out.println("Login berhasil untuk mahasiswa dengan NIM " + nim);
            Student student = new Student("", nim, "", "");
            student.menuStudent();
        } else {
            System.out.println("Panjang NIM harus 15 karakter. Silakan coba lagi.");
        }
    }

    // Fungsi untuk login user admin
    public static void adminLogin() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your username (admin): ");
        String username = input.next();
        System.out.print("Enter your password (admin): ");
        String password = input.next();
        if (username.equals(users[0][0]) && password.equals(users[0][1])) {
            System.out.println("Login berhasil sebagai admin");
            Admin admin = new Admin();
            admin.menuAdmin();
        } else {
            System.out.println("Invalid credentials for admin.");
        }
    }

    // Main program
    public static void main(String[] args) {
        Main library = new Main();
        Scanner input = new Scanner(System.in);
        String choice;

        do {
            System.out.println("Library System");
            System.out.println("1. Login as Student");
            System.out.println("2. Login as Admin");
            System.out.println("3. Exit");
            System.out.println("=====");
            System.out.print("Choose option (1-3): ");
            choice = input.next();

            switch (choice) {
                case "1":
                    mahasiswaLogin();
                    break;
                case "2":
                    adminLogin();
                    break;
                case "3":
                    System.out.println("Terima kasih. Sampai jumpa!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (!choice.equals("3"));
    }
}

class Admin {
    private Scanner scanner;
    private List<Student> userStudent;

    public Admin() {
        this.scanner = new Scanner(System.in);
        this.userStudent = new ArrayList<>();
    }

    public void menuAdmin() {
        int choice;
        do {
            System.out.println("===== Admin Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display Registered Students");
            System.out.println("3. Logout");
            System.out.print("Choose option (1-3): ");
            choice = readIntegerInput();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    displayStudent();
                    break;
                case 3:
                    System.out.println("Logging out from admin account.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 3);
    }

    private int readIntegerInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer.");
            scanner.next(); // consume the invalid input
        }
        return scanner.nextInt();
    }

    private void displayBooks(Book[] books) {
        System.out.println("Available Books:");
        for (Book book : books) {
            System.out.println(book.getId() + " (" + book.getTitle() + ") " + " (" + book.getAuthor() + ") - " + book.getStock() + " copies available");
        }
    }

    private void addBook() {
        // Implement add book functionality
    }

    private void removeBook() {
        // Implement remove book functionality
    }

    public void addStudent() {
        System.out.print("Enter student name: ");
        scanner.nextLine();
        String name = scanner.nextLine();

        String nim;
        do {
            System.out.print("Enter student NIM: ");
            nim = scanner.nextLine();
            if (nim.length() != 15) {
                System.out.println("NIM Harus 15 Digit");
            }
        } while (nim.length() != 15);

        System.out.print("Enter student faculty: ");
        String faculty = scanner.nextLine();

        System.out.print("Enter student program: ");
        String studyProgram = scanner.nextLine();

        userStudent.add(new Student(name, nim, faculty, studyProgram));
        System.out.println("Student successfully registered.");
    }

    public void displayStudent() {
        System.out.println("List of Registered Students:");
        for (Student student : userStudent) {
            System.out.println("Name: " + student.getName());
            System.out.println("Faculty: " + student.getFaculty());
            System.out.println("NIM: " + student.getNim());
            System.out.println("Program: " + student.getStudyProgram());
            System.out.println();
        }
    }
}

class Book {
    private String id;
    private String title;
    private String author;
    private String category;
    private int stock;

    public Book(String id, String title, String author, String category, int stock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.stock = stock;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

class Student {
    private String name;
    private String nim;
    private String faculty;
    private String studyProgram;
    private List<Book> borrowedBooks;
    private Scanner scanner;

    public Student(String name, String nim, String faculty, String studyProgram) {
        this.name = name;
        this.nim = nim;
        this.faculty = faculty;
        this.studyProgram = studyProgram;
        this.borrowedBooks = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void menuStudent() {
        int choice;
        do {
            System.out.println("===== Student Menu =====");
            System.out.println("1. Buku terpinjam");
            System.out.println("2. Pinjam buku");
            System.out.println("3. Pinjam Buku atau Logout");
            System.out.print("Choose option (1-3): ");
            choice = readIntegerInput();

            switch (choice) {
                case 1:
                    displayBorrowedBooks();
                    break;
                case 2:
                    borrowBook();
                    break;
                case 3:
                    System.out.println("System logout...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 3);
    }

    public int readIntegerInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer.");
            scanner.next(); // consume the invalid input
        }
        return scanner.nextInt();
    }

    public void displayBooks(Book[] books) {
        System.out.println("Available Books:");
        for (Book book : books) {
            System.out.println(book.getId() + " (" + book.getTitle() + ") " + " (" + book.getAuthor() + ") - " + book.getStock() + " copies available");
        }
    }

    private void displayBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("You haven't borrowed any books.");
        } else {
            System.out.println("Borrowed Books:");
            for (Book book : borrowedBooks) {
                System.out.println(book.getTitle() + " (" + book.getAuthor() + ")");
            }
        }
    }

    private void borrowBook() {
        System.out.println("Enter the ID of the book you want to borrow: ");
        scanner.nextLine(); // consume the newline character
        String bookId = scanner.nextLine();
        Book bookToBorrow = null;
        for (Book book : Main.bookList) {
            if (book.getId().equals(bookId)) {
                bookToBorrow = book;
                break;
            }
        }
        if (bookToBorrow != null) {
            if (bookToBorrow.getStock() > 0) {
                borrowedBooks.add(bookToBorrow);
                bookToBorrow.setStock(bookToBorrow.getStock() - 1);
                System.out.println("Book borrowed successfully.");
            } else {
                System.out.println("Sorry, the book is currently out of stock.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    public String getName() {
        return name;
    }

    public String getNim() {
        return nim;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getStudyProgram() {
        return studyProgram;
    }
}