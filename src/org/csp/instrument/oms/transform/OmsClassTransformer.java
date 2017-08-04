package org.csp.instrument.oms.transform;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import org.csp.instrument.oms.visitor.OmsIntegrationFlowClassVisitor;
import org.csp.instrument.oms.visitor.OmsJmsPublisherClassVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

public class OmsClassTransformer implements ClassFileTransformer {

	@Override
	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
			ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
		if ("com/yantra/integration/adapter/DefaultIntegrationFlow".equals(className) || 
				"com/yantra/interop/services/jms/JMSProducer".equals(className)) {
			return getClassTransformer(classfileBuffer, className);
		}
		return null;
	}
	
	
	private byte[] getClassTransformer(byte[] classfileBuffer, String className) {
		ClassReader classReader = new ClassReader(classfileBuffer);
		ClassWriter classWriter = new ClassWriter(0);
		ClassVisitor omsClassVisitor = null;
		if("com/yantra/integration/adapter/DefaultIntegrationFlow".equals(className)) {
			omsClassVisitor = new OmsIntegrationFlowClassVisitor(Opcodes.ASM5, classWriter);
		} else {
			omsClassVisitor = new OmsJmsPublisherClassVisitor(Opcodes.ASM5, classWriter);
		}
		classReader.accept(omsClassVisitor, 0);
		return classWriter.toByteArray();
	}
	
}
