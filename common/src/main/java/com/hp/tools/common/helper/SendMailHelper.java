/**
 * 
 */
package com.hp.tools.common.helper;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @author ping.huang
 * 2017年3月30日
 */
public class SendMailHelper {

	static Logger log = LoggerFactory.getLogger(SendMailHelper.class);
	
	JavaMailSenderImpl javaMailSenderImpl;
	
	@Value("${mail.send.from:xxx}")
	private String from;
	
	/**
	 * 发送邮件
	 * @param message
	 */
	public void sendSimpleMailMessage(SimpleMailMessage message) {
		log.info("sendSimpleMailMessage start. with message={}", message);
		if (ArrayUtils.isEmpty(message.getTo())) {
			log.warn("sendSimpleMailMessage error. to is empty. with message={}", message);
			return;
		}
		message.setFrom(from);
		javaMailSenderImpl.send(message);
		log.info("sendSimpleMailMessage success. with message={}", message);
	}
	
	/**
	 * 发送邮件
	 * @param message
	 */
	public void sendMimeMessage(MimeMessage message) {
		log.info("sendMimeMessage start. with message={}", message);
		javaMailSenderImpl.send(message);
		log.info("sendMimeMessage success. with message={}", message);
	}

	public void setJavaMailSenderImpl(JavaMailSenderImpl javaMailSenderImpl) {
		this.javaMailSenderImpl = javaMailSenderImpl;
	}
}
