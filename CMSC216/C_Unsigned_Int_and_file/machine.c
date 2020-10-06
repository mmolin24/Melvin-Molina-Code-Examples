#include "machine.h"
#include <stdio.h>

/* I pledge on my honor that I have not given or received
   any unauthorized assistance on this assignment 
   Melvin Molina, ID: 166944243, Section num: 0301
   Terpconnect: mmolin24 section num: 0301 */



/* function receives a word and prints out appropriate information */
/* after checking if it's valid then prints registers/memory used  */
int print_instruction(Word instruction){
  
  unsigned int mem = instruction, opcode = instruction;
  unsigned int r1 = instruction, r2 = instruction, r3 = instruction;
  
  
  /*mem receives value for the memory location*/
  mem = mem >> 9;
  mem = mem & 0x7ffff;
  
  /* places values into registers*/
  r1 = r1 >> 6;
  
  r2 = r2 >> 3;
  
  r3 = r3 & 0x7;
  r2 = r2 & 0x7;
  r1 = r1 & 0x7;
  
  /* temp 2 has the opcode*/
  
  opcode = opcode >> 28;
  opcode = opcode & 0xf;
  
  /* sends code through to the validation function */
  if(is_valid_instruction(instruction) == 0){
    return 0;
  }
  
  /* appropriate print statements */
  if(opcode == PLUS)
    printf("%-7sR%d  R%d  R%d","plus" , r1, r2, r3);
  else if(opcode == DIFF)
    printf("%-7sR%d  R%d  R%d", "diff",r1,r2, r3);
  else if(opcode == MULT)
    printf("%-7sR%d  R%d  R%d", "mult",r1, r2, r3);
  else if(opcode == QUOT)
    printf("%-7sR%d  R%d  R%d","quot", r1, r2, r3);
  else if(opcode == MOD)
    printf("%-7sR%d  R%d  R%d", "mod",r1, r2, r3);
  else if(opcode == AND)
    printf("%-7sR%d  R%d  R%d", "and",r1, r2, r3);
  else if(opcode == OR)
    printf ("%-7sR%d  R%d  R%d","or", r1, r2,r3);
  else if(opcode == NOT)
    printf("%-7sR%d  R%d","not", r1, r2);
  else if(opcode == BR)
    printf("%-7sR%d  %05d","br", r1, mem);
  else if(opcode == READ)
    printf("%-7sR%d","read", r1);
  else if(opcode == WRT)
    printf("%-7sR%d","wrt",r1);
  else if(opcode == MOVE)
    printf("%-7sR%d  R%d","move", r1, r2);
  else if(opcode == LW)
    printf("%-7sR%d  %05d","lw", r1,mem);
  else if(opcode == SW)
    printf("%-7sR%d  %05d","sw", r1, mem);
  else if(opcode == LI)
    printf("%-7sR%d  %d","li", r1, mem);
  
  return 1;
}


/* this function receives an array of a program as well as */
/* the amount of instruction numbers followed by data nums */
/* manipulating other functions validates each entry and   */
/* will appropriately print out the required information   */
int disassemble(const Word program[], unsigned int instr_num,
		unsigned int data_num){

  unsigned int count = 0x0000;
  unsigned int i;

  /*validates the input of the parameters */
  if(program == NULL){
    return 0;
  } 
  if(instr_num == 0){
    return 0;
  }
  
  if( (instr_num + data_num) > NUM_WORDS){
    return 0;
  }

  /*loops through the array with the parameter'd size*/
  for(i = 0;i < (instr_num + data_num)-1 ; i++){

    /* appropriately prints out correct instr lines */
    while(i <  instr_num){
      if(is_valid_instruction(program[i]) == 0){
	return 0;
      }
      
      printf("%04x: ", count);
      print_instruction(program[i]);
      printf("\n");
      
      
      count = count + 0x4;
      i++;
    }

    /*appropriately prints out hexadecimal values for data num*/
    while(i >= instr_num && i <= (instr_num + data_num-1)){
      
      printf("%04x: %08x\n", count, program[i]);
      
      count = count + 0x4;
      i++;
    }
    
  }  
  return 1;
}


