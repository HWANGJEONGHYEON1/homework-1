package kr.co._29cm.homework.order;


import kr.co._29cm.homework.exception.SoldOutException;
import kr.co._29cm.homework.helper.TestHelper;
import kr.co._29cm.homework.order.dto.OrderRequestDto;
import kr.co._29cm.homework.order.dto.OrderResponseDto;
import kr.co._29cm.homework.product.Product;
import kr.co._29cm.homework.product.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@Transactional
@ActiveProfiles("test")
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderProductRepository orderProductRepository;

    @BeforeEach
    void setup() {
        // 748943,디오디너리 데일리 세트 (Daily set),19000,89
        // 779989,버드와이저 HOME DJing 굿즈 세트,35000,43
        productRepository.save(Product.createProduct("748943", "디오디너리 데일리 세트 (Daily set)", 19000, 89));
        productRepository.save(Product.createProduct("779989", "버드와이저 HOME DJing 굿즈 세트", 35000, 43));
    }

    @AfterEach
    void deleteAll() {
        productRepository.deleteAll();
        orderRepository.deleteAll();
        orderProductRepository.deleteAll();
    }

    @Test
    @DisplayName("주문하기 : 배송비 무료")
    void orderDeliveryFree() {
        OrderRequestDto testOrderRequestDto = TestHelper.testOrderDeliveryFeeFreeRequestDto;
        OrderResponseDto orderResponseDtos = orderService.order(testOrderRequestDto);

        assertThat(orderResponseDtos.getOrderProductDtos().get(0).getProductName()).isEqualTo("디오디너리 데일리 세트 (Daily set)");
        assertThat(orderResponseDtos.getOrderProductDtos().get(0).getCount()).isEqualTo(1);

        assertThat(orderResponseDtos.getOrderProductDtos().get(1).getProductName()).isEqualTo("버드와이저 HOME DJing 굿즈 세트");
        assertThat(orderResponseDtos.getOrderProductDtos().get(1).getCount()).isEqualTo(3);

        assertThat(orderResponseDtos.getDeliveryFee()).isEqualTo(0);
        assertThat(orderResponseDtos.getOrderPrice()).isEqualTo(124000);


        assertThat(productRepository.findByProductNo("748943").get().getStock()).isEqualTo(88);
    }

    @Test
    @DisplayName("주문하기 : 배송비 추가")
    void orderDeliveryInclude() {
        OrderRequestDto testOrderRequestDto = TestHelper.testOrderDeliveryIncludeRequestDto;
        OrderResponseDto orderResponseDtos = orderService.order(testOrderRequestDto);

        assertThat(orderResponseDtos.getOrderProductDtos().get(0).getProductName()).isEqualTo("디오디너리 데일리 세트 (Daily set)");
        assertThat(orderResponseDtos.getDeliveryFee()).isEqualTo(2500);
    }

    @Test
    @DisplayName("주문하기 : 재고부족")
    void stockLess() {
        OrderRequestDto testOrderRequestDto = TestHelper.testOrderStockLessRequestDto;
        assertThatThrownBy(() -> orderService.order(testOrderRequestDto))
                .isInstanceOf(SoldOutException.class);
    }

    @Test
    @DisplayName("주문하기 : 존재하지않는 ProductNo")
    void notExistProductNo() {
        OrderRequestDto testOrderRequestDto = TestHelper.testOrderNotExistProductNoRequestDto;
        assertThatThrownBy(() -> orderService.order(testOrderRequestDto))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
