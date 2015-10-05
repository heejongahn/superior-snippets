#include <cstring>
#include <iostream>

using namespace std;

int main()  {
    char a[] = "array1:";

    *strstr(a, ":") = '\0';

    cout << a << endl;
    return 0;
}
