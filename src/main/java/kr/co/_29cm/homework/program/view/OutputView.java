package kr.co._29cm.homework.program.view;

import kr.co._29cm.homework.common.ResponseError;
import kr.co._29cm.homework.order.dto.OrderProductDto;
import kr.co._29cm.homework.order.dto.OrderResponseDto;
import kr.co._29cm.homework.product.dto.ProductResponseDto;

import java.util.List;

import static kr.co._29cm.homework.exception.ErrorCode.*;

public class OutputView {

    private static final String HEADER = "상품번호\t상품명\t\t\t\t\t\t\t\t판매가격\t재고수량";
    private static final String HORIZONTAL = "---------------------------------------------";
    private static final String ORDER_LIST_MESSAGE = "주문 내역 : ";
    private static final String ORDER_AMOUNT_MESSAGE = "주문금액 : ";
    private static final String DELIVERY_FEE_MESSAGE = "배송비 : ";
    private static final String PAYMENT_AMOUNT_MESSAGE = "지불금액 : ";
    private static final String FINAL_MESSAGE = "고객님의 주문 감사합니다.";
    private static final String SOLD_OUT_MESSAGE = "SoldOutException 발생. 주문한 상품량이 재고량보다 큽니다.";
    private static final String EMPTY_ORDER_MESSAGE = "주문하신 상품이 없습니다.";
    private static final String PRODUCT_QUANTITY_MORE_THAN_ZERO_MESSAGE = "주문하실 물품의 개수는 1개 이상이어야합니다.";

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

    public static void printOrderHistory(OrderResponseDto orderResponseDto) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ORDER_LIST_MESSAGE);
        stringBuilder.append("\n");
        stringBuilder.append(HORIZONTAL);
        stringBuilder.append("\n");

        for (OrderProductDto orderProductDto : orderResponseDto.getOrderProductDtos()) {
            stringBuilder.append(String.format("%s - %s개", orderProductDto.getProductName(), orderProductDto.getCount()));
            stringBuilder.append("\n");
        }

        stringBuilder.append(HORIZONTAL);
        stringBuilder.append("\n");
        stringBuilder.append(String.format(ORDER_AMOUNT_MESSAGE + " - %s원", orderResponseDto.getOrderPrice()));

        long deliveryFee = orderResponseDto.getDeliveryFee();
        if (deliveryFee > 0) {
            stringBuilder.append("\n");
            stringBuilder.append(String.format(DELIVERY_FEE_MESSAGE + " - %s원", deliveryFee));
        }

        stringBuilder.append("\n");
        stringBuilder.append(HORIZONTAL);
        stringBuilder.append("\n");
        stringBuilder.append(String.format(PAYMENT_AMOUNT_MESSAGE + " - %s원", orderResponseDto.getTotalPrice()));
        System.out.println(stringBuilder);
    }

    public static void printErrorMessage(ResponseError responseError) {

        switch (responseError.getCode()) {
            case SOLD_OUT:
                System.out.println(SOLD_OUT_MESSAGE);
                break;
            case REQUEST_INVALID_001:
                System.out.println(EMPTY_ORDER_MESSAGE);
                break;
            case REQUEST_INVALID_002:
                System.out.println(PRODUCT_QUANTITY_MORE_THAN_ZERO_MESSAGE);
                break;
            case BAD_REQUEST:
                System.out.println(responseError.getTitle());
                break;
            default:
                System.out.println(INTERNAL_SERVER_ERROR.getMessage());
        }
    }
}
