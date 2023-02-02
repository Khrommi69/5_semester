
package myPackage;

import java.util.Scanner;

public class Main {

	private static String inputFileName;
	private static String field1;
	
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
		System.out.print("We are here");
	}
	
	public static String enteringAutorName () {
		Scanner scan = new Scanner(System.in);
		System.out.print("Введите значение поля 1 (имя автора) -> ");
		String str = scan.nextLine();
		scan.close();
		return str;
	}
	
	public static void main(String[] args) {

		//инициализация имени файла со входными параметрами (если есть то извлечь из аргумента коммандной строки)
		initializationFileName(args);
		
		//Ввод записей из файла в коллекцию TreeSet (множество отсортированное)
//		тут метод(ы) добавления
		
		//отобразить записи в консоли
//		collection1.print();
		
		//предложить пользователю ввести значение поля 1 (String autor;)
		field1 = enteringAutorName();
		System.out.println("Поле 1 - " + field1);
		
		//Проверка наличия объекта в коллекции TreeSet с таким полем
//		тут код
		
		//Ввести записи из файла заданного параметром командной строки –i в коллекцию LinkedList.
		//Отобразить записи в консоли. 
		//Отсортировать по полю 1.
		//Отобразить записи в консоли.
		//Отсортировать по полю 3 (колво страниц) в направлении по убыванию.
		//Отобразить записи в консоли.
		//Вывести записи в файл, заданный параметром командной строки –o.
		
		//Ввести записи из файла заданного параметром командной строки –i в коллекцию TreeMap.
		//Отобразить записи в консоли.
		//Предложить пользователю ввести значение поля 1.
		//Отобразить в консоли значения остальных полей по введенному значению поля 1.
		
		
		

	}

}





//коллекция типа TreeSet() (отсортированная коллекция)
//FirstTask collection1 = new FirstTask();
//
//collection1.add(new Book("Danil", 2003, 12345, "Makeevka"));
//collection1.readFile(inputFileName);



//Book testObject = new Book("Danil", 2003, 12345, "Makeevka");
//testObject.printBook();