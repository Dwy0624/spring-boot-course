package top.dwy.boot.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.dwy.boot.mp.entity.Book;

/**
 * @author alani
 */
@Mapper
public interface BookMapper extends BaseMapper<Book> {
    Book selectByIdForUpdate(Long id);
}