/**
 * 
 */
package com.hp.tools.common.queue;

import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ping.huang
 * 2017年3月30日
 */
public class HPQueueProducer {

	
	static Logger log = LoggerFactory.getLogger(HPQueueProducer.class);
	
	/**
	 * 发送
	 * @param topic
	 * @param message
	 */
	public void send(String topic, Object message) {
		BlockingQueue<Object> queue = HPQueueFactory.getInstance().getQueue(topic);
		try {
			queue.put(message);
		} catch (InterruptedException e) {
			log.error("put message into queue error. with topic={}", topic, e);
		}
	}
	
}
