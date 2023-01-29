import Interfaces.*;
import java.io.*;

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
		System.out.println("������ ������: " + bufSize);
	}
	
	//������� �� ����� ���������� ������
	@Override
	public void Print() {
		for (int i = 0; i < bufSize; i++) {
			System.out.print(buffer[i] + " ");
		}
		System.out.println();
	}
	
	//������� �� ����� ������ n ��������� ������
	@Override
	public void PrintFirstN(int n) {
		for (int i = 0; i < n; i++) {
			System.out.print(buffer[i] + " ");
		}
		System.out.println();
	}
	
	//������� �� ����� ��������� n ��������� ������
	@Override
	public void PrintLastN(int n) {
		for (int i = bufSize-n; i < bufSize; i++) {
			System.out.print(buffer[i] + " ");
		}
		System.out.println();
	}
	
	//��������� ����� ��� ���������� �������
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
	
	//��������� ������������ ������� ������
	@Override
	public void Max() {
		long max = buffer[0];
		for (int i = 1; i < bufSize; i++) {
			if (buffer[i] > max) {
				max = buffer[i];
			}
		}
		System.out.println("������������ ������� ������ = " + max);
	}
	
	//��������� ����������� ������� ������
	@Override
	public void Min() {
		long min = buffer[0];
		for (int i = 1; i < bufSize; i++) {
			if (buffer[i] < min) {
				min = buffer[i];
			}
		}
		System.out.println("����������� ������� ������ = " + min);
	}
	
	//��������� ����� ��������� �����a
	@Override
	public void Sum() {
		long sum = 0;
		for (int i = 0; i < bufSize; i++) {
			sum += buffer[i];
		}
		System.out.println("����� ��������� ������ = " + sum);
	}
	
	//��������� ����� � ���� � ���� ������
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
	
	//��������� ����� � ���� �� ������ �������� � ������
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