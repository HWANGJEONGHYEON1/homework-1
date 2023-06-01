package kr.co._29cm.homework.product.dto;

import lombok.Getter;

@Getter

public class ProductQuantityDto {

    private String productId;
    private int quantity;

    public ProductQuantityDto(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
