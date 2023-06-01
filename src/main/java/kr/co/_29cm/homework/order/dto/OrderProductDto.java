package kr.co._29cm.homework.order.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderProductDto {
    private String productName;
    private int count;

    public OrderProductDto(String productName, int count) {
        this.productName = productName;
        this.count = count;
    }
}
