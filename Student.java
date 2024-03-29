import java.util.*;
class Student extends User {
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
        borrowedBooks = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("NIM: " + nim);
        System.out.println("Faculty: " + faculty);
        System.out.println("Study Program: " + studyProgram);
    }

    public void showBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("No borrowed books.");
        } else {
            System.out.println("Borrowed Books:");
            for (Book book : borrowedBooks) {
                System.out.println(book.getTitle() + " (" + book.getAuthor() + ")");
            }
        }
    }

    public void logout() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("Logging out...");
        } else {
            System.out.println("You have borrowed books. Do you want to:");
            System.out.println("1. Cancel borrowing");
            System.out.println("2. Proceed with borrowing");
            int choice = scanner.nextInt();

            if (choice == 1) {
                for (Book book : borrowedBooks) {
                    book.returnBook();
                }
                borrowedBooks.clear();
                System.out.println("Borrowing cancelled.");
            } else {
                System.out.println("Proceeding with borrowing...");
            }
        }
        System.out.println("Logging out...");
    }

    @Override
    public void displayBooks() {
        super.displayBooks();
        System.out.print("Enter the ID of the book to borrow: ");
        String bookId = scanner.next();

        for (Book book : Main.getBookList()) {
            if (book.getBookId().equals(bookId)) {
                if (book.getStock() > 0) {
                    int duration;
                    do {
                        System.out.print("Enter number of days to borrow (max 14 days): ");
                        duration = scanner.nextInt();
                        if (duration <= 0 || duration > 14) {
                            System.out.println("Invalid duration. Please enter a value between 1 and 14.");
                        }
                    } while (duration <= 0 || duration > 14);

                    book.borrowBook();
                    book.setDuration(duration);
                    borrowedBooks.add(book);
                    System.out.println("Book borrowed successfully.");
                } else {
                    System.out.println("Book is out of stock.");
                }
                return;
            }
        }

        System.out.println("Invalid book ID.");
    }

    public void returnBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("No books to return.");
        } else {
            for (Book book : borrowedBooks) {
                book.returnBook();
            }
            borrowedBooks.clear();
            System.out.println("All books returned successfully.");
        }
    }

    public void menuStudent() {
        int choice;
        do {
            System.out.println("===== Student Menu =====");
            System.out.println("1. Display Borrowed Books");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Books");
            System.out.println("4. Logout");
            System.out.print("Choose option (1-4): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showBorrowedBooks();
                    break;
                case 2:
                    displayBooks();
                    break;
                case 3:
                    returnBooks();
                    break;
                case 4:
                    logout();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 4);
    }
}