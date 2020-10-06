#include <stdio.h>

int x, y, answer;

int count_digit(int num, int digit) {
  int result= -1, rightmost_digit;

  if (digit >= 0 && digit <= 9) {
    result= 0;

    if (num < 0)
      num= -num;

    if (num >= 0 && num <= 9)
      if (num == digit)
        result= 1;
      else result= 0;
    else {
      rightmost_digit= num % 10;
      result= count_digit(num / 10, digit);
      if (rightmost_digit == digit)
        result++;
    }
  }

  return result;
}
 
int main() {
  scanf("%d %d", &x, &y);

  answer= count_digit(x, y);

  printf("%d\n", answer);

  return 0;
}

