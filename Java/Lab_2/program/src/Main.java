
//����� ��������� ����� ����

public class Main {

	public static void main(String[] args) {
		
		//������� "3" ������ ��������� ���� "long" � ������� "90"
		ArbitraryClass buf1 = new ArbitraryClass(90);
		ArbitraryClass buf2 = new ArbitraryClass(90);
		ArbitraryClass buf3 = new ArbitraryClass(90);

		//������� �� ����� ���������� o �������
		buf1.PrintInfo();
		buf2.PrintInfo();
		buf3.PrintInfo();
		
		//������� �� ����� ������ 10 ��������� �������
		buf1.PrintFirstN(10);
		buf2.PrintFirstN(10);
		buf3.PrintFirstN(10);
		
		//��������� ������� "max" ��� ������� ������
		buf1.Max();
		buf2.Max();
		buf3.Max();
		
		//��������� ���������� ������� ������� "��������"
		buf1.Sort();
		buf2.Sort();
		buf3.Sort();
		
		//������� �� ����� ������ 10 ��������� �������
		buf1.PrintFirstN(10);
		buf2.PrintFirstN(10);
		buf3.PrintFirstN(10);
		
		//��������� ������ � ���� � �������������� ������ "��������� � ���� ������"
		buf1.SaveOneLine("buffer1.txt");
		buf2.SaveOneLine("buffer2.txt");
		buf3.SaveOneLine("buffer3.txt");
	}

}