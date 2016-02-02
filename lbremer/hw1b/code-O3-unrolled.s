	.file	"code.c"
	.text
	.p2align 4,,15
	.globl	saxpy
	.type	saxpy, @function
saxpy:
.LFB0:
	.cfi_startproc
	movss	a(%rip), %xmm0
	xorl	%eax, %eax
	.p2align 4,,10
	.p2align 3
.L3:
	movss	x(%rax), %xmm1
	addq	$32, %rax
	movss	x-28(%rax), %xmm2
	mulss	%xmm0, %xmm1
	movss	x-24(%rax), %xmm3
	mulss	%xmm0, %xmm2
	movss	x-20(%rax), %xmm4
	mulss	%xmm0, %xmm3
	movss	x-16(%rax), %xmm5
	mulss	%xmm0, %xmm4
	movss	x-12(%rax), %xmm6
	mulss	%xmm0, %xmm5
	movss	x-8(%rax), %xmm7
	mulss	%xmm0, %xmm6
	movss	x-4(%rax), %xmm8
	mulss	%xmm0, %xmm7
	mulss	%xmm0, %xmm8
	addss	y-32(%rax), %xmm1
	addss	y-28(%rax), %xmm2
	addss	y-24(%rax), %xmm3
	addss	y-20(%rax), %xmm4
	addss	y-16(%rax), %xmm5
	addss	y-12(%rax), %xmm6
	addss	y-8(%rax), %xmm7
	movss	%xmm1, z-32(%rax)
	addss	y-4(%rax), %xmm8
	movss	%xmm2, z-28(%rax)
	movss	%xmm3, z-24(%rax)
	movss	%xmm4, z-20(%rax)
	movss	%xmm5, z-16(%rax)
	movss	%xmm6, z-12(%rax)
	movss	%xmm7, z-8(%rax)
	movss	%xmm8, z-4(%rax)
	cmpq	$262144, %rax
	jne	.L3
	rep ret
	.cfi_endproc
.LFE0:
	.size	saxpy, .-saxpy
	.comm	a,4,4
	.comm	z,262144,32
	.comm	y,262144,32
	.comm	x,262144,32
	.ident	"GCC: (GNU) 4.8.1 20130603 (Red Hat 4.8.1-1)"
	.section	.note.GNU-stack,"",@progbits
