package top.dwy.boot.mp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.dwy.boot.exception.exception.BusinessException;
import top.dwy.boot.mp.common.dto.BookCreateDTO;
import top.dwy.boot.mp.common.dto.BookPageQuery;
import top.dwy.boot.mp.common.dto.BookUpdateDTO;
import top.dwy.boot.mp.common.dto.StockAdjustDTO;
import top.dwy.boot.mp.common.result.PageResult;
import top.dwy.boot.mp.common.vo.BookVO;
import top.dwy.boot.mp.entity.Book;
import top.dwy.boot.mp.exception.BookNotFoundException;
import top.dwy.boot.mp.exception.InsufficientStockException;
import top.dwy.boot.mp.exception.InvalidParamException;
import top.dwy.boot.mp.mapper.BookMapper;
import top.dwy.boot.mp.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author alani
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Override
    public BookVO createBook(BookCreateDTO dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        book.setCategory(dto.getCategory());
        book.setStock(dto.getStock());
        save(book);
        return convertToVO(book);
    }

    @Override
    public BookVO updateBook(Long id, BookUpdateDTO dto) {
        Book book = getById(id);
        if (book == null) {
            throw new BookNotFoundException("图书不存在");
        }
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setCategory(dto.getCategory());
        book.setIsbn(dto.getIsbn());
        return convertToVO(book);
    }

    @Override
    @Transactional
    public BookVO adjustStock(Long id, StockAdjustDTO dto) {
        Book book = getById(id);
        if (book == null) {
            throw new BookNotFoundException("图书不存在");
        }
        if (dto.getAmount() <= 0) {
            throw new InvalidParamException("调整数量必须为正数");
        }
        if (dto.getType() != 1 && dto.getType() != 2) {
            throw new InvalidParamException("调整类型无效（仅支持1-入库，2-出库）");
        }

        Book lockedBook = baseMapper.selectByIdForUpdate(id);
        if (dto.getType() == 1) {
            lockedBook.setStock(lockedBook.getStock() + dto.getAmount());
        } else {
            if (lockedBook.getStock() < dto.getAmount()) {
                throw new InsufficientStockException("库存不足");
            }
            lockedBook.setStock(lockedBook.getStock() - dto.getAmount());
        }
        updateById(lockedBook);
        return convertToVO(lockedBook);
    }

    @Override
    public BookVO getBookById(Long id) {
        Book book = getById(id);
        if (book == null) {
            throw new RuntimeException("图书不存在");
        }
        return convertToVO(book);
    }

    @Override
    public boolean checkIsbnExists(String isbn) {
        return count(new LambdaQueryWrapper<Book>().eq(Book::getIsbn, isbn)) > 0;
    }

    @Override
    public PageResult<BookVO> pageQuery(BookPageQuery query) {
        Page<Book> page = new Page<>(query.getPageNum(), query.getPageSize());
        LambdaQueryWrapper<Book> wrapper = new LambdaQueryWrapper<>();

        if (query.getTitle() != null && !query.getTitle().isEmpty()) {
            wrapper.like(Book::getTitle, query.getTitle());
        }
        if (query.getAuthor() != null && !query.getAuthor().isEmpty()) {
            wrapper.like(Book::getAuthor, query.getAuthor());
        }
        if (query.getCategory() != null && !query.getCategory().isEmpty()) {
            wrapper.like(Book::getCategory, query.getCategory());
        }

        Page<Book> bookPage = page(page, wrapper);
        List<BookVO> voList = bookPage.getRecords().stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());

        return new PageResult<>(bookPage.getTotal(), voList, query.getPageNum(), query.getPageSize());
    }

    @Override
    public boolean deleteBook(Long id) {
        Book book = getById(id);
        if (book == null) {
            throw new RuntimeException("图书不存在");
        }
        return removeById(id);
    }

    @Override
    @Transactional
    public BookVO restoreBook(Long id) {
        Book book = baseMapper.selectByIdForUpdate(id);
        if (book == null) {
            throw new BookNotFoundException("图书不存在");
        }
        if (book.getDeleted() == 0) {
            throw new BusinessException("图书未被删除，无需恢复");
        }
        book.setDeleted(0);
        updateById(book);
        return convertToVO(book);
    }

    private BookVO convertToVO(Book book) {
        BookVO vo = new BookVO();
        vo.setId(book.getId());
        vo.setTitle(book.getTitle());
        vo.setAuthor(book.getAuthor());
        vo.setIsbn(book.getIsbn());
        vo.setCategory(book.getCategory());
        vo.setStock(book.getStock());
        vo.setCreateTime(book.getCreateTime());
        vo.setUpdateTime(book.getUpdateTime());
        return vo;
    }
}
