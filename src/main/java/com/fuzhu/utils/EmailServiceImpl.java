package com.fuzhu.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class EmailServiceImpl implements EmailService {
    private static final Log log = LogFactory.getLog(EmailServiceImpl.class);

    // 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
    // 对于开启了独立密码的邮箱, 这里的邮箱密码必需使用这个独立密码（授权码）。
    public static String myEmailAccount = "";
    public static String myEmailPassword = "";
    public static String myEmailName = "";
    // qq邮箱的SMTP服务器地址：smtp.qq.com
    public static String myEmailSMTPHost = "smtp.126.com";

    static {
        //博主自己封装了一个获取本地文件的配置参数方式，大家可以参考使用。针对config.properties的，想改别的文件请大家自行修改。
        try {
            myEmailAccount = Config.getConfigValue("SENDER_MAILBOX");
            myEmailPassword = Config.getConfigValue("MAIL_PASSWPRD");
            myEmailName = Config.getConfigValue("MAIL_NAME");
            System.out.println(myEmailAccount);
            System.out.println(myEmailPassword);
            System.out.println(myEmailName);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void sendEmail(String hisEmail, String subject, String content) {
        System.out.println("myEmailAccount  :" + myEmailAccount);
        System.out.println("myEmailPassword :" + myEmailPassword);
        try {
            // 1. 创建参数配置, 用于连接邮件服务器的参数配置
            final Properties props = new Properties(); // 参数配置
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", "smtp.126.com");

            // 发件人的账号
            props.put("mail.user", myEmailAccount);
            // 发件人的密码
            props.put("mail.password", myEmailPassword);
            //网易邮箱必须这样，使用Authenticator，进行一系列的验证。不然就是给你504，验证失败或者辣鸡邮件发不出去
            Authenticator authenticator = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            };

            // 使用环境属性和授权信息，创建邮件会话
            Session mailSession = Session.getInstance(props, authenticator);
            // 创建邮件消息
            MimeMessage message = new MimeMessage(mailSession);
            // 设置发件人
            String username = props.getProperty("mail.user");
            InternetAddress form = new InternetAddress(username);
            message.setFrom(form);

            // 设置收件人
            InternetAddress to = new InternetAddress(hisEmail);
            message.setRecipient(RecipientType.TO, to);

            // 设置邮件标题
            message.setSubject(subject);

            // 设置邮件的内容体
            message.setContent(content, "text/html;charset=UTF-8");
            // 发送邮件
            Transport.send(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
