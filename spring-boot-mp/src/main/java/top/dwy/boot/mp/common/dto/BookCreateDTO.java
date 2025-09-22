package top.dwy.boot.mp.common.dto;

import lombok.Data;

import jakarta.validation.constraints.*;

/**
 * @author alani
 */
@Data
public class BookCreateDTO {
    @NotBlank(message = "书名不能为空")
    private String title;
    private String author;
    @NotBlank(message = "ISBN不能为空")
    private String isbn;
    private String category;
    @NotNull(message = "库存不能为空")
    private Integer stock;
}