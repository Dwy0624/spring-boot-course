package top.dwy.boot.filter_interceptor.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author alani
 */
@Data
public class LoginRequest {
    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;
}