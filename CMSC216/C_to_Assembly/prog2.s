	#prog2.c converted into prog2.s
	#UID 116944243
	#Section 0301
	#I pledge my honor

	.data
x:	.word 0
y:	.word 0
result:	.word 0

	.text

main:	li	$sp, 0x7ffffffc #sets up stack pointer
	sw 	$t0, x
	sw	$t1, y
	sw	$t2, result

	#receive input for X
	li	$v0, 5
	syscall

	move	$t0, $v0

	#gets second integer to place into y
	li 	$v0, 5
	syscall

	move 	$t1, $v0

	sw	$t0, x
	sw	$t1, y

	#stores values of X and Y
	sw	$t0, ($sp)
	sub	$sp, $sp, 4
	sw	$t1, ($sp)
	sub 	$sp, $sp, 4

	#calls function count digit
	jal 	count_digit

	#stores return value
	add	$sp, $sp, 8 
	move	$t2, $v0
	sw	$t2, result

	#prints out count
	li	$v0, 1
	lw	$a0, result
	syscall

	#prints new line
	li	$a0, 10
	li	$v0, 11
	syscall
	
	#exit program
	li	$v0, 10	
	syscall


	
count_digit:
	#prologue
	sub	$sp, $sp, 12
	sw	$ra, 12($sp)
	sw	$fp, 8($sp)
	add	$fp, $sp, 12

	# num and digit contain parameter values
	lw	$t1, 8($fp)
	lw	$t2, 4($fp)

	#creates count and rightmost
	li	$t3, -1
	li	$t4, 0
	
	#following saves count
	sw	$t3, 8($sp)
	
	#following saves rightmost digit
	sw	$t4, 4($sp)

	# if digit >= 0 and digit <= 9
	blt 	$t2, 0, end
	bgt	$t2, 9, end

	li 	$t3, 0

	#if num < 0
	bge	$t1, 0, nega
	mul	$t1, $t1, -1
	sw	$t1, 8($fp)

	#of num = 0 and digit = 0
nega:	bne	$t1, 0, else
	bne	$t2, 0, else

	li 	$t3, 1
	beqz 	$t1, end
	beqz	$t2, end

	#while num >0
else:	ble 	$t1, 0, end
	rem 	$t4, $t1, 10

	#if rightmost is equal to digit
	bne	$t4, $t2, noteq
	add	$t3, $t3, 1
	sw	$t3, 8($sp)
	
	#regardless divides num by 10
noteq:	div	$t1, $t1, 10
	bgt 	$t1, 0, else

	#return statement outside of all if statements
	
end:	move	$v0, $t3
	lw	$ra, 12($sp)
	lw	$fp, 8($sp)
	add	$sp, $sp, 12
	jr	$ra
	


	
