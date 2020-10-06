fibonacci:
	
	bgt 	$a0, 1, recurse
	move	$v0, $a0
	jr 	$ra
	
recurse:
	sub 	$sp, $sp, 12
	sw	$ra, 0($sp)

	sw	$a0, 4($sp)
	add	$a0, $a0, -1

	jal 	fibonacci

	lw	$a0, 4($sp)
	sw	$v0, 8($sp)

	add	$a0, $a0, -2
	jal 	fibonacci

	lw	$t0, 8($sp)
	add	$v0, $t0, $v0

	lw	$ra, 0($sp)
	add	$sp, $sp, 12
	jr	$ra

