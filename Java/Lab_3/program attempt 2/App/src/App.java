import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class App {

	public static void main (String[] args) {
		
		String inputFileName = "";
		String outputFileName = "";
		if(args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				if (args[i].equals("-i")) {
					inputFileName = args[++i];
					System.out.println("Usage: -i, filename - <" + inputFileName + ">");
				}
				else {
					inputFileName = "info.txt";
				}
				if (args[i].equals("-o")) {
					outputFileName = args[++i];
					System.out.println("Usage: -o, filename - <" + outputFileName + ">");
				}
				else {
					outputFileName = "outputInfo.txt";
				}
			}
		}
		if (inputFileName.equals("")) {
			inputFileName = "info.txt";
		}
		if (outputFileName.equals("")) {
			outputFileName = "outputInfo.txt";
		}
		
		//-----------------------------------------------------------------------------------------
		
		// ������ ������ �� ����� ��������� ���������� ��������� ������ �i � ��������� T1
		TaskOne T1 = new TaskOne();
		try {
		    T1.readFromFile(inputFileName);
		    System.out.println("���� ������ � ��������� ���� TreeSet");
		} catch (IOException e) {
		    System.out.println("������ ��� ������ � ������: " + e.getMessage());
		}
		
		//���������� ������ � �������
		T1.printBooks();
		
		//���������� ������������ ������ �������� ���� 1 (autor)
		Scanner scanner = new Scanner(System.in);
		System.out.print("������� ������ �����: ");
		String author = scanner.nextLine();
		
		//���������� � ������� ��������� �������� ������� ������ �� ���������� �������� ���� 1
		if (T1.containsBookByAuthor(author)) {
			System.out.println("��������� ���� ����� ���������� � ����� �������");
		}
		else {
			System.out.println("��������� ���� ����� �� ���������� � ����� �������");
		}
		
		//-----------------------------------------------------------------------------------------
		System.out.println();
		
		//������ ������ �� �����, ��������� ���������� ��������� ������ �i � ��������� LinkedList
		TaskTwo T2 = new TaskTwo();
		try {
		    T2.readFromFile(inputFileName);
		    System.out.println("���� ������ � ��������� ���� linkedList");
		} catch (IOException e) {
		    System.out.println("������ ��� ������ � ������: " + e.getMessage());
		}
		
		//���������� ������ � �������
		T2.printToConsole();
		
		//������������� �� ���� 1 (autor)
		T2.sortByAuthor();
		System.out.println("*���������� �� ���� autor*");
		
		//���������� ������ � �������
		T2.printToConsole();
		
		//������������� �� ���� P (pageCount) � ����������� U (�� ��������)
		T2.sortByPageCount();
		System.out.println("*���������� �� ���� ���-�� ������� (�� ��������)*");
		
		//���������� ������ � �������
		T2.printToConsole();
		
		//������� ������ � ����, �������� ���������� ��������� ������ �o
		try {
			T2.saveToFile(outputFileName);
			System.out.println("������ ��������� � ����");
			
		} catch (IOException e) {
		    System.out.println("������ ��� ������ � ������: " + e.getMessage());
		}
		
		//-----------------------------------------------------------------------------------------
		System.out.println();
		
		//������ ������ �� �����, ��������� ���������� ��������� ������ �i � ��������� T2
		TaskThree T3 = new TaskThree();
		try {
		    T3.readFromFile(inputFileName);
		    System.out.println("���� ������ � ��������� ���� TreeMap");
		} catch (IOException e) {
		    System.out.println("������ ��� ������ � ������: " + e.getMessage());
		}
		
		//���������� ������ � �������
		T3.printToConsole();
		
		//���������� ������������ ������ �������� ���� 1.
		//���������� � ������� �������� ��������� ����� �� ���������� �������� ���� 1.
		T3.printBookByAuthor();

		//-----------------------------------------------------------------------------------------
		
		try {
		    TimeUnit.SECONDS.sleep(100); // ������� �� 100 ������
		} catch (InterruptedException e) {
			System.out.println("������");
		}
		
		scanner.close();
		return;
	}

}