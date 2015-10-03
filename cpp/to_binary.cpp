#include <iostream>
#include <bitset>

int main()
{
        std::string binary = std::bitset<5>(23).to_string(); //to binary
        std::cout<<binary<<"\n";

        unsigned long decimal = std::bitset<5>(binary).to_ulong();
        std::cout<<decimal<<"\n";
        return 0;
}
