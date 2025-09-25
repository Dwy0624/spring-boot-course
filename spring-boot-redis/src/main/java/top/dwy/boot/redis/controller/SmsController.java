package top.dwy.boot.redis.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.dwy.boot.redis.result.Result;
import top.dwy.boot.redis.service.SmsService;

/**
 * @author dwy
 */

@RestController
public class SmsController {
    @Resource
    private SmsService smsService;

    @GetMapping("/sms/send")
    public Result<Boolean> sendSms(@RequestParam String phone){
        return Result.ok(smsService.sendSms(phone));
    }

}