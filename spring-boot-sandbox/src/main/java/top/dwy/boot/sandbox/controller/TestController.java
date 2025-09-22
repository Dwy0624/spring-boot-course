package top.dwy.boot.sandbox.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author alani
 */
@RestController
public class TestController {
    @GetMapping()
    public String index() {
        return "hello world";
    }
}