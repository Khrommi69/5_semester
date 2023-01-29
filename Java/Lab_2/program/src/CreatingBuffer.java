//импорт класса рандом для создания случайных чисел
import java.util.Random;


//дочерний клас для создания буфера, хранящего значения типа long
public class CreatingBuffer extends CBuffer {
	
	protected long [] buffer; //массив значений
	
	//конструктор класса CreatingBuffer
	CreatingBuffer(int bufSize) {
		super(bufSize);
		buffer = new long[bufSize];
		Generate();
	}
	
	@Override
	protected void Generate() {
		//использование конструктора Random() для создания генератора
		Random rand = new Random();
		//заполнение массива случайными числами типа long
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = rand.nextLong();
		}
	}
	
}