package com.company; //пакет файлов с которым можно работать

import java.util.Scanner;

//основная программа
public class Main {
    //основная функция (начало программы)
    public static void main(String[] args) {
        //Это комментарий - однострочный
        //Вывод строк и использование спец символов
        System.out.println("Hello world!\nЯ начал изучать Java");
        System.out.println("\\\\новая строка \t\t -_- \n");

        //ПЕРЕМЕННЫЕ И ТИПЫ ДАННЫХ
        int age;  //integer - целочисленная переменная
        age = 54; //присвоение значения переменной
        //sout + Tab - быстрый вывод в Clion
        System.out.println("Пожилой возраст это " + age + " года");

                  // целочисленные типы данных
        byte a1;  // [-128; 127] - занимает 1 байт
        short a2; // [-32768; 32767] - 2 байта
        int a3;   // примерно [-2млрд.; 2млрд.] - 4 байта (-2 147 483 648; 2 147 483 647)
        long a4;  // [-9 223 372 036 854 775 888; 9млрд*млрд] - 8 байт

        // не целочисленные - вещественные числа (без добавления буквы в конце ошибка)
        float a5 = 5.54695469f;
        // double в 2 раза больше помещается чисел после точки
        double a6 = 1.1231d;

        // символьный тип данных
        char ch = 'S';
        // строковый тип данных
        String some_str = "Это строка";

        // булевый тип данных [true, false]
        boolean isIt = true;
    }

}