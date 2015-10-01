#include <iostream>

namespace A {
    char* Add() {
        return "A의 Add() 호출!";
    }
}

namespace B {
    char* Add() {
        return "B의 Add() 호출!";
    }
}

int main()
{
    std::cout<<(A::Add())<<std::endl;
    std::cout<<(B::Add())<<std::endl;
    return 0;
}
