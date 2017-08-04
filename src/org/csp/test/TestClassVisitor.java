package org.csp.test;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class TestClassVisitor extends ClassVisitor {

	public TestClassVisitor(int api, ClassVisitor cv) {
		super(api, cv);
	}
	
	@Override
	public MethodVisitor visitMethod(int paramIntAccess, String paramStringName, String paramStringDesc, String paramStringSignature,
			String[] paramStringArrayExceptions) {
		if ("printTest".equals(paramStringName)) {
			MethodVisitor mv = super.visitMethod(paramIntAccess, paramStringName, paramStringDesc, paramStringSignature, paramStringArrayExceptions);
			return new TestVisitMethod(api, mv);
		}
		return super.visitMethod(paramIntAccess, paramStringName, paramStringDesc, paramStringSignature, paramStringArrayExceptions);
	}

	@Override
	public void visitEnd() {
		super.visitEnd();
	}
}
