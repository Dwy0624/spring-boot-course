package top.dwy.boot.redis.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import top.dwy.boot.redis.enums.ErrorCode;
import top.dwy.boot.redis.result.Result;

/**
 * @author alani
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ServerException extends RuntimeException{
    private int code;
    private String msg;

    public ServerException(String msg) {
        this.code = ErrorCode.SERVER_ERROR.getCode();
        this.msg = msg;
    }

    public ServerException(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public ServerException(String msg, Throwable e) {
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
