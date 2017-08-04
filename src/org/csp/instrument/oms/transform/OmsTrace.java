package org.csp.instrument.oms.transform;

import java.io.StringWriter;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import com.yantra.yfs.japi.YFSEnvironment;

public class OmsTrace {

	static Logger logger = Logger.getLogger(OmsTrace.class.getName());

	private static final ThreadLocal<LogData> logDataHolder = new ThreadLocal<LogData>() {
		@Override
		protected OmsTrace.LogData initialValue() {
			return new OmsTrace.LogData();
		}
	};

	public static void startAPIExecutionLog(YFSEnvironment env, String apiName, Document inDoc) {
		LogData logData = (LogData) logDataHolder.get();
		logData.rollLevel(1);
		if (logger.isLoggable(Level.INFO)) {
			logger.info(logData.getOffsetStringForCurrentLevel() + "[API:" + apiName + "]");
		}
	}

	public static void endAPIExecutionLog(YFSEnvironment env, String apiName, Document inDoc, Document outDoc,
			long startTime, long endTime) {
		LogData logData = (LogData) logDataHolder.get();
		if (logger.isLoggable(Level.INFO)) {
			//logger.info("Input :: " + getStringFromDoc(inDoc));
			logger.info(logData.getOffsetStringForCurrentLevel() + "[API:" + apiName + "]("
					+ getExecutionTimeString(startTime, endTime) + ")");
			//logger.info("Output :: " + getStringFromDoc(outDoc));
		}
		logData.rollLevel(-1);
	}

	public static void startFlowExecutionLog(YFSEnvironment env, String serviceName, Document inDoc) {
		LogData logData = (LogData) logDataHolder.get();
		logData.rollLevel(1);
		if (logger.isLoggable(Level.INFO)) {
			logger.info(logData.getOffsetStringForCurrentLevel() + "[" + serviceName + "]");
		}
	}

	public static void endFlowExecutionLog(YFSEnvironment env, String serviceName, Document inDoc, Document outDoc,
			long startTime, long endTime) {
		LogData logData = (LogData) logDataHolder.get();

		if (logger.isLoggable(Level.INFO)) {
			//logger.info("Input :: " + getStringFromDoc(inDoc));
			logger.info(logData.getOffsetStringForCurrentLevel() + "[" + serviceName + "]("
					+ getExecutionTimeString(startTime, endTime) + ")");
			//logger.info("Output :: " + getStringFromDoc(outDoc));
		}
		logData.rollLevel(-1);
	}

	
	public static void printJmsMsg(String msg) {
		LogData logData = (LogData) logDataHolder.get();
		if (logger.isLoggable(Level.INFO)) {
			logger.info("Jms Message :: " + msg);
		}
		logData.rollLevel(-1);
	}

	
	public static boolean getTrue() {
		return true;
	}
	
	private static String getExecutionTimeString(long startTime, long endTime) {
		return (endTime - startTime) + "ms";
	}

	private static class LogData {
		private int level;
		private Hashtable<Integer, String> stringHash;

		private LogData() {
			this.level = -1;
			this.stringHash = new Hashtable<Integer, String>(10);
		}

		public int getLevel() {
			return this.level;
		}

		public void rollLevel(int i) {
			this.level += i;
		}

		public Hashtable<Integer, String> getStringHash() {
			return this.stringHash;
		}

		public String getOffsetStringForCurrentLevel() {
			if (getLevel() <= 0)
				return "";
			int level = getLevel();
			Hashtable<Integer, String> stringHash = getStringHash();
			String offsetString = (String) stringHash.get(Integer.valueOf(level));
			if (offsetString == null) {
				if (getLevel() == 1) {
					stringHash.put(Integer.valueOf(1), " ");
				} else {
					stringHash.put(Integer.valueOf(level), " " + ((String) stringHash.get(Integer.valueOf(level - 1))));
				}
				offsetString = (String) stringHash.get(Integer.valueOf(level));
			}
			return offsetString;
		}
	}

	public static String getStringFromDoc(Document doc) {
		try {
			DOMSource domSource = new DOMSource(doc);
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer;
			transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			// transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(domSource, result);
			writer.flush();
			return writer.toString();
		} catch (TransformerException e) {
			return "";
		}
	}
}
