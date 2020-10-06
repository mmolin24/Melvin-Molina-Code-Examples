	#program1.c converted into prog1.s
	#UID 116944243
	#Section 0301
	#I pledge my honor

	.text

main:	li 	$v0, 5
	syscall

	#place the digit into register t0
	
	move	$t0, $v0
	#place the digit received into n
	
	sw	$t0, n

	#loads ans into register t1
	
	lw 	$t1, ans

	#checks if n is greater than 0 continues
	#if n is less than 0 then skip to the print
	
	blt 	$t0, 0, if

	#creates the top side of fractions
	
	add 	$t2, $t0, 1
	mul 	$t3, $t0, $t2

	#creates fraction
	
	div 	$t4, $t3, 2

	#multiplied by itself
	mul 	$t1, $t4, $t4
	
	#stores result into ans
	sw 	$t1, ans	

if:	
	move 	$a0, $t1
	#prepares to print out the ans
	li 	$v0, 1
	syscall

	#prints out new line
	li 	$a0, 10
	li	$v0, 11
	syscall
	
	#exits program
	li 	$v0, 10
	syscall
	
	.data
n:	.word 0
ans:	.word -1
	
