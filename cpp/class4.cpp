#include <bitset>
#include <iostream>
#include <string>

using namespace std;
class branchPoint {
    private:
      int ID, lineNum, thenExecuted, elseExecuted;
      char* condition;
    public:
      branchPoint(int _ID, int _lineNum, char* _cond)
        :ID(_ID), lineNum(_lineNum), thenExecuted(0), elseExecuted(0), condition(_cond)
      {}
      void printCondition() {
        cout << condition << endl;
      }
    };

int main() {
    branchPoint b1(0, 3, "abc");
    b1.printCondition();
    cout << sizeof(b1) << endl;

    branchPoint b2(0, 3, "abcasdfsaf");
    b2.printCondition();
    cout << sizeof(b2) << endl;
}
