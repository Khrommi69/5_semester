
public class Book implements Comparable<Book> {
    private String author;
    private int year;
    private int pageCount;
    private String publisher;

    public Book(String author, int year, int pageCount, String publisher) {
        this.author = author;
        this.year = year;
        this.pageCount = pageCount;
        this.publisher = publisher;
    }

    // Метод для сравнения книг по автору
    @Override
    public int compareTo(Book other) {
        return this.author.compareTo(other.author);
    }

    // Метод для вывода информации о книге на консоль
    @Override
    public String toString() {
        return author + " " + year + " " + pageCount + " " + publisher;
    }
    
    
    // геттеры и сеттеры для всех полей

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
