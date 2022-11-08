#include <iostream>

int main() {
    int num;
    do {
//        system("cls");
        std::cout << "Menu:" << std::endl;
        std::cout << "1 - work with matrix" << std::endl;
        std::cout << "2 - work with string" << std::endl;
        std::cout << "3 - work with text file" << std::endl;
        std::cout << "4 - exit" << std::endl << std::endl;
        std::cout << "Enter the menu number >> ";
        std::cin >> num;

    } while (num != 4);
    return 0;
}
