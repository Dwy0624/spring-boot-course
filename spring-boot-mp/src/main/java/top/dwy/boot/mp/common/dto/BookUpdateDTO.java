package top.dwy.boot.mp.common.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * @author alani
 */
@Data
public class BookUpdateDTO {
    @NotBlank(message = "书名不能为空")
    private String title;
    private String author;
    private String category;
    @NotBlank(message = "ISBN不能为空")
    private String isbn;
}