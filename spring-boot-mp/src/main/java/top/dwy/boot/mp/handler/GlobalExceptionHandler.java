package top.dwy.boot.mp.handler;

import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.dwy.boot.mp.common.result.Result;
import top.dwy.boot.mp.exception.BookNotFoundException;
import top.dwy.boot.mp.exception.InvalidParamException;
import top.dwy.boot.mp.exception.BusinessException;

/**
 * @author alani
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private static final int BAD_REQUEST_CODE = 400;
    private static final int INTERNAL_ERROR_CODE = 500;
    private static final int NOT_FOUND_CODE = 404;

    // 处理图书未找到异常
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Result<?>> handleBookNotFoundException(BookNotFoundException e) {
        return buildErrorResponse(NOT_FOUND_CODE, e.getMessage(), HttpStatus.NOT_FOUND);
    }

    // 处理参数无效异常
    @ExceptionHandler(InvalidParamException.class)
    public ResponseEntity<Result<?>> handleInvalidParamException(InvalidParamException e) {
        return buildErrorResponse(BAD_REQUEST_CODE, e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // 处理通用业务异常
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Result<?>> handleBusinessException(BusinessException e) {
        return buildErrorResponse(BAD_REQUEST_CODE, e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<Result<?>> handleBindException(BindException e) {
        String errorMsg = "请求参数校验失败";
        if (e.getBindingResult().getFieldError() != null) {
            errorMsg = e.getBindingResult().getFieldError().getDefaultMessage();
        }
        return buildErrorResponse(BAD_REQUEST_CODE, errorMsg, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Result<?>> handleConstraintViolationException(ConstraintViolationException e) {
        String errorMsg = e.getMessage();
        return buildErrorResponse(BAD_REQUEST_CODE, errorMsg, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result<?>> handleException(Exception e) {
        logger.error("服务器内部错误", e);
        return buildErrorResponse(INTERNAL_ERROR_CODE, "服务器内部错误", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Result<?>> buildErrorResponse(int code, String message, HttpStatus status) {
        return ResponseEntity.status(status).body(Result.fail(code, message));
    }
}