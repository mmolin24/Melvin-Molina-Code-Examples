/*i pledge my honor that I have not given or received any
unauthorized assistance on this assignment
terpconnect: mmolin24
UID: 116944243
section: 0301*/
#include <stdio.h>
#include "list.h"
#include <stdlib.h>

/* this function initializes the list */
void init(List *const list){

  /* sets the initial instance  of list data to 0 */
  list->data = 0;

  /* sets the upcoming list instance to null */
  list->next = NULL;
  
}

/* this function will place a list instance at the end of the
   linked list and rearange the last pointer to point to it. */
int append(List *const list, int new_value){

  List *iter, *new;

  /*checks invalid list case */
  if(list == NULL)
    return 0;

  /*allocates memory for the new node */
  new = malloc(sizeof(List));

  if(new == NULL)
    return 0;

  /* sets up the new list instance */
  new-> next = NULL;
  new-> data = new_value;
  iter = list-> next;

  if(iter == NULL){
    list-> next = new;
    return 0;
  }
  /* gets to the back of the list */
  while(iter->next != NULL){
    iter = iter-> next;
  }
  
  /* sets new node behind the last node */
  iter-> next = new;
  
  return 1;
}

/* this function puts a new instance of list in the front of
   the linked list with the parameter value and will return
   1 if it was possible and 0 if list was NULL */
int prepend(List *const list, int new_value){

  List *iter, *new;

  /*checks invalid list */
  if(list == NULL)
    return 0;

  new = malloc(sizeof(List));
  if(new == NULL)
    return 0;
  /* sets value for the new list node */
  new-> next = NULL;
  new-> data = new_value;

  iter = list-> next;

  /* checks if list is already empty*/
  if(iter == NULL){
    list-> next = new;
    new-> next = NULL;
  }

  new-> next = iter;
  list-> next = new;
  
  return 1;
}

int size(List *const list){
  int size = 0;
  List *iter;

  /*checks if list is null */
  if(list == NULL)
    return 0;
  
  /* place iterator variable */
  iter = list-> next;
  
  if(iter == NULL)
    return 0;
  
  /* valid list so add 1 to size, first has been checked */
  size++;
  
  while(iter-> next != NULL){
    iter = iter-> next;
    size++;
  }
  
  return size;
}

/* this function find the value within the list and
   returns the index value of the list that has the 
   value in the field. */
int find(List *const list, int value){

  List *iter;
  int i = 0;

  /* checks for invalid list parameter */
  if(list == NULL)
    return 0;

  /* sets iterator to the front of the list */
  iter = list-> next;

  if(iter == NULL)
    return 0;

  /*traverses list until finding the proper*/
  while(iter != NULL){
    /* looks if the value has been found yet 
       if value is found then return the index instantly*/
    if(iter-> data == value){
      
      return i;
    }
    iter = iter-> next;
    
    i++;
  }
  /* returns -1 if the proper value was not found */
  return -1;
}

/* this function will find the index value'd parameter in the linked list
   and delete the information within the variable and free the variable */
int delete(List *const list, unsigned int position){

  List *iter;
  List *temp;
  int i = 0;

  /* checks if the position is greater than the size of the list */
  if( position > (size(list)-1))
    return 0;

  /* checks the invalid list parameter */
  if(list == NULL)
    return 0;

  /* sets iter to the first node within the list */
  iter = list-> next;
  if(iter == NULL)
    return 0;\
  /* tests first position */
  if(position == 0){
    list-> next = iter-> next;
    free(iter);
  }
  /* by testing the positon 0 we can add 1 to index i*/
  i++;

  /*loops through the list looking for the position */
  while(iter != NULL && i != position){
    iter = iter-> next;
    i++;
  }
  /* once position is found delete the variable by changing the previous pointer
     and the next pointer and then freeing the pointer itself */
  temp = iter-> next;
  iter->next = iter->next-> next;
  free(temp);
  return 1;
}

/*this function iterates the linked list and prints out values */
void print(List *const list){
  
  List *iter;
  int i = 0;
  iter = list;

  /* checks if list is null */
  if(list == NULL)
    return;
  
  iter = list-> next;
  if(iter == NULL)
    return;

  /* iterates through the list until reaching the end */
  while(iter!= NULL){
    /* prints all the data members*/
    printf("%d", iter-> data);
      
    if(i != (size(list) -1)){
      printf(" ");
    }
    iter = iter -> next;
    i++;
  }

  
  printf("\n");
  
  
}
