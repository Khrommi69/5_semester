
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

//Реализовать коллекцию типа T1 (TreeSet), объектов разработанного класса A (Book).
//Реализовать возможность ввода объектов из файла, вывода на консоль, проверки присутствия
//объекта в коллекции по полю 1 (autor) класса A (Book) вводимого с клавиатуры пользователем.
//Имя файла вводить параметром командной строки -i.

public class TaskOne {
	
	//единственное поле в классе - это коллекция
	private TreeSet<Book> bookSet;
	
	//конструктор
	public TaskOne() {
        this.bookSet = new TreeSet<>();
    }
	
	//Метод для добавления книги в коллекцию
    public void addBook(Book book) {
        bookSet.add(book);
    }
	
    // Метод для ввода книг из файла
    public void readFromFile(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
        	//данные из файла считываются построчно, данные об одной книге записаны в одну строку и разделены ";"
            String line = scanner.nextLine();
            String[] tokens = line.split("; ");

            // Создаем новый объект Book и добавляем его в коллекцию
            Book book = new Book(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), tokens[3]);
            addBook(book);
        }
        scanner.close();
    }
    
    // Метод для вывода всех книг в коллекции на консоль
    public void printBooks() {
    	System.out.println("-------------------------------------------------------");
        for (Book book : bookSet) {
            System.out.println(book);
        }
        System.out.println("-------------------------------------------------------");
    }
    
    // Метод для проверки наличия книги по автору
    public boolean containsBookByAuthor(String author) {
        for (Book book : bookSet) {
            if (book.getAuthor().equals(author)) {
                return true;
            }
        }
        return false;
    }
    
}