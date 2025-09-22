package top.dwy.boot.exception.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import top.dwy.boot.exception.common.Result;
import top.dwy.boot.exception.enums.ErrorCode;

/**
 * @author alani
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException{
    private int code;
    private String msg;

    public BusinessException(String msg) {
        this.code = ErrorCode.SERVER_ERROR.getCode();
        this.msg = msg;
    }

    public BusinessException(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public BusinessException(String msg, Throwable e) {
        super(msg, e);
        this.code = ErrorCode.SERVER_ERROR.getCode();
        this.msg = msg;
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Result<?>> handleRuntimeException(RuntimeException e) {
        // 修复方式1：使用Result类中实际存在的方法
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Result.error(e.getMessage()));
    }
}
