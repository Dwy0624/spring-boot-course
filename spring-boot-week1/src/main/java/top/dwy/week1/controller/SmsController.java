package top.dwy.week1.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.dwy.week1.service.SmsService;

/**
 * @author dwy
 */
@RestController
public class SmsController {
    @Resource
    private SmsService smsService;

    @GetMapping("/sms")
    public void sendSms(String phone){
        smsService.sendSms(phone);
    }
}
