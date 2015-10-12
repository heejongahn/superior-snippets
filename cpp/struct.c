#include <stdio.h>

typedef struct bp {
  int ID, lineNum, thenExecuted, elseExecuted;
  char* condition;
} branchPoint;

int main()  {
  branchPoint bp = {0, 13, 0, 3, "abc"};
  printf("%d\n", bp.ID);
  printf("%d\n", bp.lineNum);
  printf("%d\n", bp.thenExecuted);
  printf("%d\n", bp.elseExecuted);
  printf("%s\n", bp.condition);
  return 0;
}
