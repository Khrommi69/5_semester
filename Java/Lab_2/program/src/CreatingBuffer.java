//импорт класса рандом для создания случайных чисел
import java.util.Random;
//import java.util.Arrays;


//дочерний клас для создания буфера, хранящего значения типа long
public class CreatingBuffer extends CBuffer {
	
	private long [] buffer; //массив значений
	
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
	
	//добавил метод взятия элемента массва по индексу, чтобы иметь доступ к элементам массива
	public long getBufElement(int index) {
		return buffer[index];
	}
	
//	//добавил метод взятия копии нашего буфера для ограничения доступа к исходному буферу, но в тоже время для работы с ним
//    public long[] getBuffer() {
//        return Arrays.copyOf(buffer, buffer.length);
//    }
}