package top.dwy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.dwy.enums.DrinkType;

/**
 * @author alani
 */
@RestController
@RequestMapping
public class DrinkTypeController {
    @GetMapping("/drink{type}")
    public String getDrinkType(@PathVariable DrinkType type) {
        return "您选择的是："+ type.getLabel()+"价格为"+ type.getPrice();
    }
}
