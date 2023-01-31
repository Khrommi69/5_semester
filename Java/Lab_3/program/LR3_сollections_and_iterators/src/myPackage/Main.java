
package myPackage;

import java.util.Scanner;

public class Main {

	private static String inputFileName;
	
	//попытка извлечь из аргументов командной строки имя файла, иначе ручками будем вбивать
	public static void initializationFileName(String[] args) {
		//если в параметрах коммандной строки есть -i и имя файла с инфой, то юзать его
				if (args.length > 0) {
					try {
						//проходимся по аргументам cmd. Если есть -i, то след. будет имя файла
						for (int i = 0; i < args.length; i++) {
							if (args[i].equals("-i")) {
								inputFileName = args[++i];
							}
						}
					}
					catch (Exception ex) {
						//если че то случилось при работе с аргументами коммандной строки, то вбиваем имя файла ручками
						System.out.println(ex.getMessage());
						System.out.println("Ошибка при работе с аргументами коммандной строки");
						readFileName();
					}
				}
				//иначе вводить ручками имя текстового файла
				else {
					readFileName();
				}
	}
	
	//Метод для ввода ручками имени файла с нашей информацией
	public static void readFileName () {
		Scanner scan = new Scanner(System.in);
		System.out.print("Введите имя файла со входными данными -> ");
		inputFileName = scan.nextLine();
		scan.close();
	}
	
	public static void main(String[] args) {

//		initializationFileName(args);
		
		//коллекция типа TreeSet() (отсортированная коллекция)
		FirstTask collection1 = new FirstTask();

		collection1.add(new Book("Danil", 2003, 12345, "Makeevka"));
//		collection1.readFile(inputFileName);
//		collection1.print();
	}

}



//Book testObject = new Book("Danil", 2003, 12345, "Makeevka");
//testObject.printBook();



//Реализовать коллекцию типа TreeSet объектов класса Book
//с возможностью ввода элементов из файла,
//(метод который берёт на вход коллекцию и файл, и переносит данные из файла в коллекцию)
//вывода на консоль,
//(Вывод всех элементов коллекции на экран)