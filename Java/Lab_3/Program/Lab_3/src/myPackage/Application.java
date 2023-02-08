package myPackage;

import java.util.TreeSet;
import java.util.Scanner;

public class Application {

	protected static String inputFileName;
	protected static String outputFileName;
	
	public static boolean initFileNames (String[] args) {
		if (args.length > 0) {
			try {
				//проходимся по аргументам cmd. Если есть -i, то след. будет имя файла
				for (int i = 0; i < args.length; i++) {
					if (args[i].equals("-i")) {
						inputFileName = args[++i];
					}
					if (args[i].equals("-o")) {
						outputFileName = args[++i];
					}
				}
				return true;
			}
			catch (Exception ex) {
				System.out.println("ERROR: работа с аргументами коммандной строки");
				System.out.println(ex.getMessage());
				return false;
			}
		}
		else {
			System.out.println("Аргументы коммандной строки отсутствуют");
			return false;
		}
	}
	
	
	public static void main(String[] args) {
		
		//извлечение аргументов командной строки (инициализация имён файлов со входными и выходными данными)
		if (!initFileNames(args)) {
//			return;
		}
		
		//Создание первой коллекции TreeSet
		TreeSet<Book> collection1 = new TreeSet<Book> (new BookComparator());
		
		//(1) Ввести записи из файла заданного параметром командной строки –i в коллекцию T1
		readInfo(inputFileName);
		
		
		collection1.add(new Book("Danil", 2003, 1, "Natalya"));
		
		
	}
}
