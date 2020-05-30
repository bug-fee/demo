import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class TestEmail {
    @Test
    public void testEmail() throws MessagingException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 拿到邮件发送者对象,就是放到spring中的bean
        JavaMailSender javaMailSender = (JavaMailSender) context.getBean("javaMailSenderImpl");
        System.out.println("=========="+javaMailSender);
        // 创一个邮件对象
        MimeMessage message = javaMailSender.createMimeMessage();
        // 新建一个邮件助手
        // MimeMessageHelper messageHelper = new MimeMessageHelper(message, "UTF-8");
        // 第二个参数设置是否为附件邮件
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, false, "UTF-8");
        // 设置发送者（这和配置文件写的邮箱要一直）
        messageHelper.setFrom("779010269@qq.com");
        // 设置目的地（这些接受者邮箱）
        messageHelper.setTo("308943733@qq.com");
        // Carbon Copy(抄送)
        //messageHelper.setCc("zhangzixiongc501@sina.com");
        // Blind Carbon Copy(暗抄送)。
        //messageHelper.setBcc("zhangzixiongc501@sina.com");
        // 邮件内容
        // 第二个参数为true时。内容是html
        String html = "<!DOCTYPE html>\r\n" + "<html>\r\n" + "\r\n" + "    <head>\r\n" + "       <meta charset=\"utf-8\" />\r\n" + "       <title></title>\r\n" + "   </head>\r\n" + "   <body>\r\n" + "    <h1 style='color:red;'>我是网页！！！！</h1> "+"<p>这是在测试Spring框架整合java Mail</P>" + "   </body>\r\n" + "\r\n" + "</html>";
        messageHelper.setText(html, true);
        // 邮件主题
        messageHelper.setSubject("you are skin!");
        //添加附件
        //messageHelper.addAttachment("附件1.jsp",new File("C:\\Users\\chair\\Desktop\\info.jsp"));
        javaMailSender.send(message);
    }
}