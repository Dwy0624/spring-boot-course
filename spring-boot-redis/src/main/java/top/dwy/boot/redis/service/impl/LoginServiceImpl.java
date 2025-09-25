package top.dwy.boot.redis.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.dwy.boot.redis.cache.RedisCache;
import top.dwy.boot.redis.cache.RedisKeys;
import top.dwy.boot.redis.entity.LoginRequest;
import top.dwy.boot.redis.entity.LoginResponse;
import top.dwy.boot.redis.exception.ServerException;
import top.dwy.boot.redis.service.LoginService;
import top.dwy.boot.redis.utils.CommonUtils;
import top.dwy.boot.redis.enums.ErrorCode;

import java.util.UUID;

/**
 * @author alani
 */

@Slf4j
@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final RedisCache redisCache;
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        String phone = loginRequest.getPhone();
        String inputCode = loginRequest.getCode();

        // 1. 校验手机号格式
        if (!CommonUtils.checkPhone(phone)) {
            throw new ServerException(ErrorCode.PARAM_ERROR);
        }

        // 2. 校验验证码是否为空
        if (inputCode == null || inputCode.trim().isEmpty()) {
            throw new ServerException("验证码不能为空");
        }

        // 3. 从 Redis 获取验证码（关键调整）
        String redisKey = RedisKeys.getSmsKey(phone);
        Object redisValue = redisCache.get(redisKey); // 先获取 Object 类型

        // 4. 检查 Redis 中是否存在验证码（先判断 null，再转字符串）
        if (redisValue == null) {
            throw new ServerException("验证码已过期或不存在");
        }
        String redisCode = redisValue.toString(); // 确认非 null 后再转字符串

        // 5. 校验验证码是否正确
        if (!inputCode.equals(redisCode)) {
            throw new ServerException("验证码错误");
        }

        // 6. 验证通过，删除 Redis 中的验证码（防止重复使用）
        redisCache.delete(redisKey);

        // 7. 生成 token 并返回
        String token = generateToken(phone);
        log.info("用户{}登陆成功", phone);
        return new LoginResponse(token, phone);
    }
    private String generateToken(String phone){
        return UUID.randomUUID().toString().replace("-","")+phone.hashCode();
    }
}