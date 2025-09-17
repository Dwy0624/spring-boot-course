package top.dwy.boot.mp.Mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import top.dwy.boot.mp.entity.UserAccount;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserAccountMapperTest {
    @Resource
    private UserAccountMapper userAccountMapper;

    @Test
    void testUserAccount() {
        UserAccount userAccount = userAccountMapper.selectById(1);
        assertNotNull(userAccount);
        assertEquals("admin", userAccount.getUsername());
    }

    @Test
    void testSelectCount() {
        QueryWrapper<UserAccount> wrapper = new QueryWrapper<>();
        Long count = userAccountMapper.selectCount(wrapper);
        // 建议改为动态获取或使用假设数据
        assertEquals(50L, count); // 若数据可能变化，应使用更灵活的断言方式
    }

    @Test
    @Transactional
    void testInsert() {
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername("dwy");
        userAccount.setNickname("dwy");
        userAccount.setEmail("1554642647@qq.com");
        userAccount.setPhone("1554642647");
        userAccount.setStatus(true);
        userAccount.setDeleted(false);
        userAccount.setCreateTime(LocalDateTime.now());
        userAccount.setUpdateTime(LocalDateTime.now());
        int row = userAccountMapper.insert(userAccount);
        assertEquals(1, row);
    }


    @Test
    void updateTest(){
        UserAccount userAccount = userAccountMapper.selectById(42);
        userAccount.setNickname("admin");
        int row = userAccountMapper.updateById(userAccount);
        assertEquals(1,row);
    }
}
