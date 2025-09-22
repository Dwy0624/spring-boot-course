package top.dwy.boot.mp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.dwy.boot.mp.common.dto.BookCreateDTO;
import top.dwy.boot.mp.common.dto.BookPageQuery;
import top.dwy.boot.mp.common.dto.BookUpdateDTO;
import top.dwy.boot.mp.common.dto.StockAdjustDTO;
import top.dwy.boot.mp.common.result.PageResult;
import top.dwy.boot.mp.common.vo.BookVO;
import top.dwy.boot.mp.entity.Book;

/**
 * @author alani
 */
public interface BookService extends IService<Book> {
    BookVO createBook(BookCreateDTO dto);
    BookVO updateBook(Long id, BookUpdateDTO dto);
    BookVO adjustStock(Long id, StockAdjustDTO dto);
    BookVO getBookById(Long id);
    boolean checkIsbnExists(String isbn);
    PageResult<BookVO> pageQuery(BookPageQuery query);
    boolean deleteBook(Long id);
    BookVO restoreBook(Long id);
}