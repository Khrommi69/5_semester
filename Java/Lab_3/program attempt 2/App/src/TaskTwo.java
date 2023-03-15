
import java.io.*;
import java.util.*;

//–еализовать коллекцию типа LinkedList объектов класса A (Book) с возможностью: упор€дочивани€ по 
//полю 1 (autor) (использовать Collections.sort(list)); с возможностью упор€дочивани€ по полю P (pageCount)
//в направлении U (по убыванию) класса (использовать Collections.sort(list, myComp), где myComp Ц 
//экземпл€р разработанного класса, реализующего интерфейс Comparator); с возможностью ввода элементов из файла,
//вывода на консоль и сохранени€ в файл. »мена файлов вводить параметрами командной строки Цi и Цo.

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
        	//метод split раздел€ет строку на элементы через разделитель
        	//метод trim обрезает пробелы до и после строки
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
