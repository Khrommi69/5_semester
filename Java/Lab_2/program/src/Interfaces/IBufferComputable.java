package Interfaces;

//Интерфейс описывает методы для вычисления статистики значений буфера

public interface IBufferComputable {
	//вычисляет максимальный элемент буфера
	public void Max();
	//вычисляет минимальный элемент буфера
	public void Min();
	//вычисляет сумму элементов буферa
	public void Sum();
}
