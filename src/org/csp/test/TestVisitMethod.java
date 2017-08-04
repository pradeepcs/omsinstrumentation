package org.csp.test;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class TestVisitMethod extends MethodVisitor implements Opcodes {

	public TestVisitMethod(int api, MethodVisitor mv) {
		super(api, mv);
	}
	
	@Override
	public void visitCode() {
//		mv.visitCode();
		mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mv.visitLdcInsn("FirstModified");
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
	}
}
