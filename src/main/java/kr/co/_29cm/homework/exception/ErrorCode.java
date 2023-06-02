package kr.co._29cm.homework.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    REQUEST_INVALID_001("R001", "잘못된 요청입니다.(PRODUCT_NO)"),
    REQUEST_INVALID_002("R002", "잘못된 요청입니다.(주문수량)"),
    SOLD_OUT("S001", "재고부족"),
    BAD_REQUEST("R400", "잘못된 요청"),
    INTERNAL_SERVER_ERROR("S500", "시스템 에러 발생: 관리자에게 문의 바랍니다.");

    private final String code;
    private final String message;
}
