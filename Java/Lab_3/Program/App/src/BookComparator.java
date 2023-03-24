import java.util.Comparator;

class BookComparator implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        return Integer.compare(b2.getPageCount(), b1.getPageCount());
    }
}