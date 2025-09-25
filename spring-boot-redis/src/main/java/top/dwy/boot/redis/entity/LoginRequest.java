package top.dwy.boot.redis.entity;

import lombok.Data;

/**
 * @author alani
 */

@Data
public class LoginRequest {
    private String phone;
    private String code;
}