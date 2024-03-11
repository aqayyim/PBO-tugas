import java.util.Scanner;

public class Main {
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin";
    private static final int MAX_NIM_LENGTH = 15;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Library System");
            System.out.println("1. Login as Student");
            System.out.println("2. Login as Admin");
            System.out.println("3. Exit");
            System.out.println("=====");
            System.out.print("Choose option (1-3): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter your NIM: ");
                    String nim = scanner.next();
                    if (nim.length() == MAX_NIM_LENGTH) {
                        System.out.println("Successful Login as Student");
                    } else {
                        System.out.println("Invalid NIM");
                    }
                    break;
                case 2:
                    System.out.print("Enter your username (admin): ");
                    String username = scanner.next();
                    System.out.print("Enter your password (admin): ");
                    String password = scanner.next();
                    if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
                        System.out.println("Successful Login as Admin");
                    } else {
                        System.out.println("Admin User Not Found");
                    }
                    break;
                case 3:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid option");
            }
            System.out.println();
        }

        System.out.println("adios");
    }
}