package Interfaces;

//��������� ����������� ������ ������ �� �����

public interface IBufferPrintable {
	//������� �� ����� �������������, ��� � ������ ������
	public void PrintInfo();
	//������� �� ����� ���������� ������
	public void Print();
	//������� �� ����� ������ n ��������� ������
	public void PrintFirstN(int n);
	//������� �� ����� ��������� n ��������� ������
	public void PrintLastN(int n);
}