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
		
		// Ввести записи из файла заданного параметром командной строки –i в коллекцию T1
		TaskOne T1 = new TaskOne();
		try {
		    T1.readFromFile(inputFileName);
		    System.out.println("Файл считан в коллекцию типа TreeSet");
		} catch (IOException e) {
		    System.out.println("Ошибка при работе с файлом: " + e.getMessage());
		}
		
		//Отобразить записи в консоли
		T1.printBooks();
		
		//Предложить пользователю ввести значение поля 1 (autor)
		Scanner scanner = new Scanner(System.in);
		System.out.print("Введите автора книги: ");
		String author = scanner.nextLine();
		
		//Отобразить в консоли результат проверки наличия записи по введенному значению поля 1
		if (T1.containsBookByAuthor(author)) {
			System.out.println("Указанный Вами автор содержится в наших записях");
		}
		else {
			System.out.println("Указанный Вами автор не содержится в наших записях");
		}
		
		//-----------------------------------------------------------------------------------------
		System.out.println();
		
		//Ввести записи из файла, заданного параметром командной строки –i в коллекцию LinkedList
		TaskTwo T2 = new TaskTwo();
		try {
		    T2.readFromFile(inputFileName);
		    System.out.println("Файл считан в коллекцию типа linkedList");
		} catch (IOException e) {
		    System.out.println("Ошибка при работе с файлом: " + e.getMessage());
		}
		
		//Отобразить записи в консоли
		T2.printToConsole();
		
		//Отсортировать по полю 1 (autor)
		T2.sortByAuthor();
		System.out.println("*Сортировка по полю autor*");
		
		//Отобразить записи в консоли
		T2.printToConsole();
		
		//Отсортировать по полю P (pageCount) в направлении U (по убыванию)
		T2.sortByPageCount();
		System.out.println("*Сортировка по полю кол-во страниц (по убыванию)*");
		
		//Отобразить записи в консоли
		T2.printToConsole();
		
		//Вывести записи в файл, заданный параметром командной строки –o
		try {
			T2.saveToFile(outputFileName);
			System.out.println("Данные сохранены в файл");
			
		} catch (IOException e) {
		    System.out.println("Ошибка при работе с файлом: " + e.getMessage());
		}
		
		//-----------------------------------------------------------------------------------------
		System.out.println();
		
		//Ввести записи из файла, заданного параметром командной строки –i в коллекцию T2
		TaskThree T3 = new TaskThree();
		try {
		    T3.readFromFile(inputFileName);
		    System.out.println("Файл считан в коллекцию типа TreeMap");
		} catch (IOException e) {
		    System.out.println("Ошибка при работе с файлом: " + e.getMessage());
		}
		
		//Отобразить записи в консоли
		T3.printToConsole();
		
		//Предложить пользователю ввести значение поля 1.
		//Отобразить в консоли значения остальных полей по введенному значению поля 1.
		T3.printBookByAuthor();

		//-----------------------------------------------------------------------------------------
		
		try {
		    TimeUnit.SECONDS.sleep(100); // заснуть на 100 секунд
		} catch (InterruptedException e) {
			System.out.println("Ошибка");
		}
		
		scanner.close();
		return;
	}

}