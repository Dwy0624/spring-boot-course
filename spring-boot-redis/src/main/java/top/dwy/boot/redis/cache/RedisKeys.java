package top.dwy.boot.redis.cache;

/**
 * @author alani
 */

public class RedisKeys {
    /**
     * 验证码 Key
     */
    public static String getSmsKey(String phone) {
        return "sms:captcha:" + phone;
    }
}