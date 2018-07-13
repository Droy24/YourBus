package com.controller;

import java.net.URI;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;

@RestController
@RequestMapping("/sms")
public class SmsController {
	public static final String ACCOUNT_SID = "****";
	public static final String AUTH_TOKEN = "******";

	@GetMapping
	public String sendSms() {
		System.out.println("in send sms");
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Message message = Message
				.creator(new com.twilio.type.PhoneNumber("***********"),
						new com.twilio.type.PhoneNumber("***********"), "Let's grab lunch at Milliways tomorrow!")
				.setMediaUrl(Promoter.listOfOne(URI.create("http://www.example.com/cheeseburger.png"))).create();

		System.out.println(message.getSid());
		return " Sms sent successfully";
	}
}
