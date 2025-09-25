package top.dwy.boot.redis.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.dwy.boot.redis.entity.LoginRequest;
import top.dwy.boot.redis.entity.LoginResponse;
import top.dwy.boot.redis.result.Result;
import top.dwy.boot.redis.service.LoginService;

/**
 * @author alani
 */

@RestController
@AllArgsConstructor
public class LoginController {
    private final LoginService loginService;
    @PostMapping("/login")
    public Result<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse=loginService.login(loginRequest);
        return Result.ok(loginResponse);
    }
}