package controller;

import model.Book;
import java.util.*;
import java.util.stream.Collectors;

public class LibraryManager {
    private final List<Book> library;

    public LibraryManager(List<Book> library) {
        this.library = library;
    }

    public List<Book> findByWriter(String writerName) {
        return library.stream()
                .filter(b -> b.getWriter().equalsIgnoreCase(writerName))
                .collect(Collectors.toList());
    }

    public List<Book> findByPublisher(String publisherName) {
        return library.stream()
                .filter(b -> b.getPublisher().equalsIgnoreCase(publisherName))
                .collect(Collectors.toList());
    }

    public List<Book> releasedAfter(int year) {
        return library.stream()
                .filter(b -> b.getPublishYear() > year)
                .collect(Collectors.toList());
    }

    public List<Book> sortByPublisher() {
        List<Book> sortedList = new ArrayList<>(library);
        sortedList.sort(Comparator.comparing(Book::getPublisher));
        return sortedList;
    }

    public List<Book> getAllBooks() {
        return library;
    }
}
