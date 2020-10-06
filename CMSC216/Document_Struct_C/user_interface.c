#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sysexits.h>
#include <ctype.h>
#include "document.h"

char *rem(char *command, char *result);
int valid(Document *doc, char *command); /* check these */

int run_add_paragraph_after(Document *doc, char *command) {

  char name[MAX_STR_SIZE + 1], arr[MAX_STR_SIZE + 1];
   
  int val, num;

  val = sscanf(command, "%s%d%s", name, &num, arr);

   
  if (val == 2 && num >= 0) {
    
    
    if (add_paragraph_after(doc, num) == FAILURE) {
      /* run command and if failed then print failure message */
      
      printf("add_paragraph_after failed\n");
      
    }
    
    return SUCCESS;
    
   }
  
  return FAILURE;
}

int run_add_line_after(Document *doc, char *command) {
  char name[MAX_STR_SIZE + 1], line[MAX_STR_SIZE + 1];
  char buff[MAX_STR_SIZE + 1];
  int val, num1, num2;
  
  val = sscanf(command, "%s%d%d%s", name, &num1, &num2, buff);
  
  if (val == 4 && num1 > 0 && num2 >= 0) {
    
    if (strchr(command, '*') != NULL) {
      
      strcpy(line, strchr(command, '*') + 1);
      
      /* use local function to add the function */
      if (add_line_after(doc, num1, num2, line) == FAILURE) {
	   
	printf("add_line_after failed\n");
	
      }
      
      return SUCCESS;
    }
  }
  return FAILURE;
}


int run_print_document(Document *doc, char *command) {
  
  char name[MAX_STR_SIZE + 1], buff[MAX_STR_SIZE + 1];
  
  int val;

  
  val = sscanf(command, "%s%s", name, buff);

  /* validates param */
  if (val == 1) {

    /* use local function to print out doc */
    if (print_document(doc) == FAILURE) {
      
      printf("print_document failed\n");
      
    }
    return SUCCESS;
  }
  return FAILURE;
}

int run_append_line (Document *doc, char *command) {
  
  char name[MAX_STR_SIZE + 1], line[MAX_STR_SIZE + 1];
  
  char buff[MAX_STR_SIZE + 1];
  
  int val, numb;
  
  val = sscanf(command, "%s%d%s", name, &numb, buff);

  /* validates params*/
  if (val == 3 && numb > 0) {
    
    if (strchr(command, '*') != NULL) {
      strcpy(line, strchr(command, '*') + 1);

      /* use local funcction to append */
      if (append_line(doc, numb, line) == FAILURE) {

	/* only printed if failed */
	printf("append_line failed\n");
	
      }
      return SUCCESS;
    }
  }
  return FAILURE;
}


int run_remove_line(Document *doc, char *command) {
  
  char name[MAX_STR_SIZE + 1], buf[MAX_STR_SIZE + 1];
  
  int values, num, num1;

  
  values = sscanf(command, "%s%d%d%s", name, &num, &num1, buf);
  
  if (values == 3 && num > 0 && num1 > 0) {
    

    /* use local function to remove the specified line */
    if (remove_line(doc, num, num1) == FAILURE) {
      
      printf("remove_line failed\n");
      
    }
    return SUCCESS;
  }
  return FAILURE;
}


int run_load_file(Document *doc, char *command) {
  
  char name[MAX_STR_SIZE + 1], file[MAX_STR_SIZE + 1];
  
  char buffer[MAX_STR_SIZE + 1];
  
  int num;
  

  
  num = sscanf(command, "%s%s%s", name, file, buffer);

  
  if (num == 2) {
    
    /* load file with function */
    
    if (load_file(doc, file) == FAILURE) {
      
      printf("load_file failed\n");
      
    }
    
    return SUCCESS;
    
  }
  
  return FAILURE;
}


