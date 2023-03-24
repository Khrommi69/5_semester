
import java.io.*;
import java.util.*;

public class TaskTwo {
	
    private LinkedList<Book> list;

    public TaskTwo() {
        list = new LinkedList<>();
    }

    public void addBook(Book book) {
        list.add(book);
    }

    public void sortByAuthor() {
        Collections.sort(list);
    }

    public void sortByPageCount() {
        Collections.sort(list, new BookComparator());
    }

    public void readFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split("; ");
            String author = data[0].trim();
            int year = Integer.parseInt(data[1].trim());
            int pageCount = Integer.parseInt(data[2].trim());
            String publisher = data[3].trim();
            addBook(new Book(author, year, pageCount, publisher));
        }
        reader.close();
    }

    public void printToConsole() {
    	System.out.println("-------------------------------------------------------");
        for (Book book : list) {
            System.out.println(book);
        }
        System.out.println("-------------------------------------------------------");
    }

    public void saveToFile(String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (Book book : list) {
            writer.write(book.getAuthor() + "; " + book.getYear() + "; " + book.getPageCount() + "; " + book.getPublisher());
            writer.newLine();
        }
        writer.close();
    }
}