package top.dwy.boot.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.dwy.boot.mp.entity.UserAccount;
/**
 * @author dwy
 */
@Mapper
public interface UserAccountMapper extends BaseMapper<UserAccount> {
    // 继承 BaseMapper 获得基础 CRUD 能⼒
    // 可以在此添加⾃定义查询⽅法
}