package kr.co._29cm.homework.exception;

import kr.co._29cm.homework.common.ResponseObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = {Throwable.class, Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseObject<Object> defaultExceptionHandler(Exception e) {

        return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SoldOutException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseObject<Object> soldOutExceptionHandler(Exception e) {

        return ResponseObject.error(ErrorCode.SOLD_OUT);
    }

    @ExceptionHandler(NotExistProductException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseObject<Object> notExistProductExceptionHandler(Exception e) {

        return ResponseObject.error(ErrorCode.REQUEST_INVALID_001);
    }

    @ExceptionHandler(ProductQuantityInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseObject<Object> productQuantityInvalidExceptionHandler(Exception e) {

        return ResponseObject.error(ErrorCode.REQUEST_INVALID_002);
    }


}
