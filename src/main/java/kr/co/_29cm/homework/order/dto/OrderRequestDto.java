package kr.co._29cm.homework.order.dto;

import kr.co._29cm.homework.product.dto.ProductQuantityDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {
    private List<ProductQuantityDto> productQuantityDtos;
}
