package top.dwy.boot.mp.common.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author alani
 */
@Data
public class BookVO {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private String category;
    private Integer stock;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}