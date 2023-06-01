package kr.co._29cm.homework.order.dto;

import kr.co._29cm.homework.order.Order;
import kr.co._29cm.homework.order.OrderProduct;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class OrderResponseDto {

    private List<OrderProductDto> orderProductDtos;
    private long orderPrice;
    private long deliveryFee;

    public OrderResponseDto(Order order) {
        this.orderPrice = order.getTotalPrice();
        this.deliveryFee = order.getDeliveryFee();
        this.orderProductDtos = order.getOrderProducts()
                .stream()
                .map(orderProduct -> new OrderProductDto(orderProduct.getProduct().getName(), orderProduct.getCount()))
                .collect(Collectors.toList());
    }
}
