import java.util.*;
class User {
    protected List<Book> bookList;

    public User() {
        bookList = Main.getBookList();
    }

    public void displayBooks() {
        System.out.println("Available Books:");
        for (Book book : Main.getBookList()) {
            System.out.println(book.getBookId() + " (" + book.getTitle() + ") " + " (" + book.getAuthor() + ") - " + book.getStock() + " stocks available");
        }
    }
}