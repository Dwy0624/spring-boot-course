package top.dwy.model;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@Component // 注入 1.添加依赖 2.相应的校验注解 3.value注解
public class Team {
    @Value("${team.name}")
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 3, max = 20,message = "团队名称长度必须在3-20之间")
    private String name;

    @Value("${team.leader}")
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 3, max = 8,message = "团队负责人长度必须在3-8之间")
    private String leader;

    @Value("${team.age}")
    @Min(1)
    @Max(4)
    private Integer age;

    @Value("${team.phone}")
    @Pattern(regexp = "^[0-9]{11}$",message = "手机号格式不正确") //正则表达式 由0-9组成的11位字符串
    private String phone;

    @Past
    private LocalDate createTime;
}
