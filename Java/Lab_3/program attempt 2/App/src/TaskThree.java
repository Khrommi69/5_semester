import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

//����������� ��������� ���� �2 (TreeMap) �������� ������ A (Book) c ������ �� �������� ���� 1 (autor), 
//� ������������ ����� ��������� �� �����, ������ �� ������� � ���� ����� -> ���������,
//������ �������� ����� �� ���������� � ������� �������� ���� 1 (autor). 
//��� ����� ������� ���������� ��������� ������ �i.

public class TaskThree {
	
    private TreeMap<String, Book> booksByAuthor;

    public TaskThree () {
        booksByAuthor = new TreeMap<>();
    }

    public void readFromFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split("; ");
            String author = parts[0];
            int year = Integer.parseInt(parts[1]);
            int pageCount = Integer.parseInt(parts[2]);
            String publisher = parts[3];
            Book book = new Book(author, year, pageCount, publisher);
            booksByAuthor.put(author, book);
        }
        scanner.close();
    }

    public void printToConsole() {
    	System.out.println("-------------------------------------------------------");
        for (Map.Entry<String, Book> entry : booksByAuthor.entrySet()) {
            String author = entry.getKey();
            Book book = entry.getValue();
            System.out.println(author + " -> " + book.toString());
        }
        System.out.println("-------------------------------------------------------");
    }

    public void printBookByAuthor() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("������� ��� ������: ");
        String author = scanner.nextLine();
        Book book = booksByAuthor.get(author);
        if (book == null) {
            System.out.println("����� �� �������");
        } else {
            System.out.println(book.toString());
        }
        scanner.close();
    }
}