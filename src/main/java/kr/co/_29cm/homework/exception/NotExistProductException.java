package kr.co._29cm.homework.exception;

public class NotExistProductException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String detail;

    public NotExistProductException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.detail = errorCode.getMessage();
    }

    public NotExistProductException(ErrorCode errorCode, String detail) {
        this.errorCode = errorCode;
        this.detail = detail;
    }
}
