
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

//����������� ��������� ���� T1 (TreeSet), �������� �������������� ������ A (Book).
//����������� ����������� ����� �������� �� �����, ������ �� �������, �������� �����������
//������� � ��������� �� ���� 1 (autor) ������ A (Book) ��������� � ���������� �������������.
//��� ����� ������� ���������� ��������� ������ -i.

public class TaskOne {
	
	//������������ ���� � ������ - ��� ���������
	private TreeSet<Book> bookSet;
	
	//�����������
	public TaskOne() {
        this.bookSet = new TreeSet<>();
    }
	
	//����� ��� ���������� ����� � ���������
    public void addBook(Book book) {
        bookSet.add(book);
    }
	
    // ����� ��� ����� ���� �� �����
    public void readFromFile(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
        	//������ �� ����� ����������� ���������, ������ �� ����� ����� �������� � ���� ������ � ��������� ";"
            String line = scanner.nextLine();
            String[] tokens = line.split("; ");

            // ������� ����� ������ Book � ��������� ��� � ���������
            Book book = new Book(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), tokens[3]);
            addBook(book);
        }
        scanner.close();
    }
    
    // ����� ��� ������ ���� ���� � ��������� �� �������
    public void printBooks() {
    	System.out.println("-------------------------------------------------------");
        for (Book book : bookSet) {
            System.out.println(book);
        }
        System.out.println("-------------------------------------------------------");
    }
    
    // ����� ��� �������� ������� ����� �� ������
    public boolean containsBookByAuthor(String author) {
        for (Book book : bookSet) {
            if (book.getAuthor().equals(author)) {
                return true;
            }
        }
        return false;
    }
    
}