import java.util.Arrays;
import java.util.Scanner; //��� ������ �������
import java.io.*; //��� ������ � �������

public class Main {

    public static void main(String[] args) {
        System.out.println("��������� ������� ��� ������������� ������� ������,�������� ������� �� ��������� �������� ���������� �����.");
        int number = 0;

        //���� � cmd ���� �������� ��������� ������� �� �� �����
        if (args.length > 0) {
            System.out.print("��������� ���������� ������ �� �����:");
            System.out.println(Arrays.toString(args));
        }

        //�������� �� ������� ���������� ���������� ������
        String inputFile = "";
        String outputFile = "";
        try {
            for (int i = 0; i < args.length; i++) {
                //���� ���� ���� -i �� ��������� �������� ���������� ������ ��� �������� input �����
                if (args[i].equals("-i"))
                    inputFile = args[++i];
                    //���� ���� ���� -o �� ��������� �������� ���������� ������ ��� �������� output �����
                else if (args[i].equals("-o"))
                    outputFile = args[++i];
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("������ ��� ������ � ����������� ��������� ������");
        }
        //���� ���� ���� -i ������� ������� ���� � ������� ���������
        if (!inputFile.isEmpty()) {
            try {
                //��� ��������� � input �����
                BufferedReader fileIn = new BufferedReader(new FileReader(inputFile));
                //����������� ������ �� ����� � �����
                number = Integer.parseInt(fileIn.readLine().trim());
                fileIn.close();
            }
            catch (IOException e) {
                System.err.println("������ ��� ������ � input ������ " + e);
            }
            catch (NumberFormatException e) {
                System.err.println("������ �������������� ������� ������ " + e);
                Scanner scan = new Scanner(System.in);
                System.out.print("������� ����� ������� >> ");
                number = scan.nextInt();
            }
        }
        else {
            Scanner scan = new Scanner(System.in);
            System.out.print("������� ����� >> ");
            number = scan.nextInt();
        }

        //���� ���� ���� -o ������� ������� ���� ��� ������ �����������
        if (!outputFile.isEmpty()) {
            try {
                //����� � ���� ����������� ������ ���������
                PrintWriter pw = new PrintWriter(outputFile);
                pw.println("��������� ������� ��� ������������� ������� ������,�������� ������� �� ��������� �������� ���������� �����.");
                pw.println("�������� ����� >> " + number);
                for (int i = 0; Math.pow(2, i) <= number; i++) {
                    pw.println("2^" + i + " = " + Math.pow(2, i));
                }
                pw.close();
                System.out.println("���������� ������ ��������� �������� � ����");
            } catch (IOException e) {
                System.err.println("������ ��� ������ � output ������ " + e);
            }
        }
        else {
            System.out.println("�������� ����� >> " + number);
            for (int i = 0; Math.pow(2, i) <= number; i++) {
                System.out.println("2^" + i + " = " + Math.pow(2, i));
            }
        }
    }

}