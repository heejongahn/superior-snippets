#include <string>
#include <cstring>
#include <iostream>
using namespace std;
char delim[] = " .\t";

int main()  {
    char s[] = "data1:  .word\t   100";
    char* token = strtok(s, ":");

    cout << (string)token << endl;

    token = strtok(NULL, delim);
    cout << (string)token << endl;

    cout << s << endl;

    return 0;
}
