package com.fuzhu.utils.message;

import com.fuzhu.entity.CfMessage;
import com.fuzhu.entity.User;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public abstract class MessageServiceSupport implements MessageService {

	private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";

	public abstract String getType();
	
	public static String PHONO_MESSAGE_TYPE = "1";
	


	protected boolean checkAcceptor(String acceptor){
		if(acceptor == null || "".equals(acceptor)){
			return false;
		}


		 if(PHONO_MESSAGE_TYPE.equals(getType())){
			// TODO 验证手机号码
		}

		return true;
	}
	


	public boolean send(String acceptorName, String acceptor, String context){
		return send(acceptorName, acceptor, context, "无标题");
	}
	
	public boolean send(String acceptorName, String acceptor, String context, String title){
		if(checkAcceptor(acceptor)){
			try {
				//如果有业务需求要保存，就用此bean保存。
				CfMessage message = new CfMessage();
				message.setType(getType());
				message.setAcceptor(acceptor);
				message.setAcceptorname(acceptorName);
				message.setTitle(title);
				message.setContext(context);
				message.setCreatetime(new Date());
				message.setRecordstatus(1);
				//前面应该加短信内容状态判断
				//TODO  短信内容判断，因为一般第三方的API会根据内容去发送
				//调用发送短信
				sendMessage(acceptor,context);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	public void sendMessage(String acceptor,String content){
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(Url);
		client.getParams().setContentCharset("GBK");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=GBK");
		NameValuePair[] data = {//提交短信
				new NameValuePair("account", ""),
				new NameValuePair("password", ""), //查看密码请登录用户中心->验证码、通知短信->帐户及签名设置->APIKEY
				//new NameValuePair("password", util.StringUtil.MD5Encode("密码")),
				new NameValuePair("mobile", acceptor),
				new NameValuePair("content", content),
		};
		method.setRequestBody(data);

		try {
			client.executeMethod(method);//发送短信

			Header[] headers = method.getResponseHeaders();//短信返回信息
			int statusCode = method.getStatusCode();
			System.out.println("statusCode:" + statusCode);
			for (Header h : headers) {
				System.out.println(h.toString());
			}
			String result = null;

			result = new String(method.getResponseBodyAsString().getBytes(
					"gbk"));

			System.out.println(result);
			method.releaseConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	

	public boolean[] send(String acceptorName, String[] acceptors, String context) {
		if(acceptors == null || acceptors.length == 0){
			return null;
		}
		boolean[] result = new boolean[acceptors.length];
		int i = 0;
		for(String acceptor : acceptors){
			result[i++] = send(acceptorName, acceptor, context);
		}
		return result;
	}
	
	public boolean send(Long acceptorUserId, String context){
		if(acceptorUserId == null ){
			return false;
		}
		try {
			List<User> userList=null ;
			if(userList != null && userList.size() == 1){

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean send(String acceptorLoginId, String context){
		if(acceptorLoginId == null || "".equals(acceptorLoginId)){
			return false;
		}
		try {
			List<User> userList=null ;
			if(userList != null && userList.size() == 1){
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
