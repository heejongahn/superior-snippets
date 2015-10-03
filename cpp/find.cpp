#include <string>
#include <cstring>
#include <iostream>

using namespace std;

int main() {
    char test1[30] = "and     $11, $11, $0";
    char test2[30] = "lab1:";

    char* token = strtok(test1, (char *) " ,$");
    while (token != NULL)
    {
        cout << token << endl;
        token = strtok(NULL, (char *) " ,$");
    }


    if (((string) test1).find(":") != string::npos)
    {
        cout << test1 << endl;
    }

    if (((string) test2).find(":") != string::npos)
    {
        cout << test2 << endl;
    }

    return 0;
}
