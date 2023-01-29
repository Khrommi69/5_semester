import Interfaces.*;

//������������ �����, �������������� �� ������ CreatingBuffer
//����������� ������ ����������� ����������� ��� ���������� ������� � ������������ � ���������

public class ArbitraryClass extends CreatingBuffer implements IBufferComputable, IBufferPrintable, IBufferSortable, IBufferStorable {
	
	//����������� � ����������
	ArbitraryClass(int bufSize) {
		super(bufSize);
	}
	
	//������� �� ����� �������������, ��� � ������ ������
	@Override
	public void PrintInfo() {
		System.out.println("������������� ������: " + getBudID());
		System.out.println("��� ������: " + "long");
		System.out.println("������ ������: " + getBufSize());
	}
	
	//������� �� ����� ���������� ������
	@Override
	public void Print() {
		for (int i = 0; i < bufSize; i++) {
			System.out.print(getBufElement(i) + " ");
		}
		System.out.println();
	}
	
	//������� �� ����� ������ n ��������� ������
	@Override
	public void PrintFirstN(int n) {
		
	}
	
	//������� �� ����� ��������� n ��������� ������
	@Override
	public void PrintLastN(int n) {
		
	}
	
	//��������� ����� ��� ���������� �������
	@Override
	public void Sort() {
		
	}
	
	//��������� ������������ ������� ������
	@Override
	public void Max() {
		
	}
	
	//��������� ����������� ������� ������
	@Override
	public void Min() {
		
	}
	
	//��������� ����� ��������� �����a
	@Override
	public void Sum() {
		
	}
	
	//��������� ����� � ���� � ���� ������
	@Override
	public void SaveOneLine(String filename) {
		
	}
	
	//��������� ����� � ���� �� ������ �������� � ������
	@Override
	public void SaveSeparateLines (String filename) {
		
	}
	
}