
//����������� ����� CBuffer
abstract class CBuffer {
	
	//protected - ������ � ����� ������ ����� ������ ������ ����� ������ � ������ ��� ��������
	//(�� ����� ���� ����� ��� � ����� ������ ������� � ������ ������)
	protected int bufID;        //���������� ������������� ������
	protected int bufSize;      //������������ ����� ������
	protected static int bufCount = 0; //���-�� ��������� ������� (���������� 0) (����� ���������� ��� ���� �������� ������ �.�. static)
	
	//����������� ����������� ������������� ���� �����
	CBuffer(int bufSize) {
		this.bufSize = bufSize;
		bufCount++;
		bufID = bufCount;
	}
	
	
	//��� ����������� ������� � ����� ����� �� ������ ������� ����������� ��������� ������
	public int getBufCount() {
		return bufCount;
	}
	
	public int getBudID() {
		return bufID;
	}
	
	//����������� ����� Generate()
	abstract void Generate();
	
}