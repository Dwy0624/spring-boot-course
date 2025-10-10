package top.dwy.boot.websocket.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import top.dwy.boot.websocket.model.CustomMessage;
import top.dwy.boot.websocket.model.DeviceMonitorData;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 增强版WebSocket处理器：支持天气、一言和设备监控数据推送
 * @author alani
 */
@Slf4j
@Component
public class SimpleTimeWebSocketHandler implements WebSocketHandler {
    // 使用线程安全的ConcurrentHashMap存储WebSocket会话
    private static final Map<String, WebSocketSession> SESSIONS = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        SESSIONS.put(session.getId(), session);
        log.info("新的WebSocket连接建立，会话ID: {}, 当前连接数: {}", session.getId(), SESSIONS.size());
        // 连接建立后发送欢迎消息
        String welcomeMessage = "🎉 欢迎连接信息推送服务！\n" +
                "将为您推送：天气信息、随机一言和设备监控数据";
        sendMsg(session, welcomeMessage);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String payload = message.getPayload().toString();
        log.info("收到客户端消息: {}, 会话ID: {}", payload, session.getId());
        if ("ping".equalsIgnoreCase(payload.trim())) {
            sendMsg(session, "pong");
        } else if ("status".equalsIgnoreCase(payload.trim())) {
            // 客户端请求当前设备状态
            DeviceMonitorData data = DeviceMonitorData.generateRandomData();
            sendMsg(session, formatDeviceData(data));
        } else {
            String response = "收到消息: " + payload + "\n发送 'ping' 测试连接，发送 'status' 获取设备状态";
            sendMsg(session, response);
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error("WebSocket传输错误，会话ID: {}", session.getId(), exception);
        SESSIONS.remove(session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        SESSIONS.remove(session.getId());
        log.info("WebSocket连接关闭，会话ID: {}, 关闭状态: {}, 当前连接数: {}",
                session.getId(), closeStatus, SESSIONS.size());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 定时任务1：每3秒推送天气或一言（随机切换）
     */
    @Scheduled(fixedRate = 3000)
    public void sendWeatherOrHitokoto() {
        if (SESSIONS.isEmpty()) return;

        String message;
        // 50%概率推送天气，50%概率推送一言
        if (Math.random() > 0.5) {
            message = CustomMessage.getRandomWeather();
        } else {
            message = CustomMessage.getRandomHitokoto();
        }

        broadcastMessage(message);
    }

    /**
     * 定时任务2：每10秒推送设备监控数据
     */
    @Scheduled(fixedRate = 10000)
    public void sendDeviceMonitorData() {
        if (SESSIONS.isEmpty()) {
            log.debug("当前没有活跃的WebSocket连接");
            return;
        }

        log.info("开始执行设备监控数据推送，当前连接数: {}", SESSIONS.size());
        DeviceMonitorData data = DeviceMonitorData.generateRandomData();
        String message = formatDeviceData(data);
        broadcastMessage(message);
    }

    /**
     * 格式化设备监控数据为字符串
     */
    private String formatDeviceData(DeviceMonitorData data) {
        return String.format("📊 设备监控 [%s]\n" +
                        "设备ID: %s\n" +
                        "CPU使用率: %.2f%%\n" +
                        "内存使用率: %.2f%%\n" +
                        "磁盘使用率: %.2f%%\n" +
                        "网络速度: %dkb/s",
                data.getTimestamp(),
                data.getDeviceId(),
                data.getCpuUsage(),
                data.getMemoryUsage(),
                data.getDiskUsage(),
                data.getNetworkSpeed());
    }

    /**
     * 向所有连接的客户端广播消息
     */
    private void broadcastMessage(String message) {
        SESSIONS.values().removeIf(session -> {
            try {
                if (session.isOpen()) {
                    sendMsg(session, message);
                    return false;
                } else {
                    log.warn("发现已关闭的会话，将其移除: {}", session.getId());
                    return true;
                }
            } catch (Exception e) {
                log.error("发送消息失败，移除会话: {}", session.getId(), e);
                return true;
            }
        });
    }

    /**
     * 发送消息到指定会话
     */
    private void sendMsg(WebSocketSession session, String message) {
        try {
            if (session.isOpen()) {
                TextMessage textMessage = new TextMessage(message);
                session.sendMessage(textMessage);
                log.debug("消息发送成功，会话ID: {}", session.getId());
            }
        } catch (Exception e) {
            log.error("发送消息失败，会话ID: {}", session.getId(), e);
        }
    }
}