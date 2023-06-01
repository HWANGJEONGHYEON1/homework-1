package kr.co._29cm.homework.order;

import kr.co._29cm.homework.order.dto.OrderRequestDto;
import kr.co._29cm.homework.order.dto.OrderResponseDto;
import kr.co._29cm.homework.product.Product;
import kr.co._29cm.homework.product.ProductRepository;
import kr.co._29cm.homework.product.dto.ProductQuantityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public OrderResponseDto order(OrderRequestDto orderRequestDto) {

        List<OrderProduct> orderProducts = new ArrayList<>();
        for (ProductQuantityDto productQuantityDto : orderRequestDto.getProductQuantityDtos()) {
            Product product = productRepository.findByProductNo(productQuantityDto.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("productNo is not exist"));

            OrderProduct orderProduct = OrderProduct.createOrderProduct(product, productQuantityDto.getQuantity());
            orderProducts.add(orderProduct);
        }
        Order order = Order.createOrder(LocalDateTime.now(), orderProducts);

        orderRepository.save(order);
        return new OrderResponseDto(order);
    }
}
