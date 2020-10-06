	#is_palindrome

strlen:
   	# PROLOGUE
 
	sub     $sp, $sp, 12 
	sw      $ra, 12($sp) 
	sw      $fp, 8($sp) 
	add     $fp, $sp, 12 

	#BODY
	li $t0, 0 # initialize the count to zero
	la 	$t6, ($a0)
loop:
	lb $t1, 0($t6) # load the next character into t1
	beqz $t1, exit # check for the null character
	addi $t6, $t6, 1 # increment the string pointer
	addi $t0, $t0, 1 # increment the count
	j loop # return to the top of the loop

exit:	move $v0, $t0		
	
	#EPILOGUE
	lw      $ra, 12($sp) 
	lw      $fp, 8($sp) 
	add     $sp, $sp, 12 
	jr      $ra 	


	
is_palindrome:

	#prologue
	sub     $sp, $sp, 12 
	sw      $ra, 12($sp) 
	sw      $fp, 8($sp) 
	add     $fp, $sp, 12
	
	# a0 has the string required
	li 	$t0, 0
	li 	$t1, 0
	
	
	jal strlen		
	
	# setting up the for loop
	# len/2 is in $t1
	div $t1, $v0, 2
	la 	$t4,($a0) # t4 has the parameter string
	li 	$t0, 0 # i

lop:
	# creates the required index for far part
	sub $t5, $v0, $t0
	sub $t5, $t5, 1
	
       	
	add $t2, $t4, $t0
	add $t3, $t4, $t5
	
	lb $t2, 0($t2)
	lb $t3, 0($t3)
	
	add $t0, $t0, 1

	bne $t2, $t3, badend
	

	#t0 is i
	#t1 has div
	
	bgt $t1, $t0, lop
	
	j goodend




goodend:	li $v0, 1
	j end

badend:	li $v0, 0
	j end	
	
end:	lw      $ra, 12($sp) 
	lw      $fp, 8($sp) 
	add     $sp, $sp, 12 
	jr      $ra 	
	
