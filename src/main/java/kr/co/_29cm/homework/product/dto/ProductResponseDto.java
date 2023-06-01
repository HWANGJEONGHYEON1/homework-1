package kr.co._29cm.homework.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {
    private String no;
    private String name;
    private int price;
    private int stock;
}
