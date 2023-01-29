
//Старт программы будет тута

public class Main {

	public static void main(String[] args) {
		
		//Создать "3" буфера заданного типа "long" и размера "90"
		ArbitraryClass buf1 = new ArbitraryClass(90);
		ArbitraryClass buf2 = new ArbitraryClass(90);
		ArbitraryClass buf3 = new ArbitraryClass(90);

		//Вывести на экран информацию o буферах
		buf1.PrintInfo();
		buf2.PrintInfo();
		buf3.PrintInfo();
		
		//Вывести на экран первые 10 элементов буферов
		buf1.PrintFirstN(10);
		buf2.PrintFirstN(10);
		buf3.PrintFirstN(10);
		
		//вычислить функцию "max" для каждого буфера
		buf1.Max();
		buf2.Max();
		buf3.Max();
		
		//Выполнить сортировку буферов методом "Пузырька"
		buf1.Sort();
		buf2.Sort();
		buf3.Sort();
		
		//Вывести на экран первые 10 элементов буферов
		buf1.PrintFirstN(10);
		buf2.PrintFirstN(10);
		buf3.PrintFirstN(10);
		
		//Сохранить буферы в файл с использованием метода "сохранить в одну строку"
		buf1.SaveOneLine("buffer1.txt");
		buf2.SaveOneLine("buffer2.txt");
		buf3.SaveOneLine("buffer3.txt");
	}

}