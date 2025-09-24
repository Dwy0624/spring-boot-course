package top.dwy.boot.redis.entity;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class StudentTest {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void testStudent() {
        Address address = Address.builder()
                .city("上海")
                .province("上海")
                .build();
        Student student = Student.builder()
                .name("dwy")
                .address(address)
                .build();
        redisTemplate.opsForValue().set("student", student,30, TimeUnit.SECONDS);
    }
}