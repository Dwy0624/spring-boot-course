package top.dwy.boot.mp.common.result;

import lombok.Data;

import java.util.List;

/**
 * @author alani
 */
@Data
public class PageResult<T> {
    private Long total;
    private List<T> records;
    private Integer pageNum;
    private Integer pageSize;

    public PageResult(Long total, List<T> records, Integer pageNum, Integer pageSize) {
        this.total = total;
        this.records = records;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
}