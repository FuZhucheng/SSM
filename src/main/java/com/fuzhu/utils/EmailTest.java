package com.fuzhu.utils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by asus on 2017/6/27.
 */
public class EmailTest {
    //创建一个线程池，可装载大概3个线程任务的
    private static ExecutorService executorService = Executors.newFixedThreadPool(2);

    // 收件人邮箱（替换为自己知道的有效邮箱）1433317518
    public static String receiveMailAccount = "751197996@qq.com";

    public static void main(String[] args)  {
        //这种写法详情请去参考lambda表达式
//        executorService.submit(() -> EmailServiceFactory.getEmailService().sendEmail(receiveMailAccount, "BSS系统工单", "<body><p>工单工号GGGGDADA</p></body>"));
        executorService.submit(new Runnable() {
            public void run() {
                EmailServiceFactory.getEmailService().sendEmail(receiveMailAccount, "BSS系统工单", "<body><p>工单工号GGGGDADA</p></body>");
            }
        });
        executorService.submit(new Runnable() {
            public void run() {
                EmailServiceFactory.getEmailService().sendEmail(receiveMailAccount, "BSS系统工单", "<body><p>工单工号GGGGDADA</p></body>");
            }
        });
        executorService.submit(new Runnable() {
            public void run() {
                EmailServiceFactory.getEmailService().sendEmail(receiveMailAccount, "BSS系统工单", "<body><p>工单工号GGGGDADA</p></body>");
            }
        });
        executorService.submit(new Runnable() {
            public void run() {
                EmailServiceFactory.getEmailService().sendEmail(receiveMailAccount, "BSS系统工单", "<body><p>工单工号GGGGDADA</p></body>");
            }
        });
        executorService.submit(new Runnable() {
            public void run() {
                EmailServiceFactory.getEmailService().sendEmail(receiveMailAccount, "BSS系统工单", "<body><p>工单工号GGGGDADA</p></body>");
            }
        });
        executorService.submit(new Runnable() {
            public void run() {
                EmailServiceFactory.getEmailService().sendEmail(receiveMailAccount, "BSS系统工单", "<body><p>工单工号GGGGDADA</p></body>");
            }
        });
        //调用 shutdown() 方法，ExecutorService 并不会马上关闭，而是不再接收新的任务，一但所有的线程结束执行当前任务，ExecutorServie 才会真的关闭。所有在调用 shutdown() 方法之前提交到 ExecutorService 的任务都会执行。
        //调用时机由你自己去决定
        //你希望立即关闭 ExecutorService，你可以调用 shutdownNow() 方法。这個方法会尝试马上关闭所有正在执行的任务，并且跳过所有已经提交但是还没有运行的任务。但是对于正在执行的任务，是否能够成功关闭它是无法保证的，有可能他们真的被关闭掉了，也有可能它会一直执行到任务结束。这是一個最好的尝试。
        executorService.shutdown();
    }

}
