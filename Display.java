package view;

import model.Book;
import java.util.List;

public class Display {
    public void showList(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("Жодної книги не знайдено!");
        } else {
            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
            books.forEach(System.out::println);
            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        }
    }
}
