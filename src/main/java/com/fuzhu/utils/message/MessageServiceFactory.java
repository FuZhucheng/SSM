package com.fuzhu.utils.message;

public class MessageServiceFactory {
	
	public static MessageService getMobileMessageService(){
		return new MessageServiceSupport(){
			public String getType() {
				return MessageServiceSupport.PHONO_MESSAGE_TYPE;
			}
		};
	}
	
}
