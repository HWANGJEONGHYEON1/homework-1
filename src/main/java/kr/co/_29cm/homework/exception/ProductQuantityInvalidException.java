package kr.co._29cm.homework.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class ProductQuantityInvalidException extends RuntimeException {

    private final ErrorCode errorCode;
    private final String detail;

    public ProductQuantityInvalidException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.detail = errorCode.getMessage();
    }

    public ProductQuantityInvalidException(ErrorCode errorCode, String detail) {
        this.errorCode = errorCode;
        this.detail = detail;
    }
}
