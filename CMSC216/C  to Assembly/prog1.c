#include <stdio.h>

int n;
int ans= -1;

int main(void) {
  scanf("%d", &n);

  if (n >= 0)
    ans= ((n * (n + 1)) / 2) * ((n * (n + 1)) / 2);

  printf("%d\n", ans);

  return 0;
}
