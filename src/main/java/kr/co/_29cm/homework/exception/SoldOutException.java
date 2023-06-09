package kr.co._29cm.homework.exception;

public class SoldOutException extends RuntimeException {

    private final ErrorCode errorCode;
    private final String detail;

    public SoldOutException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.detail = errorCode.getMessage();
    }

    public SoldOutException(ErrorCode errorCode, String detail) {
        this.errorCode = errorCode;
        this.detail = detail;
    }
}
