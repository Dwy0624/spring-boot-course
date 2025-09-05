package top.dwy.week1.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author dwy
 */
@RestController
public class HelloController {
    @GetMapping("/strings")
    public List<String> strings() {
        return List.of("Hello","World!");
    }

    @Value("${my.feature.helloSwitch}")
    private boolean isHelloEnabled;
    @Value("${my.feature.closeMsg}")
    private String closeMessage;

    @GetMapping("/hello")
    public String hello() {
        if (isHelloEnabled) {
            return "接口开放中！ 欢迎访问我得第一个Spring Boot 项目";
        }else {
            return closeMessage;
        }
    }
}
