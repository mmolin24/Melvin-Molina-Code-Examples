reverse_prefix_sum:
	addi	$sp, $sp, -8
	sw	$a0, 0($sp)
	sw	$ra, 4($sp)

	lw	$t0, 0($a0)

	beq	$t0, -1, ret

	addi 	$a0, $a0, 4

	jal reverse_prefix_sum

	lw	$a0, 0($sp)
	lw	$t0, 0($a0)

	addu	$v0, $v0, $t0

	lw	$ra, 4($sp)
	addi	$sp, $sp, 8

	sw	$v0, 0($a0)

	jr	$ra

ret:	lw	$a0, 0($sp)
	lw	$ra, 4($sp)

	addi	$sp, $sp, 8

	li	$v0, 0
	jr	$ra
