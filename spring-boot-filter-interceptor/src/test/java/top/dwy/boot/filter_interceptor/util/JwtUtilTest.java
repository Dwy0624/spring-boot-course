package top.dwy.boot.filter_interceptor.util;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class JwtUtilTest {
    @Resource
    private JwtUtil jwtUtil;
    @Test
    void generateToken() {
        String token = jwtUtil.generateToken("1L","alani");
        log.info("token:{}",token);
        boolean b=jwtUtil.validateToken( token);
        log.info("验证结果：{}",b);
        assertTrue(b);
    }
}