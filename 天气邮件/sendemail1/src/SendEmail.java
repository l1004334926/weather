// 需要用户名密码邮件发送实例
//文件名 SendEmail2.java
//本实例以QQ邮箱为例，你需要在qq后台设置

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;



public class SendEmail
{
    public static void main(String [] args)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        // 收件人电子邮箱邮件发给王益庆<wangyq@yuanian.com>，抄送邱光成<qiugch@yuanian.com>，王明辉<wangmh@yuanian.com>
        String to = "liuch@yuanian.com";

        // 发件人电子邮箱
        String from = "1004334926@qq.com";

        // 指定发送邮件的主机为 smtp.qq.com
        String host = "smtp.qq.com";  //QQ 邮件服务器

        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);

        properties.put("mail.smtp.auth", "true");
        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties,new Authenticator(){
            public PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication("1004334926@qq.com", "sivwwkurmmjebfdi"); //发件人邮件用户名、授权码
            }
        });

        try{
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: 头部头字段
            message.setSubject("春蕾学员培训-天气预报  刘翀  "+df.format(new Date()));// new Date()为获取当前系统时间

            // 设置消息体
            String cityCode = "101200101";
            List<Weather> list1 = WeatherUtil.getURLInfo("http://www.weather.com.cn/weather/" + cityCode + ".shtml");
            message.setText("武汉"+list1.toString());
            // 发送消息
            Transport.send(message);
            System.out.println("Sent message successfully....from runoob.com");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}