#include <stdio.h>

int x, y, result;

int count_digit(int num, int digit) {
  int count= -1, rightmost_digit;

  if (digit >= 0 && digit <= 9) {
    count= 0;

    if (num < 0)
      num= -num;

    if (num == 0 && digit == 0)
      count= 1;
    else
      while (num > 0) {
        rightmost_digit= num % 10;
        if (rightmost_digit == digit)
          count++;
        num /= 10;
      }
  }

  return count;
}
 
int main() {
  scanf("%d %d", &x, &y);

  result= count_digit(x, y);

  printf("%d\n", result);

  return 0;
}
