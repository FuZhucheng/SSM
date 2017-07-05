package com.fuzhu.utils;

public class EmailServiceFactory {

	public static EmailService getEmailService(){
		return new EmailServiceImpl();
	}
}
