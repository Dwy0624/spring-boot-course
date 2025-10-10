package top.dwy.boot.websocket;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author alani
 */
@SpringBootApplication
@EnableScheduling // 启用定时任务
public class WebSocketApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(WebSocketApplication.class, args);
    }
}