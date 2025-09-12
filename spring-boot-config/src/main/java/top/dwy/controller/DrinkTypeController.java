package top.dwy.controller;

import org.springframework.web.bind.annotation.*;
import top.dwy.common.ApiResponse;
import top.dwy.enums.DrinkType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author alani
 */
@RestController
@RequestMapping("/drink")
public class DrinkTypeController {
    @GetMapping("/{type}")
    public ApiResponse<Map<String,Object>> chooseDrink(@PathVariable String type){
        try {
            DrinkType drink= DrinkType.valueOf(type.toUpperCase());
            Map<String,Object> result=new HashMap<>();
            result.put("name",drink.getLabel());
            result.put("price",drink.getPrice());
            return ApiResponse.success("选择成功",result);
        }catch (IllegalArgumentException e){
            return ApiResponse.error("饮料类型无效");
        }
    }
    @GetMapping("/menu")
    public ApiResponse<List<Map<String,Object>>> getMenu() {
        List<Map<String, Object>> menu = new ArrayList<>();
        for (DrinkType drink : DrinkType.values()) {
            Map<String, Object> item = new HashMap<>();
            item.put("name", drink.getLabel());
            item.put("price", drink.getPrice());
            menu.add(item);
        }
        return ApiResponse.success("获取菜单成功", menu);
    }
    @GetMapping("/order")
    public ApiResponse<Map<String,Object>> orderDrink(@RequestParam String type, @RequestParam int num){
        try{
            DrinkType drink=DrinkType.valueOf(type.toUpperCase());
            double total=drink.getPrice()*num;
            Map<String,Object> result=new HashMap<>();
            result.put("name",drink.getLabel());
            result.put("num",num);result.put("unitPrice",drink.getPrice());
            result.put("totalPrice",total);
            return ApiResponse.success("下单成功",result);
        }catch(IllegalArgumentException e){
            return ApiResponse.error("饮料类型无效");
        }
    }
}
