package kr.co._29cm.homework.program.view;

import kr.co._29cm.homework.product.dto.ProductResponseDto;

import java.util.List;

public class OutputView {

    public static final String HEADER = "상품번호\t상품명\t\t\t\t\t\t\t\t판매가격\t재고수량";
    public static final String HORIZONTAL = "---------------------------------------------";
    public static final String ORDER_LIST_MESSAGE = "주문 내역 : ";
    public static final String ORDER_AMOUNT_MESSAGE = "주문금액 : ";
    public static final String DELIVERY_FEE_MESSAGE = "배송비 : ";
    public static final String PAYMENT_AMOUNT_MESSAGE = "지불금액 : ";
    public static final String FINAL_MESSAGE = "고객님의 주문 감사합니다.";

    public static final String SOLD_OUT_MESSAGE = "SoldOutException 발생. 주문한 상품량이 재고량보다 큽니다.";
    public static final String EMPTY_ORDER_MESSAGE = "주문하신 상품이 없습니다.";

    public static void printProducts(List<ProductResponseDto> products) {
        System.out.println(HEADER);
        StringBuilder productInfoStringBuilder = new StringBuilder();

        products.forEach(product -> {
            productInfoStringBuilder.append(product.getNo());
            productInfoStringBuilder.append("\t");
            productInfoStringBuilder.append(product.getName());
            productInfoStringBuilder.append("\t");
            productInfoStringBuilder.append(product.getPrice());
            productInfoStringBuilder.append("\t");
            productInfoStringBuilder.append(product.getStock());
            productInfoStringBuilder.append("\n");
        });

        System.out.println(productInfoStringBuilder);
    }

    public static void printFinalMessage() {
        System.out.println(FINAL_MESSAGE);
    }

    public static void printOrderHistory() {
        System.out.println(ORDER_LIST_MESSAGE);
        System.out.println(HORIZONTAL);
    }
}
