package Interfaces;

//��������� ������ ��� �������� ������ � ��������� ����

public interface IBufferStorable {
	//��������� ����� � ���� � ���� ������
	public void SaveOneLine(String filename);
	//��������� ����� � ���� �� ������ �������� � ������
	public void SaveSeparateLines (String filename);
}