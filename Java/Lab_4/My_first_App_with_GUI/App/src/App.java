import javax.swing.*;


public class App {
	
	public static void main (String[] args) {
		
		// ������� ����� � ������������� ��� ������.
		 JFrame jf = new JFrame();
		 jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 jf.setSize(400, 300);
		 jf.setVisible(true);
		 // ������� ������.
		 JPanel p = new JPanel();
		 jf.add(p);
//		 // � ������ ��������� �������� BorderLayout.
//		 p.setLayout(new BorderLayout());
//		 // � ������ ��������� ������ � ������������� ��� ��� ������� ������������.
//		 p.add(new JButton("OK"), BorderLayout.NORTH);
		
	}
	
}