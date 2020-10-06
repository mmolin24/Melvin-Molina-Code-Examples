/* Implement your shell here */
#include <unistd.h>
#include <sys/types.h>
#include <err.h>
#include<sys/wait.h>
#include<sysexits.h>
#include<stdlib.h>
#include<stdio.h>
#include<string.h>
#define MAX 1024

int main() {
  
  char *total[MAX + 1];
  char arr[MAX + 1];
  char *buff = NULL; 
  
  pid_t pid = 0;
  
  int len = 0;
  int i = 0;
  int e = 0;
  
  /* prefix */
  printf("shell_jr: ");
  fflush(stdout);
    

  /* will go until stdin runs out */
  while(fgets(arr, MAX + 1, stdin) != NULL ) {


      
    /* formats the input */
    len = strlen(arr);
    
    for( ; i < len; i++) {
      
      if(arr[i] == '\n') {
	
	arr[i] = '\0';
	
      }
    }

    
    i=0;

    /* split the input by "" */
    buff = strtok(arr, " ");

    /* wasn't able to use sscanf properly */
    while(buff != NULL) {
      total[i] = buff;
      i++;
      buff = strtok(NULL, " ");
    }

    /* placing null */
    if(total[1] != NULL) {
      total[2] = NULL;
    } else{
      total[1] = NULL;
    }
    
  


   
    if(strcmp(total[0], "exit") == 0 || strcmp(total[0], "hastalavista") == 0) {
      /* exit shell_jr */
      
      printf("See you\n");
      fflush(stdout);
      exit(0);
      
    } else if(strcmp(total[0], "cd") == 0) {
      /* change directory */
      
      e = chdir(total[1]);

      /* checks if chdir was successful */
      if(e == -1) {
	err(EX_OSERR, "Cannot change to directory %s", total[1]);
	fflush(stdout);
      }
      
    } else {

      /* all other commands are forked and execvp */
      
      if((pid = fork()) < 0) {
	err(EX_OSERR, "fork error");
      }
      if(pid) {
	
	/* parent process */
	wait(NULL);
	
      } else {
	
	/* child process */
	execvp(total[0], total);

	/* reaches here if the execvp was unsucessful */
	printf("Failed to execute %s\n", total[0]);
	
	fflush(stdout);
	exit(EX_OSERR);
      }
    }

    /* print out shell prefix */
    
    printf("shell_jr: ");
    fflush(stdout);

  }

  /* reaches end of program */
  
  return 0;
}
