package org.csp.instrument.oms.visitor;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class OmsJmsPublisherMethodVisitor extends MethodVisitor implements Opcodes {

	public OmsJmsPublisherMethodVisitor(int api, MethodVisitor mv) {
		super(api, mv);
	}

	@Override
	public void visitCode() {
		mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
		mv.visitInsn(DUP);
		mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
		mv.visitLdcInsn("Omsinstrumentation Start JMS Message:::: ");
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
		mv.visitVarInsn(ALOAD, 1);
		mv.visitMethodInsn(INVOKEINTERFACE, "com/yantra/interop/services/ServiceMessage", "getXMLData", "()Ljava/lang/String;", true);
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
		mv.visitLdcInsn("Omsinstrumentation End JMS Message:::: ");
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
	}
}
