import java.util.*;
class Admin extends User {
    public List<Student> studentList;
    private final Scanner scanner;

    public Admin() {
        studentList = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void addStudent() {
        scanner.nextLine();

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        String nim;
        while (true) {
            System.out.print("Enter student NIM: ");
            nim = scanner.nextLine();
            if (nim.length() == 15) {
                break;
            }
            System.out.println("NIM must be 15 digits.");
        }

        System.out.print("Enter student faculty: ");
        String faculty = scanner.nextLine();

        System.out.print("Enter student study program: ");
        String studyProgram = scanner.nextLine();

        studentList.add(new Student(name, nim, faculty, studyProgram));
        System.out.println("Student successfully registered.");
    }

    public void inputBook() {
        System.out.println("Select book category:");
        System.out.println("1. History Book");
        System.out.println("2. Story Book");
        System.out.println("3. Text Book");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        System.out.print("Enter book author: ");
        String author = scanner.nextLine();

        System.out.print("Enter book stock: ");
        int stock = scanner.nextInt();

        String bookId = generateId();

        switch(choice) {
            case 1:
                Main.getBookList().add(new HistoryBook(bookId, title, author, "History", stock));
                break;
            case 2:
                Main.getBookList().add(new StoryBook(bookId, title, author, "Story", stock));
                break;
            case 3:
                Main.getBookList().add(new TextBook(bookId, title, author, "Text", stock));
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.println("Book added successfully. ID: " + bookId);
    }

    @Override
    public void displayBooks() {
        super.displayBooks();
    }

    public void displayStudent() {
        System.out.println("Registered Students:");
        for (Student student : studentList) {
            student.displayInfo();
            System.out.println();
        }
    }

    public boolean isAdmin(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }

    private String generateId() {
        return "B" + (Main.getBookList().size() + 1);
    }

    public void menuAdmin() {
        int choice;
        do {
            System.out.println("===== Admin Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display Registered Students");
            System.out.println("3. Input Book");
            System.out.println("4. Display Books");
            System.out.println("5. Logout");
            System.out.print("Choose option (1-5): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    displayStudent();
                    break;
                case 3:
                    inputBook();
                    break;
                case 4:
                    displayBooks();
                    break;
                case 5:
                    System.out.println("Logging out from admin account.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 5);
    }
}