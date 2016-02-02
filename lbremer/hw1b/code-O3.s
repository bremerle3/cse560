	.file	"code.c"
	.text
	.p2align 4,,15
	.globl	saxpy
	.type	saxpy, @function
saxpy:
.LFB0:
	.cfi_startproc
	movss	a(%rip), %xmm1
	xorl	%eax, %eax
	.p2align 4,,10
	.p2align 3
.L3:
	movss	x(%rax), %xmm0
	addq	$4, %rax
	mulss	%xmm1, %xmm0
	addss	y-4(%rax), %xmm0
	movss	%xmm0, z-4(%rax)
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
