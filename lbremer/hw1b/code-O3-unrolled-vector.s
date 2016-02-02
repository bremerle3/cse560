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
	shufps	$0, %xmm0, %xmm0
.L3:
	movaps	x(%rax), %xmm1
	subq	$-128, %rax
	movaps	x-112(%rax), %xmm2
	mulps	%xmm0, %xmm1
	movaps	x-96(%rax), %xmm3
	mulps	%xmm0, %xmm2
	movaps	x-80(%rax), %xmm4
	mulps	%xmm0, %xmm3
	movaps	x-64(%rax), %xmm5
	mulps	%xmm0, %xmm4
	addps	y-128(%rax), %xmm1
	movaps	x-48(%rax), %xmm6
	mulps	%xmm0, %xmm5
	addps	y-112(%rax), %xmm2
	movaps	x-32(%rax), %xmm7
	mulps	%xmm0, %xmm6
	addps	y-96(%rax), %xmm3
	movaps	x-16(%rax), %xmm8
	mulps	%xmm0, %xmm7
	addps	y-80(%rax), %xmm4
	movaps	%xmm1, z-128(%rax)
	mulps	%xmm0, %xmm8
	movaps	%xmm2, z-112(%rax)
	movaps	%xmm3, z-96(%rax)
	movaps	%xmm4, z-80(%rax)
	addps	y-64(%rax), %xmm5
	addps	y-48(%rax), %xmm6
	addps	y-32(%rax), %xmm7
	addps	y-16(%rax), %xmm8
	movaps	%xmm5, z-64(%rax)
	movaps	%xmm6, z-48(%rax)
	movaps	%xmm7, z-32(%rax)
	movaps	%xmm8, z-16(%rax)
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
