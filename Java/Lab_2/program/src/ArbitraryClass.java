import Interfaces.*;

//произвольный класс, унаследованный от класса CreatingBuffer
//реализующий методы интерфейсов необходимых для выполнения задания в соответствии с вариантом

public class ArbitraryClass extends CreatingBuffer implements IBufferComputable, IBufferPrintable, IBufferSortable, IBufferStorable {
	
	//конструктор с параметром
	ArbitraryClass(int bufSize) {
		super(bufSize);
	}
	
	//выводит на экран идентификатор, тип и размер буфера
	@Override
	public void PrintInfo() {
		System.out.println("Идентификатор буфера: " + getBudID());
		System.out.println("Тип буфера: " + "long");
		System.out.println("Размер буфера: " + getBufSize());
	}
	
	//выводит на экран содержимое буфера
	@Override
	public void Print() {
		for (int i = 0; i < bufSize; i++) {
			System.out.print(getBufElement(i) + " ");
		}
		System.out.println();
	}
	
	//выводит на экран первые n элементов буфера
	@Override
	public void PrintFirstN(int n) {
		
	}
	
	//выводит на экран последние n элементов буфера
	@Override
	public void PrintLastN(int n) {
		
	}
	
	//описывает метод для сортировки массива
	@Override
	public void Sort() {
		
	}
	
	//вычисляет максимальный элемент буфера
	@Override
	public void Max() {
		
	}
	
	//вычисляет минимальный элемент буфера
	@Override
	public void Min() {
		
	}
	
	//вычисляет сумму элементов буферa
	@Override
	public void Sum() {
		
	}
	
	//сохраняет буфер в файл в одну строку
	@Override
	public void SaveOneLine(String filename) {
		
	}
	
	//сохраняет буфер в файл по одному элементу в строке
	@Override
	public void SaveSeparateLines (String filename) {
		
	}
	
}