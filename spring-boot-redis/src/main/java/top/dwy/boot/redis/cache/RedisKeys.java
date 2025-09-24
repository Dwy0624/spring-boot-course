package top.dwy.boot.redis.cache;

/**
 * @author alani
 */
public class RedisKeys {
    public static String getSmsKey(String phone){
        return "sms:captcha" + phone;
    }
}
