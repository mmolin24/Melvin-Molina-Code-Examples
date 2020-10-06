#include <stdio.h>
#include <string.h>
#include "document.h"

int init_document(Document *doc, const char *name) {

  /* makes a doc with the param name */
   if (doc != NULL && name != NULL && strlen(name) <= MAX_STR_SIZE) {
      strcpy(doc->name, name);
      return SUCCESS;
   }
   return FAILURE;
}

int reset_document(Document *doc) {

  /* checks if doc is empty*/
   if (doc != NULL) {
     
     /* create doc*/
     
      doc->number_of_paragraphs = 0;
      
      return SUCCESS;
      
   }
   
   return FAILURE;
}

int print_document(Document *doc) {
   int i, j;
   
   if (doc != NULL) {
     
      printf("Document name: \"%s\"\n", doc->name);
      
      printf("Number of Paragraphs: %d\n", doc->number_of_paragraphs);

      
      for (i = 0; i < doc->number_of_paragraphs; i++) {

	/* checks for lines */
	
	 if (doc->paragraphs[i].number_of_lines > 0) {

	   /* prints lines*/
	    for (j = 0; j < doc->paragraphs[i].number_of_lines; j++) {
	      
	       printf("%s\n", doc->paragraphs[i].lines[j]);
	       
	    }
	    
	    /* if doc has no lines*/
	    if (i < (doc->number_of_paragraphs - 1))
	       printf("\n");
	 }
      }
      return SUCCESS;
   }
   return FAILURE;
}

int add_paragraph_after(Document *doc, int paragraph_number) {
   int i, start = 0, end = 0, num = 0;

   /* validates doc*/
   if (doc != NULL) {

      num = doc->number_of_paragraphs;
      
      if (num < MAX_PARAGRAPHS && paragraph_number <= num) {
        
	 if (paragraph_number == 0 && !num) {
	   
	    doc->paragraphs[0].number_of_lines = 0;
	    
	 }
	 
	 if (paragraph_number == doc->number_of_paragraphs) {
	   
	    end = doc->number_of_paragraphs;
	    doc->paragraphs[end].number_of_lines = 0;
	    
	 }
	 else {
	    start = doc->number_of_paragraphs;
	    for (i = start; i >= paragraph_number; i--) {
	      
	       doc->paragraphs[i + 1] = doc->paragraphs[i];
	       
	    }

	    
	    doc->paragraphs[paragraph_number].number_of_lines = 0;
	 }
	 
	 (doc->number_of_paragraphs)++;
	 return SUCCESS;
      }
   }
   return FAILURE;
}

int add_line_after(Document *doc, int paragraph_number, int line_number, const char *new_line) {
   int i, num_lines = 0;
   char *pos, *line1, *line2;

   /* validates params */
   if (doc != NULL && new_line != NULL) {

      if (paragraph_number <= doc->number_of_paragraphs) {
	 
	 num_lines = doc->paragraphs[paragraph_number - 1].number_of_lines;
	 
	 if (num_lines < MAX_PARAGRAPH_LINES && line_number <= num_lines) {
     
	    /* places in the beginning */
	    if (line_number == 0 && !num_lines) {
	      
	       pos = doc->paragraphs[paragraph_number - 1].lines[0];
	       strcpy(pos, new_line);
	       
	    }
	    /* places in end */
	    else if (line_number == num_lines) {
	      
	       pos = doc->paragraphs[paragraph_number - 1].lines[line_number];
	       strcpy(pos, new_line);
	       
	    }
	    /* places in middle */
	    else {
	      
	       for (i = num_lines; i >= line_number; i--) {
	      
		  line1 = doc->paragraphs[paragraph_number - 1].lines[i];
		  
		  line2 = doc->paragraphs[paragraph_number - 1].lines[i+1];
		  
		  strcpy(line2, line1);
	       }
	       
	       pos = doc->paragraphs[paragraph_number - 1].lines[line_number];
	       
	       strcpy(pos, new_line);
	    }
	    
	    (doc->paragraphs[paragraph_number - 1].number_of_lines)++;
	    return SUCCESS;
	 }
      }
   }
   return FAILURE;
}

int get_number_lines_paragraph(Document *doc, int paragraph_number, int *number_of_lines) {
   int count = 0;

   
   /* validates param*/
   if (doc != NULL) {
      if (paragraph_number <= doc->number_of_paragraphs) {
	
	 count = doc->paragraphs[paragraph_number].number_of_lines;
	 
	 *number_of_lines = count;
	 
	 return SUCCESS;
	 
      }
   }
   return FAILURE;
}


int append_line(Document *doc, int paragraph_number, const char *new_line) {
   int num = 0;
   
   if (doc != NULL && new_line != NULL) {

      num = doc->paragraphs[paragraph_number - 1].number_of_lines;
      if (paragraph_number <= MAX_PARAGRAPHS && num < MAX_PARAGRAPH_LINES) {
	
	/* use addline function to add the line to a specified position */
	
	 add_line_after(doc, paragraph_number, num, new_line);
	 
	 return SUCCESS;
      }
   }
   return FAILURE;
}

