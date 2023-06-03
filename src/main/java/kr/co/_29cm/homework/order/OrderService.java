package kr.co._29cm.homework.order;

import kr.co._29cm.homework.exception.ErrorCode;
import kr.co._29cm.homework.exception.NotExistProductException;
import kr.co._29cm.homework.exception.ProductQuantityInvalidException;
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

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public OrderResponseDto order(OrderRequestDto orderRequestDto) {
        System.out.println("#Thread :"+ Thread.currentThread().getName());
        List<OrderProduct> orderProducts = new ArrayList<>();
        for (ProductQuantityDto productQuantityDto : orderRequestDto.getProductQuantityDtos()) {
            Product product = validateProductDto(productQuantityDto);

            OrderProduct orderProduct = OrderProduct.createOrderProduct(product, productQuantityDto.getQuantity());
            orderProducts.add(orderProduct);
        }
        Order order = Order.createOrder(LocalDateTime.now(), orderProducts);

        orderRepository.save(order);
        return new OrderResponseDto(order);
    }

    private Product validateProductDto(ProductQuantityDto productQuantityDto) {
        Product product = productRepository.findByProductNo(productQuantityDto.getProductId())
                .orElseThrow(() -> new NotExistProductException(ErrorCode.REQUEST_INVALID_001));

        if (productQuantityDto.getQuantity() <= 0) {
            throw new ProductQuantityInvalidException(ErrorCode.REQUEST_INVALID_001);
        }
        return product;
    }
}
