import java.util.Arrays;
import java.util.Scanner; //для чтения всякого
import java.io.*; //для работы с файлами

public class Main {

    public static void main(String[] args) {
        System.out.println("Программа находит все положительные степени двойки,значение которых не превышает величины введенного числа.");
        int number = 0;

        //Если в cmd были переданы аргументы вывести их на экран
        if (args.length > 0) {
            System.out.print("Аргументы коммандной строки на входе:");
            System.out.println(Arrays.toString(args));
        }

        //Проверка на наличие аргументов коммандной строки
        String inputFile = "";
        String outputFile = "";
        try {
            for (int i = 0; i < args.length; i++) {
                //если есть флаг -i то следующий аргумент коммандной строки это название input файла
                if (args[i].equals("-i"))
                    inputFile = args[++i];
                    //если есть флаг -o то следующий аргумент коммандной строки это название output файла
                else if (args[i].equals("-o"))
                    outputFile = args[++i];
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Ошибка при работе с аргументами командной строки");
        }
        //если есть флаг -i попытка открыть файл с входным значением
        if (!inputFile.isEmpty()) {
            try {
                //для обращения к input файлу
                BufferedReader fileIn = new BufferedReader(new FileReader(inputFile));
                //превращение строки из файла в число
                number = Integer.parseInt(fileIn.readLine().trim());
                fileIn.close();
            }
            catch (IOException e) {
                System.err.println("Ошибка при работе с input файлом " + e);
            }
            catch (NumberFormatException e) {
                System.err.println("Ошибка преобразования входных данных " + e);
                Scanner scan = new Scanner(System.in);
                System.out.print("Введите число вручную >> ");
                number = scan.nextInt();
            }
        }
        else {
            Scanner scan = new Scanner(System.in);
            System.out.print("Введите число >> ");
            number = scan.nextInt();
        }

        //если есть флаг -o попытка открыть файл для вывода результатов
        if (!outputFile.isEmpty()) {
            try {
                //вывод в файл результатов работы программы
                PrintWriter pw = new PrintWriter(outputFile);
                pw.println("Программа находит все положительные степени двойки,значение которых не превышает величины введенного числа.");
                pw.println("Введённое число >> " + number);
                for (int i = 0; Math.pow(2, i) <= number; i++) {
                    pw.println("2^" + i + " = " + Math.pow(2, i));
                }
                pw.close();
                System.out.println("Результаты работы программы выведены в файл");
            } catch (IOException e) {
                System.err.println("Ошибка при работе с output файлом " + e);
            }
        }
        else {
            System.out.println("Введённое число >> " + number);
            for (int i = 0; Math.pow(2, i) <= number; i++) {
                System.out.println("2^" + i + " = " + Math.pow(2, i));
            }
        }
    }

}