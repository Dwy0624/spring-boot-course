package top.dwy.boot.mp.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import top.dwy.boot.mp.common.dto.BookCreateDTO;
import top.dwy.boot.mp.common.dto.BookPageQuery;
import top.dwy.boot.mp.common.dto.BookUpdateDTO;
import top.dwy.boot.mp.common.dto.StockAdjustDTO;
import top.dwy.boot.mp.common.result.PageResult;
import top.dwy.boot.mp.common.result.Result;
import top.dwy.boot.mp.common.vo.BookVO;
import top.dwy.boot.mp.service.BookService;
import java.util.HashMap;
import java.util.Map;

/**
 * @author alani
 */
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public Result<BookVO> createBook(@Valid @RequestBody BookCreateDTO dto) {
        BookVO bookVO = bookService.createBook(dto);
        return Result.success(bookVO);
    }

    @PutMapping("/{id}")
    public Result<BookVO> updateBook(@PathVariable Long id, @Valid @RequestBody BookUpdateDTO dto) {
        BookVO bookVO = bookService.updateBook(id, dto);
        return Result.success(bookVO);
    }

    @PatchMapping("/{id}/stock/adjust")
    public Result<BookVO> adjustStock(@PathVariable Long id, @Valid @RequestBody StockAdjustDTO dto) {
        BookVO bookVO = bookService.adjustStock(id, dto);
        return Result.success(bookVO);
    }

    @GetMapping("/{id}")
    public Result<BookVO> getBookById(@PathVariable Long id) {
        BookVO bookVO = bookService.getBookById(id);
        return Result.success(bookVO);
    }

    @GetMapping("/exists/isbn/{isbn}")
    public Result<Map<String, Boolean>> checkIsbnExists(@PathVariable String isbn) {
        boolean exists = bookService.checkIsbnExists(isbn);
        Map<String, Boolean> result = new HashMap<>();
        result.put("exists", exists);
        return Result.success(result);
    }

    @GetMapping("/page")
    public Result<PageResult<BookVO>> pageQuery(BookPageQuery query) {
        PageResult<BookVO> pageResult = bookService.pageQuery(query);
        return Result.success(pageResult);
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return Result.success(null);
    }

    @PutMapping("/{id}/restore")
    public Result<BookVO> restoreBook(@PathVariable Long id) {
        BookVO bookVO = bookService.restoreBook(id);
        return Result.success(bookVO);
    }
}