package top.dwy.boot.redis.service.impl;

import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.dwy.boot.redis.cache.RedisCache;
import top.dwy.boot.redis.cache.RedisKeys;
import top.dwy.boot.redis.config.CloopenConfig;
import top.dwy.boot.redis.enums.ErrorCode;
import top.dwy.boot.redis.exception.ServerException;
import top.dwy.boot.redis.service.SmsService;
import top.dwy.boot.redis.utils.CommonUtils;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;
/**
 * @author alani
 */
@Slf4j
@Service
@AllArgsConstructor
public class SmsServiceImpl implements SmsService {
    @Resource
    private final CloopenConfig cloopenConfig;
    private final RedisCache redisCache;

    @Override
    public void sendSms(String phone) {
        if (!CommonUtils.checkPhone( phone)){
            throw new ServerException(ErrorCode.PARAM_ERROR);
        }

        int code = CommonUtils.generateCode();
        redisCache.set(RedisKeys.getSmsKey(phone),code,60);
        log.info("发送短信验证码：{}", code);

        String serverIp = cloopenConfig.getServerIp();
        String serverPort = cloopenConfig.getPort();
        String accountSid = cloopenConfig.getAccountSid();
        String accountToken = cloopenConfig.getAccountToken();
        String appId = cloopenConfig.getAppId();
        String templateId = cloopenConfig.getTemplateId();

        // 修正SDK初始化
        CCPRestSmsSDK sdk = new CCPRestSmsSDK();
        sdk.init(serverIp, serverPort);
        sdk.setAccount(accountSid, accountToken);
        sdk.setAppId(appId);
        sdk.setBodyType(BodyType.Type_JSON);

        String[] datas = {String.valueOf(code), "1"};
        HashMap<String, Object> result = sdk.sendTemplateSMS(
                phone,
                templateId,
                datas,
                "1234",
                UUID.randomUUID().toString()
        );

        // 修正状态码判断（通常成功码是"000000"）
        if ("000000".equals(result.get("statusCode"))) {
            @SuppressWarnings("unchecked")
            HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            for (String key : keySet) {
                Object object = data.get(key);
                log.info("{} = {}", key, object);
            }
        } else {
            // 修正日志输出语法
            log.error("错误码={}, 错误信息= {}",
                    result.get("statusCode"),
                    result.get("statusMsg"));
        }
    }
}