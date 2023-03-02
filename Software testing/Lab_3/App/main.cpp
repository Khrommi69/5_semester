#include <iostream>
#include <fstream>
#include <cassert>
#include <string>

/*
    Этот класс имеет статический метод transform, который принимает строку в качестве параметра и
    возвращает трансформированную строку. В методе transform создается новая строка transformed_str,
    которая изначально равна исходной строке. Также создается флаг found_first_hash, который указывает
    на то, был ли найден первый символ #. Затем мы проходимся по всем символам в строке transformed_str.
    Если мы встречаем символ #, то мы устанавливаем флаг found_first_hash в true. Если мы встречаем
    любой другой символ после того, как found_first_hash становится true, то мы заменяем этот символ на @.
    В конце метод transform возвращает трансформированную строку transformed_str.
 */

class StringTransformer {
public:
    static std::string transform(const std::string& str) {
        std::string transformed_str = str;
        bool found_first_hash = false;

        for (char& c : transformed_str) {
            if (c == '#') {
                found_first_hash = true;
            }
            else if (found_first_hash) {
                c = '@';
            }
        }

        return transformed_str;
    }
};

// Функция для записи результатов тестирования в log-файл
void writeLog(const std::string& message, const std::string& fileName) {
    std::ofstream file(fileName, std::ios_base::app);
    if (!file.is_open()) {
        std::cerr << "Ошибка при открытии файла " << fileName << std::endl;
        return;
    }
    file << message << std::endl;
    file.close();
}

int main() {
    // Тестовый сценарий 1: проверка преобразования строки "Hello, #world!" в "Hello, @@@@@@"
    std::string str1 = "Hello, #world!";
    std::string expected1 = "Hello, #@@@@@@";
    std::string result1 = StringTransformer::transform(str1);
    assert(result1 == expected1);
    std::string message1 = "Тест 1 пройден успешно";
    std::cout << message1 << std::endl;
    writeLog(message1, "test.log");

    // Тестовый сценарий 2: проверка преобразования строки "#dvjnsialw" в "#@@@@@@@@@"
    std::string str2 = "#dvjnsialw";
    std::string expected2 = "#@@@@@@@@@";
    std::string result2 = StringTransformer::transform(str2);
    assert(result2 == expected2);
    std::string message2 = "Тест 2 пройден успешно";
    std::cout << message2 << std::endl;
    writeLog(message2, "test.log");

    // Тестовый сценарий 3: проверка преобразования строки "no hashes" в "no hashes"
    std::string str3 = "no hashes";
    std::string expected3 = "no hashes";
    std::string result3 = StringTransformer::transform(str3);
    assert(result3 == expected3);
    std::string message3 = "Тест 3 пройден успешно";
    std::cout << message3 << std::endl;
    writeLog(message3, "test.log");

    return 0;
}