package top.dwy.boot.websocket.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * 设备监控数据模型
 * @author alani
 */
@Data
public class DeviceMonitorData {
    private static final Random RANDOM = new Random();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // 设备基本信息
    private String deviceId;
    private String deviceName;
    private String timestamp;

    // 监控指标
    private double cpuUsage;
    // CPU使用率(%)
    private double memoryUsage;
    // 内存使用率(%)
    private double diskUsage;
    // 磁盘使用率(%)
    private int networkSpeed;
    // 网络速度(kb/s)

    // 随机生成模拟数据
    public static DeviceMonitorData generateRandomData() {
        DeviceMonitorData data = new DeviceMonitorData();
        data.setDeviceId("device-" + (RANDOM.nextInt(1000) + 100));
        data.setDeviceName("服务器-" + (RANDOM.nextInt(10) + 1));
        data.setTimestamp(LocalDateTime.now().format(FORMATTER));

        // 生成合理范围内的随机监控数据
        data.setCpuUsage(round2(RANDOM.nextDouble() * 80 + 5));
        // 5%-85%
        data.setMemoryUsage(round2(RANDOM.nextDouble() * 70 + 10));
        // 10%-80%
        data.setDiskUsage(round2(RANDOM.nextDouble() * 50 + 20));
        // 20%-70%
        data.setNetworkSpeed(RANDOM.nextInt(1000) + 100);
        // 100-1100kb/s

        return data;
    }

    // 保留两位小数
    private static double round2(double value) {
        return Math.round(value * 100) / 100.0;
    }
}