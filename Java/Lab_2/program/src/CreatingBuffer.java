//������ ������ ������ ��� �������� ��������� �����
import java.util.Random;


//�������� ���� ��� �������� ������, ��������� �������� ���� long
public class CreatingBuffer extends CBuffer {
	
	protected long [] buffer; //������ ��������
	
	//����������� ������ CreatingBuffer
	CreatingBuffer(int bufSize) {
		super(bufSize);
		buffer = new long[bufSize];
		Generate();
	}
	
	@Override
	protected void Generate() {
		//������������� ������������ Random() ��� �������� ����������
		Random rand = new Random();
		//���������� ������� ���������� ������� ���� long
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = rand.nextLong();
		}
	}
	
}