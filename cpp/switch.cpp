#include <iostream>

int main() {
    int i;
    std::cin>>i;

    switch (i) {
        case 1:
            std::cout<<"Hello, heejong!\n";
        case 2:
            std::cout<<"I love you, hajin!\n";
            break;
        default:
            std::cout<<"Oh yeah!\n";
        }
    return 0;
}
