package top.dwy.boot.exception.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
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

    public BusinessException(String msg,Throwable e) {
        this.code = ErrorCode.SERVER_ERROR.getCode();
        this.msg = msg;
    }

}
