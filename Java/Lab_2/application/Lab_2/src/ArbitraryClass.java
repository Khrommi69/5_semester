import Interfaces.*;
import java.io.*;

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
		System.out.println("Buf_id: " + getBudID());
		System.out.println("Buf_type: " + "long");
		System.out.println("Buf_size: " + bufSize);
	}
	
	//выводит на экран содержимое буфера
	@Override
	public void Print() {
		for (int i = 0; i < bufSize; i++) {
			System.out.print(buffer[i] + " ");
		}
		System.out.println();
	}
	
	//выводит на экран первые n элементов буфера
	@Override
	public void PrintFirstN(int n) {
		for (int i = 0; i < n; i++) {
			System.out.print(buffer[i] + " ");
		}
		System.out.println();
	}
	
	//выводит на экран последние n элементов буфера
	@Override
	public void PrintLastN(int n) {
		for (int i = bufSize-n; i < bufSize; i++) {
			System.out.print(buffer[i] + " ");
		}
		System.out.println();
	}
	
	//описывает метод для сортировки массива
	@Override
	public void Sort() {
		long temp;
		boolean isSorted = false;
		while(!isSorted) {
			isSorted = true;
			for (int i = 1; i < buffer.length; i++) {
				if (buffer[i] < buffer[i-1]) {
					temp = buffer[i];
					buffer[i] = buffer[i-1];
					buffer[i-1] = temp;
					isSorted = false;
				}
			}
		}
	}
	
	//вычисляет максимальный элемент буфера
	@Override
	public void Max() {
		long max = buffer[0];
		for (int i = 1; i < bufSize; i++) {
			if (buffer[i] > max) {
				max = buffer[i];
			}
		}
		System.out.println("Max buf element = " + max);
	}
	
	//вычисляет минимальный элемент буфера
	@Override
	public void Min() {
		long min = buffer[0];
		for (int i = 1; i < bufSize; i++) {
			if (buffer[i] < min) {
				min = buffer[i];
			}
		}
		System.out.println("Минимальный элемент буфера = " + min);
	}
	
	//вычисляет сумму элементов буферa
	@Override
	public void Sum() {
		long sum = 0;
		for (int i = 0; i < bufSize; i++) {
			sum += buffer[i];
		}
		System.out.println("Сумма элементов буфера = " + sum);
	}
	
	//сохраняет буфер в файл в одну строку
	@Override
	public void SaveOneLine(String filename) {
		try {
			File file = new File(filename);
			PrintWriter pw = new PrintWriter(file);
			for (int i = 0; i < buffer.length; i++) {
				pw.print(buffer[i] + " ");
			}
			pw.close();
		}
		catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	//сохраняет буфер в файл по одному элементу в строке
	@Override
	public void SaveSeparateLines (String filename) {
		try {
			File file = new File(filename);
			PrintWriter pw = new PrintWriter(file);
			for (int i = 0; i < buffer.length; i++) {
				pw.println(buffer[i]);
			}
			pw.close();
		}
		catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

}