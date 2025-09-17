package top.dwy.boot.mp.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.dwy.boot.mp.Mapper.UserAccountMapper;
import top.dwy.boot.mp.entity.UserAccount;

import java.util.List;


/**
 * @author dwy
 */
@RestController
@RequestMapping("/user-account") // 接口统一前缀
public class UserAccountController {
    @Resource
    private UserAccountMapper userAccountMapper;

    // 1. 查：根据ID查询单个用户
    @GetMapping("/{id}") // GET请求，路径参数传ID
    public UserAccount getUserById(@PathVariable Long id) {
        // MP内置方法：selectById（根据主键查询）
        return userAccountMapper.selectById(id);
    }

    // 2. 查：查询所有用户（支持逻辑删除过滤，若配置了逻辑删除）
    @GetMapping("/list") // GET请求，无参数
    public List<UserAccount> getUserList() {
        // MP内置方法：selectList（条件查询，null表示查所有）
        return userAccountMapper.selectList(null);
    }

    // 3. 增：新增用户（修复原代码的请求方式错误）
    @PostMapping("/add") // 新增必须用POST请求（原代码用GET会丢失请求体）
    public String addUser(@RequestBody UserAccount userAccount) {
        // MP内置方法：insert（插入一条记录）
        int result = userAccountMapper.insert(userAccount);
        // 根据插入结果返回提示（result=1表示成功，0表示失败）
        return result > 0 ? "用户新增成功" : "用户新增失败";
    }

    // 4. 改：根据ID修改用户（全字段修改/部分字段修改）
    @PutMapping("/update") // 修改推荐用PUT请求（语义：更新资源）
    public String updateUser(@RequestBody UserAccount userAccount) {
        // 关键：MP的updateById需要传入主键ID（否则无法定位要修改的记录）
        if (userAccount.getId() == null) {
            return "修改失败：用户ID不能为空";
        }
        // MP内置方法：updateById（根据主键更新记录）
        int result = userAccountMapper.updateById(userAccount);
        return result > 0 ? "用户修改成功" : "用户修改失败（可能用户不存在）";
    }

    // 5. 删：根据ID删除用户（逻辑删除，符合表结构中的deleted字段）
    @DeleteMapping("/delete/{id}") // 删除推荐用DELETE请求（语义：删除资源）
    public String deleteUser(@PathVariable Long id) {
        // 先判断用户是否存在（避免删除不存在的记录）
        UserAccount existUser = userAccountMapper.selectById(id);
        if (existUser == null) {
            return "删除失败：ID为" + id + "的用户不存在";
        }
        // MP内置方法：deleteById（根据主键删除记录，若配置逻辑删除则自动更新deleted=1）
        int result = userAccountMapper.deleteById(id);
        return result > 0 ? "用户删除成功" : "用户删除失败";
    }
}