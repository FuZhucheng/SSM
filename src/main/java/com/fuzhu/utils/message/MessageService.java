package com.fuzhu.utils.message;

public interface MessageService {
	
	/**
	 * 
	 * @param acceptorName 消息接收者名称
	 * @param acceptor 消息接收者,若是短信为手机号码,若是邮件则是邮箱地址
	 * @param context 消息内容
	 * @return 成功返回true, 内部采用异步机制,这里返回成功只是代表初步校验成功,比如手机号码或邮箱格式校验
	 */
	public boolean send(String acceptorName, String acceptor, String context);
	
	/**
	 * 
	 * @param acceptorName 消息接收者名称
	 * @param acceptor 消息接收者,若是短信为手机号码,若是邮件则是邮箱地址
	 * @param context 消息内容
	 * @return 成功返回true, 内部采用异步机制,这里返回成功只是代表初步校验成功,比如手机号码或邮箱格式校验
	 */
	public boolean[] send(String acceptorName, String[] acceptor, String context);
	

	public boolean send(String acceptorName, String acceptor, String context, String title);
	
	/**
	 * 
	 * @param acceptorUserId 接收者的用户ID, cf_user.userid
	 * @param context
	 * @return
	 */
	public boolean send(Long acceptorUserId, String context);
	
	/**
	 * 
	 * @param acceptorLoginId 接收者的登陆ID, cf_user.loginid
	 * @param context
	 * @return
	 */
	public boolean send(String acceptorLoginId, String context);

}
