package kr.co._29cm.homework.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import kr.co._29cm.homework.exception.ErrorCode;
import lombok.*;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class ResponseError {

    private ErrorCode code;

    private String title;

    private String detail;

    public static ResponseError ofErrorCode(ErrorCode errorCode) {
        return ResponseError.builder()
                .code(errorCode)
                .title(errorCode.name())
                .detail(errorCode.getMessage())
                .build();
    }
}
