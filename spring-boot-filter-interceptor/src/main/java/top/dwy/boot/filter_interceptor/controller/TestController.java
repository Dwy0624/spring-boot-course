package top.dwy.boot.filter_interceptor.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.dwy.boot.filter_interceptor.result.Result;

/**
 * @author alani
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class TestController {

    @GetMapping("/test/filter")
    public Result<String> testFilter(@RequestParam String name) {
        return Result.ok("Hello, " + name);
    }

    @GetMapping("/pay/{id}")
    public Result<String> pay(@PathVariable long id) {
        log.info("开始支付");
        return Result.ok("支付成功，订单号：" + id);
    }
    @GetMapping("/test/cors")
    public Result<String> testCors() {
        return Result.ok("跨域请求测试通过，当前时间：" + System.currentTimeMillis());
    }

    /*
    @GetMapping("/test")
    public String get() {
        log.info("进入 Controller");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "hello world";
    }

    @GetMapping("/pay/{id}")
    public String pay(@PathVariable int id){
        log.info("进入支付环节,id:{}", id);
        return "订单号为"+"支付成功";
    }

    @GetMapping("/order")
    public  String order(@RequestParam String username ){
        log.info("进入用户{}的订单环节",username);
        return "用户："+username+"的订单生成成功";
    }

     */
}
