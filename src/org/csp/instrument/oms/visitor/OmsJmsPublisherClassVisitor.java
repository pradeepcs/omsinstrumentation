package org.csp.instrument.oms.visitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class OmsJmsPublisherClassVisitor extends ClassVisitor implements Opcodes {

	public OmsJmsPublisherClassVisitor(int api, ClassVisitor cv) {
		super(api, cv);
	}

	public MethodVisitor visitMethod(int paramIntAccess, String paramStringName, String paramStringDesc,
			String paramStringSignature, String[] paramStringArrayExceptions) {
		MethodVisitor mv = super.visitMethod(paramIntAccess, paramStringName, paramStringDesc,
				paramStringSignature, paramStringArrayExceptions);
		if (("send".equals(paramStringName))
				&& ("(Lcom/yantra/interop/services/ServiceMessage;Lcom/yantra/yfs/japi/YFSEnvironment;Lcom/yantra/interop/services/jms/JMSMessageIDHolder;)Lcom/yantra/interop/services/ServiceMessage;"
						.equals(paramStringDesc))) {
			return new OmsJmsPublisherMethodVisitor(api, mv);
		}
		return mv;
	}
}
