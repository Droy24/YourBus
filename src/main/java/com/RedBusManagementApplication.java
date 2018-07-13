package com;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.service.EmailService;
import com.utility.Mail;

@SpringBootApplication
@EnableAutoConfiguration
public class RedBusManagementApplication {
//	private static Logger log = LoggerFactory.getLogger(Application.class);

	@Autowired
	private EmailService emailService;

	public static void main(String[] args) {
		SpringApplication.run(RedBusManagementApplication.class, args);

	}

	public void run(ApplicationArguments applicationArguments) throws Exception {
		

		Mail mail = new Mail();
		mail.setFrom("no-reply@memorynotfound.com");
		mail.setTo("info@memorynotfound.com");
		mail.setSubject("Sending Email with Thymeleaf HTML Template Example");

		Map model = new HashMap();
		model.put("name", "Memorynotfound.com");
		model.put("location", "Belgium");
		model.put("signature", "https://memorynotfound.com");
		mail.setModel(model);

		emailService.sendSimpleMessage(mail);
	}

}
