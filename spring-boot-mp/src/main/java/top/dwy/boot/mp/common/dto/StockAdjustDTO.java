package top.dwy.boot.mp.common.dto;

import lombok.Data;

import jakarta.validation.constraints.*;

/**
 * @author alani
 */
@Data
public class StockAdjustDTO {
    @NotNull(message = "调整数量不能为空")
    private Integer amount;
    @NotNull(message = "调整类型不能为空（1: 入库，2: 出库）")
    private Integer type;
}