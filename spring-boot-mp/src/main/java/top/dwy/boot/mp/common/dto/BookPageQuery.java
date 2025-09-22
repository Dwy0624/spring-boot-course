package top.dwy.boot.mp.common.dto;

import lombok.Data;

/**
 * @author alani
 */
@Data
public class BookPageQuery {
    private Integer pageNum = 1;
    private Integer pageSize = 10;
    private String title;
    private String author;
    private String category;
}