package top.dwy.boot.redis.service;

import top.dwy.boot.redis.entity.LoginRequest;
import top.dwy.boot.redis.entity.LoginResponse;

/**
 * @author alani
 */

public interface LoginService {
    LoginResponse login(LoginRequest loginRequest);
}