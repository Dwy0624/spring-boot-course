package top.dwy.service;

import top.dwy.enums.ResultStatus;
import top.dwy.model.Mail;

public interface MailService {

    ResultStatus sendSimpleMail(Mail mail);
    ResultStatus sendHtmlMail(Mail mail);
}