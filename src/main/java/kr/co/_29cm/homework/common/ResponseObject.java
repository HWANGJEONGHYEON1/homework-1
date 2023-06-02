package kr.co._29cm.homework.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import kr.co._29cm.homework.exception.ErrorCode;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ResponseObject<T> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    private ResponseError errors;

    public static <T> ResponseObject<T> toResponse(T data) {
        return ResponseObject.<T>builder()
                .data(data)
                .build();
    }

    public static ResponseObject<Object> error(ErrorCode errorCode) {
        return ResponseObject.builder()
                .errors(ResponseError.ofErrorCode(errorCode))
                .build();
    }
}
