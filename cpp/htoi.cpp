#include <iostream>
#include <string>
#include <sstream>
#include <bitset>

using namespace std;

int main () {
    char h1[] = "0xf";
    string h = (string) h1;
    stringstream ss;
    ss << hex << h;

    int n;
    ss >> n;

    cout << n << endl;
    return 0;
}
