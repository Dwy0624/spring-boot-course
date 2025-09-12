package top.dwy.service;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.dwy.model.Mail;
@SpringBootTest
class MailServiceTest {
    @Resource
    private MailService mailService;
    @Test
    void sendSimpleMail() {
        Mail mail=new Mail();
        mail.setTo("664903978@qq.com");
        mail.setSubject("测试邮件");
        mail.setContent("测试邮件内容");
        mailService.sendSimpleMail(mail);
    }
    @Test
    void sendHtmlMail() {
        Mail mail=new Mail();
        mail.setTo("664903978@qq.com");
        mail.setSubject("测试邮件");
        mail.setContent("<html><body><h1>测试邮件内容</h1></body></html>");
        mailService.sendHtmlMail(mail);
    }
}