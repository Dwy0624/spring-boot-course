package top.dwy.boot.websocket.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 自定义消息模型（天气、一言等）
 * @author alani
 */
@Data
public class CustomMessage {
    private static final Random RANDOM = new Random();
    private static final List<String> WEATHER_LIST = new ArrayList<>();
    private static final List<String> HITOKOTO_LIST = new ArrayList<>();

    // 静态初始化模拟数据
    static {
        // 天气数据
        WEATHER_LIST.add("晴，25℃，微风");
        WEATHER_LIST.add("多云，22℃，东北风3级");
        WEATHER_LIST.add("小雨，18℃，东南风2级");
        WEATHER_LIST.add("阴，20℃，西北风1级");
        WEATHER_LIST.add("雷阵雨，26℃，西南风4级");

        // 随机一言
        HITOKOTO_LIST.add("生命不是一场赛跑，而是一次旅行。");
        HITOKOTO_LIST.add("今天的你，比昨天更优秀了吗？");
        HITOKOTO_LIST.add("保持热爱，奔赴山海。");
        HITOKOTO_LIST.add("每一个不曾起舞的日子，都是对生命的辜负。");
        HITOKOTO_LIST.add("努力不一定成功，但放弃一定失败。");
        HITOKOTO_LIST.add("人生没有白走的路，每一步都算数。");
    }

    // 随机获取天气信息
    public static String getRandomWeather() {
        return "当前天气：" + WEATHER_LIST.get(RANDOM.nextInt(WEATHER_LIST.size()));
    }

    // 随机获取一言
    public static String getRandomHitokoto() {
        return "一言：" + HITOKOTO_LIST.get(RANDOM.nextInt(HITOKOTO_LIST.size()));
    }
}