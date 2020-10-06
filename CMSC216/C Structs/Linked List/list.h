/* (c) Melvin Molina, 2019. */

 
typedef struct list{
  struct list *next;
  int data;
} List;



  
void init(List *const list);
int append(List *const list, int new_value);
int prepend(List *const list, int new_value);
int size(List *const list);
int find(List *const list, int value);
int delete(List *const list, unsigned int position);
void print(List *const list);
