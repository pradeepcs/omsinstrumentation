package org.csp.instrument.oms.visitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class OmsIntegrationFlowClassVisitor extends ClassVisitor {



	public OmsIntegrationFlowClassVisitor(int api, ClassVisitor cv) {
		super(api, cv);
	}

	public MethodVisitor visitMethod(int paramIntAccess, String paramStringName, String paramStringDesc, String paramStringSignature,
			String[] paramStringArrayExceptions) {
		if (("invoke".equals(paramStringName))
				&& ("(Lcom/yantra/yfs/japi/YFSEnvironment;Ljava/lang/String;Lorg/w3c/dom/Document;)Lorg/w3c/dom/Document;"
						.equals(paramStringDesc))) {
			return super.visitMethod(paramIntAccess, "invoke1", paramStringDesc, paramStringSignature, paramStringArrayExceptions);
		}
		return super.visitMethod(paramIntAccess, paramStringName, paramStringDesc, paramStringSignature, paramStringArrayExceptions);
	}

	public void visitEnd() {
		MethodVisitor mv = super
				.visitMethod(
						1,
						"invoke",
						"(Lcom/yantra/yfs/japi/YFSEnvironment;Ljava/lang/String;Lorg/w3c/dom/Document;)Lorg/w3c/dom/Document;",
						null,
						new String[] { "com/yantra/yfs/japi/YFSException" });
		mv.visitCode();
		Label l0 = new Label();
		Label l1 = new Label();
		Label l2 = new Label();
		mv.visitTryCatchBlock(l0, l1, l2, null);
		Label l3 = new Label();
		mv.visitLabel(l3);
		mv.visitLineNumber(57, l3);
		mv.visitMethodInsn(184, "java/lang/System", "currentTimeMillis", "()J", false);
		mv.visitVarInsn(55, 4);
		Label l4 = new Label();
		mv.visitLabel(l4);
		mv.visitLineNumber(58, l4);
		mv.visitInsn(1);
		mv.visitVarInsn(58, 6);
		mv.visitLabel(l0);
		mv.visitLineNumber(60, l0);
		mv.visitVarInsn(25, 1);
		mv.visitVarInsn(25, 2);
		mv.visitVarInsn(25, 3);
		mv.visitMethodInsn(
				184,
				"org/csp/instrument/oms/transform/OmsTrace",
				"startAPIExecutionLog",
				"(Lcom/yantra/yfs/japi/YFSEnvironment;Ljava/lang/String;Lorg/w3c/dom/Document;)V", false);
		Label l5 = new Label();
		mv.visitLabel(l5);
		mv.visitLineNumber(61, l5);
		mv.visitVarInsn(25, 0);
		mv.visitVarInsn(25, 1);
		mv.visitVarInsn(25, 2);
		mv.visitVarInsn(25, 3);
		mv.visitMethodInsn(
				182,
				"com/yantra/integration/adapter/DefaultIntegrationFlow",
				"invoke1",
				"(Lcom/yantra/yfs/japi/YFSEnvironment;Ljava/lang/String;Lorg/w3c/dom/Document;)Lorg/w3c/dom/Document;", false);
		mv.visitVarInsn(58, 6);
		Label l6 = new Label();
		mv.visitLabel(l6);
		mv.visitLineNumber(62, l6);
		mv.visitVarInsn(25, 6);
		mv.visitVarInsn(58, 8);
		mv.visitLabel(l1);
		mv.visitLineNumber(64, l1);
		mv.visitVarInsn(25, 1);
		mv.visitVarInsn(25, 2);
		mv.visitVarInsn(25, 3);
		mv.visitVarInsn(25, 6);
		mv.visitVarInsn(22, 4);
		mv.visitMethodInsn(184, "java/lang/System", "currentTimeMillis", "()J", false);
		mv.visitMethodInsn(
				184,
				"org/csp/instrument/oms/transform/OmsTrace",
				"endAPIExecutionLog",
				"(Lcom/yantra/yfs/japi/YFSEnvironment;Ljava/lang/String;Lorg/w3c/dom/Document;Lorg/w3c/dom/Document;JJ)V", false);
		Label l7 = new Label();
		mv.visitLabel(l7);
		mv.visitLineNumber(62, l7);
		mv.visitVarInsn(25, 8);
		mv.visitInsn(176);
		mv.visitLabel(l2);
		mv.visitLineNumber(63, l2);
		
		
		mv.visitFrame(0, 6, new Object[] {
				"com/yantra/integration/adapter/DefaultIntegrationFlow",
				"com/yantra/yfs/japi/YFSEnvironment", "java/lang/String",
				"org/w3c/dom/Document", Opcodes.LONG, "org/w3c/dom/Document" },
				1, new Object[] { "java/lang/Throwable" });
		mv.visitVarInsn(58, 7);
		Label l8 = new Label();
		mv.visitLabel(l8);
		mv.visitLineNumber(64, l8);
		mv.visitVarInsn(25, 1);
		mv.visitVarInsn(25, 2);
		mv.visitVarInsn(25, 3);
		mv.visitVarInsn(25, 6);
		mv.visitVarInsn(22, 4);
		mv.visitMethodInsn(184, "java/lang/System", "currentTimeMillis", "()J", false);
		mv.visitMethodInsn(
				184,
				"org/csp/instrument/oms/transform/OmsTrace",
				"endAPIExecutionLog",
				"(Lcom/yantra/yfs/japi/YFSEnvironment;Ljava/lang/String;Lorg/w3c/dom/Document;Lorg/w3c/dom/Document;JJ)V", false);
		Label l9 = new Label();
		mv.visitLabel(l9);
		mv.visitLineNumber(65, l9);
		mv.visitVarInsn(25, 7);
		mv.visitInsn(191);
		Label l10 = new Label();
		mv.visitLabel(l10);
		mv.visitLocalVariable("this",
				"Lcom/yantra/integration/adapter/DefaultIntegrationFlow;",
				null, l3, l10, 0);
		mv.visitLocalVariable("env", "Lcom/yantra/yfs/japi/YFSEnvironment;",
				null, l3, l10, 1);
		mv.visitLocalVariable("transactionName", "Ljava/lang/String;", null,
				l3, l10, 2);
		mv.visitLocalVariable("transactionData", "Lorg/w3c/dom/Document;",
				null, l3, l10, 3);
		mv.visitLocalVariable("startTime", "J", null, l4, l10, 4);
		mv.visitLocalVariable("outDoc", "Lorg/w3c/dom/Document;", null, l0,
				l10, 6);
		mv.visitMaxs(8, 9);
		mv.visitEnd();
	}
	

}
