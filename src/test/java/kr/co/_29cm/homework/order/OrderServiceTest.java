package kr.co._29cm.homework.order;


import kr.co._29cm.homework.helper.TestHelper;
import kr.co._29cm.homework.order.dto.OrderRequestDto;
import kr.co._29cm.homework.order.dto.OrderResponseDto;
import kr.co._29cm.homework.product.Product;
import kr.co._29cm.homework.product.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Transactional
@ActiveProfiles("test")
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setup() {
        productRepository.save(TestHelper.testProduct1);
        productRepository.save(TestHelper.testProduct2);
        productRepository.save(TestHelper.testProduct3);
    }

    @AfterEach
    void delete() {
        productRepository.deleteAll();
    }

    @Test
    @DisplayName("주문 생성")
    void order() {
        OrderRequestDto testOrderRequestDto = TestHelper.testOrderRequestDto;
        OrderResponseDto orderResponseDtos = orderService.order(testOrderRequestDto);

        assertThat(orderResponseDtos.getOrderProductDtos().get(0).getProductName()).isEqualTo("무지티셔츠");
        assertThat(orderResponseDtos.getOrderProductDtos().get(0).getCount()).isEqualTo(5);

        assertThat(orderResponseDtos.getOrderProductDtos().get(1).getProductName()).isEqualTo("청바지");
        assertThat(orderResponseDtos.getOrderProductDtos().get(1).getCount()).isEqualTo(3);

        assertThat(orderResponseDtos.getDeliveryFee()).isEqualTo(0);
        assertThat(orderResponseDtos.getOrderPrice()).isEqualTo(110000);

        Product product = productRepository.findByProductNo("111").get();

        assertThat(product.getStock()).isEqualTo(5);
//        assertThat(productRepository.findByProductNo("222").get().getStock()).isEqualTo(7);
    }
}
