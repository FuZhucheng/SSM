package com.fuzhu.utils.message;


import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;

/**
 * Created by asus on 2017/6/27.
 */
public class MessageTest {


    private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";

    public static void main(String[] args) {

        /*
            方式一：
         */
        //content：发送的内容；telephoneSend要发送的电话
        int mobile_code = (int)((Math.random()*9+1)*100000);
        String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");
        MessageServiceFactory.getMobileMessageService().send("fuzhu",
                "13631476062", content);


        /*
            方式二是一键完成短信功能的展示而已
         */
//        HttpClient client = new HttpClient();
//        PostMethod method = new PostMethod(Url);
//
//        client.getParams().setContentCharset("GBK");
//        method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=GBK");
//
//        int mobile_code = (int)((Math.random()*9+1)*100000);
//
//        String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");
//
//        NameValuePair[] data = {//提交短信
//                new NameValuePair("account", ""),
//                new NameValuePair("password", ""), //查看密码请登录用户中心->验证码、通知短信->帐户及签名设置->APIKEY
//                //new NameValuePair("password", util.StringUtil.MD5Encode("密码")),
//                new NameValuePair("mobile", ""),
//                new NameValuePair("content", content),
//        };
//        method.setRequestBody(data);
//
//        try {
//            client.executeMethod(method);//发送短信
//
//        Header[] headers = method.getResponseHeaders();//短信返回信息
//        int statusCode = method.getStatusCode();
//        System.out.println("statusCode:" + statusCode);
//        for (Header h : headers) {
//            System.out.println(h.toString());
//        }
//        String result = null;
//
//            result = new String(method.getResponseBodyAsString().getBytes(
//                    "gbk"));
//
//        System.out.println(result);
//        method.releaseConnection();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

    }

}
