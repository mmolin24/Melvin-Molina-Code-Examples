isqrt: 	sub	$sp, $sp, 8 	#PROLOGUE
	sw	$ra, 8($sp)
	sw	$fp, 4($sp)
	add 	$fp, $sp, 8 

	
	sub 	$sp, $sp, 8	#grow stack for small and large local vars
	li	$t0, 0
	li 	$t1, 0
	sw	$t0, 8($sp)
	sw	$t1, 4($sp)
	
	bge	$a0, 2, recurse
	move	$v0, $a0
	j 	ret

	
recurse:	
	sub 	$sp, $sp, 4
	sw	$a0, 4($sp) 	#save original n into the stack
	la	$t3, ($a0)
	
	
	srl	$t3, $t3, 2	# right shift the n value
	move 	$a0, $t3	#modify n to pass into recursion
	
	jal 	isqrt 		#set up for the recursive recall

	
	sll	$t1, $v0, 1	#left shifts result
	sw	$t1, 12($sp)	#saves small in the stack

	
	add	$t2, $t1, 1	#makes large	
	sw 	$t2, 8($sp)	#saves large in the stack

	
	mul	$t3, $t2, $t2 	#squares large for if statement
	lw	$t4, 4($sp)	#loads original n from stack
	
	
	bge	$t4, $t3, lrg	#if statement to check which returns

	move	$v0, $t1 	#puts smaller into return value
	j ret
	
lrg:	move	$v0, $t2	#puts larger into return value
	j ret
	


ret:	move 	$sp, $fp
	lw 	$ra, ($fp)
	lw	$fp, -4($sp)
	jr	$ra
