package top.dwy.boot.redis.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.dwy.boot.redis.enums.ErrorCode;

/**
 * @author alani
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class ServerException extends RuntimeException{
    private int code;
    private String msg;
    public ServerException(String msg) {
        this.code = ErrorCode.PHONE_ERROR.getCode();
        this.msg = msg;
    }

    public ServerException(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public ServerException(String msg,Throwable e) {
        this.code = ErrorCode.PHONE_ERROR.getCode();
        this.msg = msg;
    }

}
