#include"safe-fork.h"
#include<stdio.h>
#include<unistd.h>
#include<sys/types.h>
#include<stdlib.h>
/*
  Melvin Molina
  CMSC216
  UID:116944243
*/

int main(){

  int main_pipe[2];
  pid_t pid;
  const char *pgm = "/usr/bin/wc";
  char *args[] = {"-wc", "-w", NULL};
  int words;
   
  pipe(main_pipe);
  pid = safe_fork();
  
  if(pid> 0 ){
    /* PARENT process */
    
    dup2(main_pipe[0], STDIN_FILENO);
    
 
    scanf("%d", &words);
    
    if(words < 200){

      printf("Too short!\n");
      return 1;

    }else {

      printf("Long enough!\n");
      return 0;
      
    }

    
  }else{

    if(pid == 0){
      /* CHILD process */  

      dup2(main_pipe[1], STDOUT_FILENO);    
      execv(pgm, args);    
      
   
    }
    
  }
  return 0;
  
}
