package com.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.config.PropConfig;

import com.utility.Mail;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    private TemplateEngine templateEngine;
	private PropConfig propConfig;
//    @Autowired
//    private SpringTemplateEngine templateEngine;
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public EmailService(JavaMailSender mailSender, TemplateEngine templateEngine, PropConfig propConfig) {
		this.mailSender = mailSender;
		this.templateEngine = templateEngine;
		this.propConfig = propConfig;
	}
	
	public void sendEMail() {
		logger.info("To send EMail");
		final String sendFrom= "devasheesh.roy@wittybrains.com";
		final String sendTo="devasheesh.roy@wittybrains.com";
		final String subject= "Project mail";
		final String message="Test message";
		List<String> ccEmailList = new ArrayList<String>();
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {

				// R16-812 add cc list to all emails
				if (ccEmailList != null && !ccEmailList.isEmpty()) {
					InternetAddress[] recipientAddress = new InternetAddress[ccEmailList.size()];
					for (int i = 0; i <= ccEmailList.size() - 1; i++) {
						String ccId = ccEmailList.get(i);
						recipientAddress[i] = new InternetAddress(ccId);
					}
					mimeMessage.setRecipients(javax.mail.Message.RecipientType.CC, recipientAddress);
				}
				mimeMessage.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(sendTo, sendTo));

				// R16-812 end

				mimeMessage.setFrom(new InternetAddress(sendFrom, sendFrom));
				mimeMessage.setSubject(subject);
				InternetHeaders headers = new InternetHeaders();
				headers.addHeader("Content-type", "text/html; charset=UTF-8");

				MimeBodyPart body = new MimeBodyPart(headers, message.getBytes("UTF-8"));
				MimeMultipart multipart = new MimeMultipart();

				multipart.addBodyPart(body);
				mimeMessage.setContent(multipart);

			}
		};

		EmailService.this.mailSender.send(preparator);
		logger.warn("Email sent to: " + sendTo);
	}
	

    public void sendSimpleMessage(Mail mail) throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

//        helper.addAttachment("logo.png", new ClassPathResource("memorynotfound-logo.png"));

        Context context = new Context();
        context.setVariables(mail.getModel());
//        context.setVariables(variables);
        String html = templateEngine.process("email-template", context);

        helper.setTo(mail.getTo());
        helper.setText(html, true);
        helper.setSubject(mail.getSubject());
        helper.setFrom(mail.getFrom());

        mailSender.send(message);
    }

}
