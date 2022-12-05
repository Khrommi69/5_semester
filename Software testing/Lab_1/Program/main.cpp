#include <iostream>
#include <iomanip>
#include <algorithm>
#include <fstream>
#include <cstring>

int print_menu() {
    int num;
    system("cls");
    std::cout << "Меню:" << std::endl;
    std::cout << "1 - Работать с матрицей" << std::endl;
    std::cout << "2 - Работать со строкой" << std::endl;
    std::cout << "3 - Работать с текстовым файлом" << std::endl;
    std::cout << "4 - Выход" << std::endl << std::endl;
    std::cout << "Введите номер меню >> ";
    std::cin.clear();   //очистить буфер ввода
    fflush(stdin); //очистить буфер ввода
    std::cin >> num;
    std::cout << std::endl;
    return num;
}

int **entering_a_square_matrix(size_t N) {
    //выделение памяти двумерному массиву
    int **matrix = new int * [N];
    for (size_t i = 0; i < N; i++) {
        matrix[i] = new int [N];
    }

    std::cout << "Ввод матрицы:" << std::endl;
    for (size_t i = 0; i < N; i++) {
        for (size_t j = 0; j < N; j++) {
            std::cout << "matrix[" << i+1 << "][" << j+1 << "] : ";
            std::cin >> matrix[i][j];
        }
    }
    return matrix;
}

void matrix_output(int **matrix, size_t N) {
    for(size_t i = 0; i < N; i++){
        for(size_t j = 0; j < N; j++)
            std::cout << std::setw(3) << matrix[i][j] << ' ';
        std::cout << std::endl;
    }
}

bool is_the_matrix_positive_definite(int **matrix, size_t N) {
    //положительно определённая матрица: невырожденная матрица B, что A = B^T·B
    //невырожденная матрица - матрица определитель которой != 0
    //B^T - транспонирование матрица - строки меняются на столбцы (матрицу как будто перевернули дном вверх)
    //не могу понять как работает положительно определенная матрица поэтому просто узнаю невырожденная она или нет
    bool isMPV = false;
    size_t determinant = matrix[0][0]*matrix[1][1]*matrix[2][2] + matrix[0][1]*matrix[1][2]*matrix[2][0] + matrix[0][2]*matrix[1][0]*matrix[2][1]
                       - matrix[0][2]*matrix[1][1]*matrix[2][0] - matrix[0][1]*matrix[1][0]*matrix[2][2] - matrix[0][0]*matrix[1][2]*matrix[2][1];
    //если матрица вырожденная то (условно) матрица положительно определённая
    if (determinant != 0) isMPV = true;
    (isMPV == true)? std::cout << "Матрица положительно определённая" << std::endl : std::cout << "Матрица положительно не определённая" << std::endl;
    return isMPV;
}


void matrix_destroyer(int **matrix, size_t N) {
    for (size_t i = 0; i < N; i++) {
        delete [] matrix[i];
    }
    delete [] matrix;
}

void work_with_matrix() {
    size_t N = 3;
    int **matrix;
    std::cout << "Матрица размера: " << N << "x" << N << std::endl;
    //Ввод матрицы
    matrix = entering_a_square_matrix(N);
    //Вывод матрицы
    matrix_output(matrix, N);
    //определение того, является ли матрица положительно определённой
    bool isMPV = is_the_matrix_positive_definite(matrix, N);
    //очистка памяти
    matrix_destroyer(matrix, N);
    system("pause");
}

void work_with_string() {
//    getchar();
//    std::string s;
//    std::cout << "Введите строку:" << std::endl;
//    std::cin >> s;
//    //преобразование строки в массив символов для работы с символами
//    char str[s.length() + 1];
//    s.copy(str, s.length() + 1);


    getchar();
    std::string s;
    std::cout << "Введите строку:" << std::endl;
    std::cin >> s;
    //преобразование строки в массив символов для работы с символами
//    char *str = new char [s.length()+1];
//    strcpy (str, s.c_str());
    char *str = const_cast<char*>(s.c_str());

    //обработка строки
    bool flag = false;
    for(int i = 0; i < s.length() + 1; i++) {
        if (!flag) {
            if(str[i] == '#') flag = true;
        }
        else {
            str[i] = '@';
        }
    }
    std::cout << "Обработанная строка:" << std::endl << str << std::endl;
    system("pause");
}

//поиск минимальной длины строки текстового файла и печать этой строки на экран
void work_with_text_file() {
    std::ifstream file("D:\\5_semester\\Software testing\\Lab_1\\Program\\text.txt");
    std::string temp_str;
    std::getline(file, temp_str);
    std::string min_str = temp_str;
    int min_str_length = temp_str.length();
    // пока не достигнут конец файла класть очередную строку в переменную str
    while(getline(file, temp_str)) {
        if (temp_str.length() < min_str_length) {
            min_str = temp_str;
            min_str_length = temp_str.length();
        }
    }
    file.close();
    if (min_str_length == 0) {
        std::cout << "В файле нет строк" << std::endl;
    }
    else {
        std::cout << "Минимальная длина строки в текстовом файле = " << min_str_length
        << std::endl << "Вот эта строка:" << std::endl << min_str << std::endl;
    }
    system("pause");
}

int main() {
    setlocale(LC_ALL, "Rus");
    system("color 70");
    size_t num;
    do {
        switch (num = print_menu()) {
            case 1:
                work_with_matrix();
                break;
            case 2:
                work_with_string();
                break;
            case 3:
                work_with_text_file();
                break;
            case 4:
                system("pause");
                break;
            default:
                std::cout << "Введите корректное значение" << std::endl;
                system("pause");
        }
    } while (num != 4);
    return 0;
}




//приколюхи
//#include <limits>
//std::cout <<  sizeof(size_t) << std::endl;
//std::cout << std::numeric_limits<size_t>::max() << std::endl;
//std::cout << std::numeric_limits<size_t>::min() << std::endl;
//std::cout <<  sizeof(unsigned long) << std::endl;
//std::cout << std::numeric_limits<unsigned long>::max() << std::endl;
//std::cout << std::numeric_limits<unsigned long>::min() << std::endl;
//std::cout <<  sizeof(unsigned long long) << std::endl;
//std::cout << std::numeric_limits<unsigned long long>::max() << std::endl;
//std::cout << std::numeric_limits<unsigned long long>::min() << std::endl;