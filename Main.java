import controller.FileHandler;
import controller.LibraryManager;
import model.Book;
import view.Display;

import java.util.*;

public class Main {

    private static ResourceBundle bundle;
    private static Locale currentLocale;

    public static void main(String[] args) {

        currentLocale = new Locale("ua");
        bundle = ResourceBundle.getBundle("location.messages", currentLocale);

        List<Book> library = new ArrayList<>(List.of(
                new Book("Кайдашева сім’я", "І.Нечуй-Левицький", "Основи", 1879, 220, 230.0),
                new Book("Маленький принц", "А.де Сент-Екзюпері", "Gallimard", 1943, 96, 180.0),
                new Book("451° за Фаренгейтом", "Р.Бредбері", "HarperCollins", 1953, 210, 260.0),
                new Book("Портрет Доріана Грея", "О.Вайльд", "Vintage Books", 1890, 288, 270.0),
                new Book("Сто років самотності", "Г.Г.Маркес", "Folio", 1967, 432, 340.0)
        ));

        LibraryManager manager = new LibraryManager(library);
        Display screen = new Display();
        Scanner input = new Scanner(System.in);
        int option;

        do {
            printMenu();

            try {
                System.out.print(bundle.getString("input.choice") + " ");
                option = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(bundle.getString("error.number"));
                option = -1;
                continue;
            }

            switch (option) {
                case 1 -> {
                    System.out.print(bundle.getString("input.author") + " ");
                    String a = input.nextLine();
                    screen.showList(manager.findByWriter(a));
                }
                case 2 -> {
                    System.out.print(bundle.getString("input.publisher") + " ");
                    String pub = input.nextLine();
                    screen.showList(manager.findByPublisher(pub));
                }
                case 3 -> {
                    try {
                        System.out.print(bundle.getString("input.year") + " ");
                        int y = Integer.parseInt(input.nextLine());
                        screen.showList(manager.releasedAfter(y));
                    } catch (NumberFormatException e) {
                        System.out.println(bundle.getString("error.number"));
                    }
                }
                case 4 -> screen.showList(manager.sortByPublisher());
                case 5 -> {
                    System.out.print("Path: ");
                    String path = input.nextLine();
                    FileHandler.saveToFile(manager.getAllBooks(), path);
                }
                case 6 -> {
                    System.out.print("Path: ");
                    String path = input.nextLine();
                    List<Book> loaded = FileHandler.loadFromFile(path);
                    if (!loaded.isEmpty()) {
                        library.clear();
                        library.addAll(loaded);
                        screen.showList(library);
                    }
                }
                case 11 -> changeLanguage();
                case 0 -> System.out.println(bundle.getString("menu.exit"));
                default -> System.out.println("???");
            }

        } while (option != 0);
    }

    private static void printMenu() {
        System.out.println("\n" + bundle.getString("menu.title"));
        System.out.println("1 — " + bundle.getString("menu.option1"));
        System.out.println("2 — " + bundle.getString("menu.option2"));
        System.out.println("3 — " + bundle.getString("menu.option3"));
        System.out.println("4 — " + bundle.getString("menu.option4"));
        System.out.println("5 — " + bundle.getString("menu.option5"));
        System.out.println("6 — " + bundle.getString("menu.option6"));
        System.out.println("7 — " + bundle.getString("menu.option7"));
        System.out.println("8 — " + bundle.getString("menu.option8"));
        System.out.println("9 — " + bundle.getString("menu.option9"));
        System.out.println("10 — " + bundle.getString("menu.option10"));
        System.out.println("11 — " + bundle.getString("menu.option11"));
        System.out.println("0 — " + bundle.getString("menu.exit"));
    }

    private static void changeLanguage() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n1 — Українська");
        System.out.println("2 — English");
        System.out.print(">> ");

        int ch = Integer.parseInt(sc.nextLine());

        if (ch == 1) {
            currentLocale = new Locale("ua");
        } else if (ch == 2) {
            currentLocale = new Locale("en");
        }

        bundle = ResourceBundle.getBundle("location.messages", currentLocale);
        System.out.println("✔ Language changed\n");
    }
}
