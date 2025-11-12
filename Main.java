import controller.FileHandler;
import controller.LibraryManager;
import model.Book;
import view.Display;
import java.util.*;

public class Main {
    public static void main(String[] args) {
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
            System.out.println("\nМеню:");
            System.out.println("1 — Пошук книг за автором");
            System.out.println("2 — Пошук книг за видавництвом");
            System.out.println("3 — Книги, видані після певного року");
            System.out.println("4 — Відсортувати за видавництвом");
            System.out.println("5 — Зберегти бібліотеку у файл");
            System.out.println("6 — Зчитати бібліотеку з файлу");
            System.out.println("7 — Знайти рядок із найбільшою кількістю слів у файлі");
            System.out.println("8 — Зашифрувати файл");
            System.out.println("9 — Розшифрувати файл");
            System.out.println("10 — Підрахунок частоти HTML-тегів на сторінці");
            System.out.println("0 — Вихід");
            System.out.print("Ваш вибір: ");

            try {
                option = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Введіть число!");
                option = -1;
                continue;
            }

            switch (option) {
                case 1 -> {
                    System.out.print("Введіть автора: ");
                    String a = input.nextLine();
                    screen.showList(manager.findByWriter(a));
                }
                case 2 -> {
                    System.out.print("Введіть видавництво: ");
                    String pub = input.nextLine();
                    screen.showList(manager.findByPublisher(pub));
                }
                case 3 -> {
                    try {
                        System.out.print("Введіть рік: ");
                        int y = Integer.parseInt(input.nextLine());
                        screen.showList(manager.releasedAfter(y));
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Невірний формат року!");
                    }
                }
                case 4 -> {
                    System.out.println("Відсортовані за видавництвом книги:");
                    screen.showList(manager.sortByPublisher());
                }
                case 5 -> {
                    System.out.print("Введіть шлях і назву файлу для збереження: ");
                    String path = input.nextLine();
                    FileHandler.saveToFile(manager.getAllBooks(), path);
                }
                case 6 -> {
                    System.out.print("Введіть шлях до файлу для зчитування: ");
                    String path = input.nextLine();
                    List<Book> loaded = FileHandler.loadFromFile(path);
                    if (!loaded.isEmpty()) {
                        library.clear();
                        library.addAll(loaded);
                        System.out.println("Бібліотеку оновлено з файлу.");
                        screen.showList(library);
                    }
                }
                case 7 -> {
                    System.out.print("Введіть шлях до файлу: ");
                    String path = input.nextLine();
                    String line = FileHandler.getLongestLine(path);
                    System.out.println("Рядок із найбільшою кількістю слів:");
                    System.out.println(line);
                }
                case 8 -> {
                    System.out.print("Введіть шлях вхідного файлу: ");
                    String inPath = input.nextLine();
                    System.out.print("Введіть шлях вихідного файлу: ");
                    String outPath = input.nextLine();
                    System.out.print("Введіть символ-ключ: ");
                    char key = input.nextLine().charAt(0);
                    FileHandler.encryptFile(inPath, outPath, key);
                }
                case 9 -> {
                    System.out.print("Введіть шлях зашифрованого файлу: ");
                    String inPath = input.nextLine();
                    System.out.print("Введіть шлях для розшифрованого файлу: ");
                    String outPath = input.nextLine();
                    System.out.print("Введіть символ-ключ: ");
                    char key = input.nextLine().charAt(0);
                    FileHandler.decryptFile(inPath, outPath, key);
                }
                case 10 -> {
                    System.out.print("Введіть URL сторінки: ");
                    String url = input.nextLine();
                    FileHandler.analyzeTags(url);
                }
                case 0 -> System.out.println("Програму завершено.");
                default -> System.out.println("Невірний пункт меню.");
            }
        } while (option != 0);
    }
}
