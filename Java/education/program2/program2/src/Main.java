import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        /*
        // Создание объекта класса scanner
        Scanner scan = new Scanner(System.in);
        System.out.print("Введите своё имя: ");
        // Считывание строки
        String user_name = scan.nextLine();
        System.out.println("Привет, " + user_name);
        int num1 = scan.nextInt();
        */

        Scanner scan = new Scanner(System.in);
        System.out.println("Программа посчитает все положительные степени двойки, значение которых не превышает ваше число");
        System.out.print("Введите число >> ");
        int num = scan.nextInt();
        int degree_of_two = 0;
        for (int i=0; degree_of_two <= num; i++) {
            degree_of_two = (int) Math.pow(2,i);
            System.out.println("2^" + i + " = " + degree_of_two);
        }
    }

    /*

    // >, <, ==, >=, <=, !=
    // || - или, && - и
    // str.equals("строка") == true если str == строка

    if (a > b) {
        ...
    } else if {
        ...
    } else {
        ...
    }

     */

}

/*
как программы выполняются и компилируются в Java
байт код > jvm
после компиляции файлы .class
как его выполнить?
с помощью
точка входа java программы

 */