import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));




        Scanner scan = new Scanner(System.in);

        System.out.println("Программа находит все положительные степени двойки,\nзначение которых не превышает величины введенного числа.");

        System.out.print("Введите число >> ");
        int number = scan.nextInt();
        System.out.println("Введённое число >> " + number);
        for (int i = 0; Math.pow(2, i) <= number; i++) {
            System.out.println("2^" + i + " = " + Math.pow(2, i));
        }
    }

}