int run_replace_text(Document *doc, char *command) {
  
  char name[MAX_STR_SIZE + 1], *one, *two, *three, *four;
  
  char search[MAX_STR_SIZE + 1], new[MAX_STR_SIZE + 1];
  
  char temp1[MAX_STR_SIZE + 1], temp2[MAX_STR_SIZE + 1];
  
  int num = 0;
  
  num = sscanf(command, "%s%s%s", name, temp1, temp2);
  one = strstr(command, "\"");
  
  /* find quote */
  if (num == 3 && one != NULL) {
    two = strstr(one + 1, "\"");

    /* placing pointers */
    if (two != NULL) {
      three = strstr(two + 1, "\"");
      
      if (three != NULL) {
	four = strstr(three + 1, "\"");
	
	if (four != NULL) {
	 
	  strncpy(search, one + 1, two - one);
	  
	  search[two - (one + 1)] = '\0';
	  
	  strncpy(new, three + 1, four - three);
	  
	  new[four - (three + 1)] = '\0';

	  /* use local function to place text */
	  if (replace_text(doc, search, new) == FAILURE) {

	    printf("remove_line failed\n");	 

	  }
	  
	  return SUCCESS;
	  
	}
	
      }
      
    }
    
  }
  return FAILURE;
}


int run_highlight_text(Document *doc, char *command) {
  
  char name[MAX_STR_SIZE + 1], target[MAX_STR_SIZE + 1];
  
  char temp[MAX_STR_SIZE + 1], *str, *result;
  
  int num_values;
  
  num_values = sscanf(command, "%s%s", name, temp);
  str = strstr(command, "\"");
  
  if (num_values == 2 && str != NULL) {
    
    if ((result = rem(str, target)) != NULL) {
      highlight_text(doc, result);      
      return SUCCESS;
    }
  }   
  return FAILURE;
}

int run_remove_text(Document *doc, char *command) {
  char name[MAX_STR_SIZE + 1], target[MAX_STR_SIZE + 1];
  
  char temp[MAX_STR_SIZE + 1], *str, *res;
  
  int num = 0;

  
  num = sscanf(command, "%s%s", name, temp);
  
  str = strstr(command, "\"");
  
  if (num == 2 && str != NULL) {
    
    if ((res = rem(str, target)) != NULL) {
      
      remove_text(doc, res);
      
      return SUCCESS;
      
    }
    
  }
  
  return FAILURE;
}

int run_save_document(Document *doc, char *command) {
  
  char doc_n[MAX_STR_SIZE + 1], fil[MAX_STR_SIZE + 1];
  
  char extra[MAX_STR_SIZE + 1];
  
  int num;
  
  num = sscanf(command, "%s%s%s", doc_n, fil, extra);

  /* validates num */
  if (num == 2) {

    /* uses local function to save the document */
    if (save_document(doc, fil) == FAILURE) {
      
      printf("save_document failed\n");
      
    }
    
    return SUCCESS;
  }
  
  return FAILURE;
}


int run_reset_document(Document *doc, char *command) {
  
  char name[MAX_STR_SIZE + 1], spam[MAX_STR_SIZE + 1];
  
  int value;

  
  value = sscanf(command, "%s%s", name, spam);

  /* validates value */
  if (value == 1) {

    /* resets the doc from the parameter doc */
    reset_document(doc);
    
    return SUCCESS;
    
  }
  return FAILURE;
}

char *rem(char *c, char *result) {
  
  char *one, *two;
  
  one = strstr(c, "\"");   
  if (one != NULL) {
    two = strstr(one + 1, "\"");
    
    if (two != NULL) {
      strncpy(result, one + 1, two - one);
      result[two - (one + 1)] = '\0';
      
      return result;
    }
  }
  return NULL;
}


