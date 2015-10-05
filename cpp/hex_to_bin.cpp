#include <iostream>
#include <string>
#include <sstream>
#include <bitset>

using namespace std;

int main () {
    string h = "0xf0f0";
    stringstream ss;
    ss << hex << h;

    unsigned n;
    ss >> n;

    bitset<32> b(n);
    cout << b.to_string() << endl;

    return 0;
}
