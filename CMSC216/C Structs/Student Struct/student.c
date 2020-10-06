#include "student.h"
#include <string.h>
#include <stdlib.h>
/* i pledge on my honor that i have not given or received any
   unauthorized assistance on this assignment
   terpconnect: mmolin24
   UID 116944243
   section: 0301

 */

Student *new_student(const char name[], unsigned int id, float shoe_size){
  
  Student *newstu = malloc(sizeof(Student));
  newstu->id = id;
  newstu->shoe_size = shoe_size;
  
  
  if(name == NULL){
    newstu->name = malloc(4);
    newstu->name = '\0';
    return newstu;
  }

  newstu->name = malloc(strlen(name));
  strcpy(newstu->name, name);
    
  return newstu;
}


/* function received a student and ID parameter
   returns 0 if the ID do not equal and returns 
   any number back if they are equal */
unsigned int has_id(Student *const student, unsigned int id){

  if(student == NULL)
    return 0;
  if(get_id(student) == id)
    return id;
  else
    return 0;
}

unsigned int has_name(Student *const student, const char name[]){

  if(name == NULL || student == NULL)
    return 0;
  if(strcmp(name, "") == 0)
    return 1;
  else if(strcmp(student->name, name) == 0)
    return 1;
  
  return 0;
}

/* returns the ID of the parameter student */
unsigned int get_id(Student *const student){
  
  return student->id;
}

/* returns the shoe size of the parameter student */
float get_shoe_size(Student *const student){

  if(student == NULL)
    return 0.0;

  
  return student->shoe_size;
}


/* receives parameter student and a new shoe_size to replace
   the current shoe size of the student */
void change_shoe_size(Student *const student, float new_shoe_size){

  student->shoe_size = new_shoe_size;

}

/* changes the name of the student parameter with the
   string parameter */
void change_name(Student *const student, const char new_name[]){

  if(student == NULL)
    return;
  
  if(new_name == NULL){
    free(student->name);
    student->name = malloc(4);
    strcpy(student->name, "\0");
    return;
  }else{
    free(student->name);
    student->name = malloc(sizeof(new_name));
    strcpy(student->name, new_name);
    return;
  }
}

void copy_student(Student *student1, Student *const student2){

  if(student1 == NULL || student2 == NULL)
    return;
  
  change_name(student1, student2->name);
  student1->id = student2->id;
  student1->shoe_size = student2->shoe_size;
  return;
  
}
