package top.dwy.week1.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration; // 关键：Bean 注解

/**
 * @author dwy
 */ // 1. Bean 注解：标记为配置类，Spring 会自动注册为 Bean
@Configuration
// 2. （可选）从配置文件读取属性，前缀与 application.properties 对应
@ConfigurationProperties(prefix = "dwy.sms.ccp")
// 3. Lombok 注解：自动生成 getter/setter（若不用 Lombok，需手动写）
@Data
public class CloopenConfig {
    // 成员变量（与配置文件中的属性名对应，如 cloopen.server-ip）
    private String serverIp;
    private String port;
    private String accountSid;
    private String accountToken;
    private String appId;
    private String templateId;
}