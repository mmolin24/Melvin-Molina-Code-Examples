#include<stdio.h>


/*program prints out line size, and appropriate messages if
  the line's size is above 80 characters*/
int main(){
  /*declare/initialize variables used throughout program*/
  int line = 1;
  char arr[999];
  int index = 0;
  int counter= 0;

  /* continue reading until file is done*/
  while ( !feof(stdin) ) {

    /*continues reading until line is done*/
    while( line != 0){

      /* exit program if file ended*/
     if (feof(stdin)) 
       return 0;
   
      /* received input and places into array*/
      scanf("%c",&arr[index]);

      if(feof(stdin))
	return 0;
      /*check if char is newline to then exit while loop*/
      if (arr[index] == '\n')
	line = 0;
      else
	index++;
      
    }
    
    /* checks whether or not to print out initial X or Space*/
    if ( index <= 79 )
      printf(" ");
    else
      printf("X");

    /* prints out the amount of characters*/
    printf(" %3d: ", index);
    
    /* loops through array if finished*/
    if (arr[0] != '\0') {
       for(counter = 0; counter < index; counter++) {
	 printf("%c", arr[counter]);
       }
       /*send to next line after printing array*/
       printf("\n");
    }

    /* checks if not at end of file yet*/
    if (!feof(stdin)) {
      line = 1;
      index = 0;
    }
    else {
      printf("\n");
      return 0;
    }
    
  }
  return 0;
}
