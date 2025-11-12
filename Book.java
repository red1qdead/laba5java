package model;

import java.io.Serializable;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String writer;
    private String publisher;
    private int publishYear;
    private int pageCount;
    private double cost;

    public Book(String name, String writer, String publisher, int publishYear, int pageCount, double cost) {
        this.name = name;
        this.writer = writer;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.pageCount = pageCount;
        this.cost = cost;
    }

    public String getName() { return name; }
    public String getWriter() { return writer; }
    public String getPublisher() { return publisher; }
    public int getPublishYear() { return publishYear; }
    public int getPageCount() { return pageCount; }
    public double getCost() { return cost; }

    @Override
    public String toString() {
        return String.format("%-35s | %-22s | %-20s | %6d | %6d стор. | %8.2f грн",
                name, writer, publisher, publishYear, pageCount, cost);
    }
}
