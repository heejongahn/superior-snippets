#include <fstream>
#include <iostream>

using namespace std;
char inputString[1000];

int main(int argc, char* argv[]){
    char* filename = argv[1];

    ofstream outFile("copy.txt");
    ifstream inFile((string)filename);

    while(!inFile.eof())    {
        inFile.getline(inputString, 100);
        outFile << inputString << endl;
    }

    inFile.close();
    outFile.close();
    return 0;
}