int valid(Document *doc, char *command) {
  
  int status = FAILURE;
  

  /* locate the command */
  if (strstr(command, "add_paragraph_after") != NULL) {
    
    if (!run_add_paragraph_after(doc, command)) {
      
      status = SUCCESS;
      
    }
  }
  else if (strstr(command, "add_line_after") != NULL) {
    
    if (!run_add_line_after(doc, command)) {
      
      status = SUCCESS;
    }
  }
  else if (strstr(command, "print_document") != NULL) {
    
    if (!run_print_document(doc, command)) {
      
      status = SUCCESS;
    }
  }
  else if (strstr(command, "append_line") != NULL) {
    
    if (!run_append_line(doc, command)) {
      
      status = SUCCESS;
    }
  }   
  else if (strstr(command, "remove_line") != NULL) {
    
    if (!run_remove_line(doc, command)) {
      
      status = SUCCESS;
    }
  }
  else if (strstr(command, "load_file") != NULL) {
    
    if (!run_load_file(doc, command)) {
      
      status = SUCCESS;
    }
  }
  else if (strstr(command, "replace_text") != NULL) {
    
    if (!run_replace_text(doc, command)) {
      
      status = SUCCESS;
    }
  }
  else if (strstr(command, "highlight_text") != NULL) {
    
    if (!run_highlight_text(doc, command)) {
      
      status = SUCCESS;
    }
  }
  else if (strstr(command, "remove_text") != NULL) {
    
    if (!run_remove_text(doc, command)) {
      
      status = SUCCESS;
    }
  }
  else if (strstr(command, "save_document") != NULL) {
    
    if (!run_save_document(doc, command)) {
      
      status = SUCCESS;
    }
  }
  else if (strstr(command, "reset_document") != NULL) {
    
    if (!run_reset_document(doc, command)) {
      
      status = SUCCESS;
    }
  }
  
  /* if reached then invalid command detected */
  if (status == FAILURE) {
    
    printf("Invalid Command\n");
    
  }
  
  return status;
}

int main(int argc, char *argv[]) {
  Document doc;
  const char *doc_name = "main_document";
  FILE *input_file;
  
  char line[MAX_STR_SIZE + 1], line1, line2[MAX_STR_SIZE + 1];
  int num_values = 0;

  
  if (init_document(&doc, doc_name) == FAILURE) {
    
    printf("Initialization failed\n");
    
  }
  else {
    
    if (argc == 1) {
      input_file = stdin;
      
      
	 printf("> ");
	 fgets(line, MAX_STR_SIZE + 1, stdin);
	 
	 /* scan until quit or exit */
	 while (strcmp(line, "quit\n") && strcmp(line, "exit\n")) {
	   
	   sscanf(line, " %c%s", &line1, line2); 
	   strtok(line, "\n"); 
	   
	    if (strcmp(line, "\n") != 0 && line1 != '#') {
	      valid(&doc, line);
	    }
	    printf("> ");
	    fgets(line, MAX_STR_SIZE + 1, stdin);
	 }
    }
    /* reads from file that user put from command line */      
    else if (argc == 2) {
      
      
      if ((input_file = fopen(argv[1], "r")) == NULL) {
	
	fprintf(stderr, "%s cannot be opened.\n", argv[1]);
	
	exit(EX_OSERR);
	
	 }
      else {
	/* reads file */
	while (fgets(line, MAX_STR_SIZE + 1, input_file)) {
	  
	  if (strcmp(line, "quit\n") && strcmp(line, "exit\n")) {
	    
	    num_values = sscanf(line, " %c%s", &line1, line2);
	    
	    strtok(line, "\n"); 
	    
	    if (num_values > 0 && line1 != '#') {
	      
	      valid(&doc, line);
	      
	    }
	    
	  }
	  
	}

	
      }
      
    }
    /* invalid */
    else {
      
      fprintf(stderr, "Usage: user_interface\n");
      
      fprintf(stderr, "Usage: user_interface <filename>\n");
      
      exit(EX_USAGE);
    }
  }
  fclose(input_file);
  exit(EXIT_SUCCESS);
}
