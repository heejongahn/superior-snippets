#include <iostream>
#include <string>

using namespace std;
int main () {
    char input_string[] = "4($3)";
    char* token;
    int rt, offset;

    token = strtok(input_string, "(");
    offset = atoi(token);

    token = strtok(NULL, "$) ");
    rt = atoi(token);

    cout << offset << endl;
    cout << rt << endl;

    return 0;
}