/* this function receives a single word to validate */
/* checks all appropriate aspects of the word       */
int is_valid_instruction(Word word){
  unsigned int opcode = word, mem = word;
  unsigned int r1 = word, r2 = word, r3 = word;
  
  /*mem receives value for the memory location*/
  mem = mem >> 9;
  mem = mem & 0x7ffff;
  
  /* places values into registers*/
  r1 = r1 >> 6;
  
  r2 = r2 >> 3;
  
  r3 = r3 & 0x7;
  r2 = r2 & 0x7;
  r1 = r1 & 0x7;
  
  opcode = opcode >> 28;
  opcode = opcode & 0xf;
  
  /*checks for invalid opcodes*/
  if(opcode >= 15 || opcode < 0){
    return 0;
  }
  
  
  /* checks for a modifying registers 0 or 6 */
  if(opcode != 8 && opcode != 10 && opcode!= 13){
    if(r1 == R0 || r1 == R6){
      return 0;
    }
  }
  
  /* begins checking opcodes*/
  if(opcode >= 0 && opcode <= 14){
    if(r1 != 8){
      
      /* finishes checking opcodes 0-6,9,10*/
      if((opcode <= 6 && opcode >= 0) ||
	 (opcode == 9 || opcode == 10)){
	if(r2 == 8 && r3 == 8)
	  return 0;
      }
      /* finishes opcodes 7 and 11 */
      if(opcode == 7 || opcode == 11){
	if(r2 == 8 || r3 == 8){
	  return 0;
	}
      }
      /* finishes checking opcodes 8 12 and 13*/
      if(opcode == 8 || opcode == 12 ||
	 opcode == 13){
	if(mem %4 != 0 || mem >= NUM_BYTES)
	  return 0;
      }
      /* checks opcode 14 which is special*/
      if(opcode == 14){
	if(mem < 0 || mem > 524287){
	  return 0;
	}
      }
	
    }
  }
  return 1;
}


/* this function moves the program into another array */
/* also offsets the memory location and counts how many */
/* times a memory location is changed */
int relocate(const Word program[], unsigned int instr_num,
	     unsigned int data_num, int offset, Word relocated_program[],
	     unsigned short *const num_changed_instr){
  
  unsigned int total = (instr_num + data_num -1);
  unsigned int opcode, registers, mem, moved;
  int i;
  
  /* checks for all invalid input cases */
  if(program == NULL){
    return 0;
  }
  if(offset %4 != 0){
    return 0;
  }
  if(num_changed_instr == NULL){
    return 0;
  }
  
  /*loops through the amount of data in array*/
  for(i = 0;i <= total; i++){
    
    /*focuses on the instruction amount in array */
    while(i < instr_num){
      	  
      opcode = program[i] >> 28;
      opcode = opcode & 0xf;
   
      /* INSTRUCTION SIDE */
      if(is_valid_instruction(program[i]) == 0){
	  return 0;
	}
      /* checks for opcode being memory utilized */
      if(opcode == 8 || opcode == 12
	 || opcode == 13 || opcode == 13)
	{
	  
	  registers = program[i] & 0x1ff;
	  mem = program[i] >> 9;
	  mem = mem & 0x7ffff;
	  mem = mem + offset;
	  /* checks the size of mem */
	  if(mem  > NUM_BYTES || mem < 0)
	      {
		return 0;
	      }
	  /*applies offset to memory */
	  moved = opcode;
	  moved = moved << 19;
	  moved = moved + mem;
	  moved = moved << 9;
	  moved = moved + registers;
	  /* relocates the memory within the
	     appropriate opcode */
	  relocated_program[i] = moved;
	  *num_changed_instr =  *num_changed_instr + 1;
	  i++;
	}
      else{
	
	relocated_program[i] = program[i];
	i++;
	
      }
    }
    
    /* checks the data to place directly into new array*/ 
    while(i >= instr_num && i <= total){
      /* DATA SIDE */
      relocated_program[i] = program[i];
      i++;
    }
  }
  return 1;
}


