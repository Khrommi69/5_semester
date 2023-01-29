package Interfaces;

//Интерфейс описывающий методы вывода на экран

public interface IBufferPrintable {
	//выводит на экран идентификатор, тип и размер буфера
	public void PrintInfo();
	//выводит на экран содержимое буфера
	public void Print();
	//выводит на экран первые n элементов буфера
	public void PrintFirstN(int n);
	//выводит на экран последние n элементов буфера
	public void PrintLastN(int n);
}