int remove_line(Document *doc, int paragraph_number, int line_number) {
   int i, j, num = 0;
   char *line, *line2;
   
   /* valdates doc */
   if (doc != NULL) {
     
      num = doc->paragraphs[paragraph_number - 1].number_of_lines;
      
      if (paragraph_number <= doc->number_of_paragraphs && line_number <= num) {
	
	 if (line_number < num) {
	   
	    for (i = line_number - 1; i < num - 1; i++) {

	       line = doc->paragraphs[paragraph_number - 1].lines[i];
	       
	       line2 = doc->paragraphs[paragraph_number - 1].lines[i + 1];
	       
	       for (j = 0; j < MAX_STR_SIZE; j++) {
		 
		  line[j] = line2[j];
	       }
	    }
	 }
	 
	 (doc->paragraphs[paragraph_number - 1].number_of_lines)--;
	 
	 return SUCCESS;
      }
   }
   return FAILURE;
}

int load_document(Document *doc, char data[][MAX_STR_SIZE + 1], int data_lines) {
   int i = 0;
   if (doc != NULL && data != NULL && data_lines) {
     
	 /* add paragraph*/
	 add_paragraph_after(doc, doc->number_of_paragraphs);

	 /*  load the data into the paragraphs  */
	 for (i = 0; i < data_lines; i++) {
	   
	    if (strcmp(data[i], "") == 0) {
	      
	       add_paragraph_after(doc, doc->number_of_paragraphs);
	       
	    }
	    else append_line(doc, doc->number_of_paragraphs, data[i]);
	 }
	 
	 return SUCCESS;
   }
   return FAILURE;
}


int replace_text(Document *doc, const char *target, const char *replacement) {
   int i, j, num = 0;
   char *curr, temp[MAX_STR_SIZE + 1] = {0};

   if (doc != NULL && target != NULL && replacement != NULL) {

     
      for (i = 0; i < doc->number_of_paragraphs; i++) {
	
	 num = doc->paragraphs[i].number_of_lines;
	 
	 for (j = 0; j < num; j++) {

	    curr = strstr(doc->paragraphs[i].lines[j], target);
	    
	    while (curr != '\0') {
	       
	       strcpy(temp, curr);
	       
	       strcpy(curr, "");
	       
	       strcat(curr, replacement);
	       
	       strcat(curr + strlen(replacement), temp + strlen(target));
	       
	       curr = strstr(curr + strlen(target), target);	       
	    }
	 }
      } 
      return SUCCESS;
   }
   return FAILURE;
}


int highlight_text(Document *doc, const char *target) {
   char res[MAX_STR_SIZE + 1] = "";

   /*validate params */
   if (doc != NULL && target != NULL) {

     
      strcat(res, HIGHLIGHT_START_STR);
      strcat(res, target);
      strcat(res, HIGHLIGHT_END_STR);

      
      replace_text(doc, target, res);
      return SUCCESS;
   }
   
   return FAILURE;
}


int remove_text(Document *doc, const char *target) {
  
  /* validate parameters */
   if (doc != NULL && target != NULL) {
     
      /* replaces target with empty string*/
     
      replace_text(doc, target, "");
      
      return SUCCESS;
   }
   return FAILURE;
}



int save_document(Document *document, const char *filename) {
  
   FILE *out;
   
   int i,j, lines;   
   
   if (document != NULL && filename != NULL) {
     
      if ((out = fopen(filename, "w")) != NULL) {

	/* writes to file */
	 for (i = 0; i <= document->number_of_paragraphs; i++) {

	    lines = document->paragraphs[i].number_of_lines;
	    
	    for (j = 0; j <= lines; j++) {
	       
	       fputs(document->paragraphs[i].lines[j], out);
	       
	       if (j < lines) {
		  fputs("\n", out);
		  
	       }
	    }
	    
	    if ((i+1) < document->number_of_paragraphs) {
	      
	       fputs("\n", out);
	       
	    }
	 }
	 /* end of for loop */
	 fclose(out);
	 return SUCCESS;
      }
   }
   return FAILURE;
}

int load_file(Document *doc, const char *filename) {
  
   FILE *in;
   char line[MAX_STR_SIZE + 1], line1, line2[MAX_STR_SIZE + 1];
   int value, num = 1;
   
   if (doc != NULL && filename != NULL) {
     
      if ((in = fopen(filename, "r")) != NULL) {

	/* adds in first line */
	 add_paragraph_after(doc, 0);	    
	
	 /* reads */
	 while (fgets(line, MAX_STR_SIZE + 1, in)) { 
	    
	    value = sscanf(line, "%c%s", &line1, line2);
	    
	    strtok(line, "\n");	    

	    if (line1 != '#') {
	      
	       if (value < 2) {
		 
		  add_paragraph_after(doc, num);
		  
		  num++;
		  
	       }
	       else append_line(doc, num, line);
	       
	    }
	    
	 }
	 
	 return SUCCESS;
      }
   }
   return FAILURE;
}
