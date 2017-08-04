package org.csp.instrument.oms.agent;

import java.lang.instrument.Instrumentation;

import org.csp.instrument.oms.transform.OmsClassTransformer;

public class IntegrationAdapter {

	public static void premain(String agentArgs, Instrumentation inst) {
		inst.addTransformer(new OmsClassTransformer());
	}

}