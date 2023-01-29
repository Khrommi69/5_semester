
//абстрактный класс CBuffer
abstract class CBuffer {
	
	//protected - Доступ к полям класса имеют только методы этого класса и методы его потомков
	//(на самом деле будут ещё и иметь методы классов в данном пакете)
	protected int bufID;        //уникальный идентификатор буфера
	protected int bufSize;      //макисмальный разер буфера
	protected static int bufCount = 0; //кол-во созданных буферов (изначально 0) (общая переменная для всех объектов класса т.к. static)
	
	//конструктор выполняющий инициализацию всех полей
	CBuffer(int bufSize) {
		this.bufSize = bufSize;
		bufCount++;
		bufID = bufCount;
	}
	
	
	//Для организации доступа к нашим полям из других классов реализованы следующие методы
	public int getBufCount() {
		return bufCount;
	}
	
	public int getBudID() {
		return bufID;
	}
	
	//абстрактный метод Generate()
	abstract void Generate();
	
}