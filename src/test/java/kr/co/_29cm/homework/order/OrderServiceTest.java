package kr.co._29cm.homework.order;


import kr.co._29cm.homework.exception.NotExistProductException;
import kr.co._29cm.homework.exception.ProductQuantityInvalidException;
import kr.co._29cm.homework.exception.SoldOutException;
import kr.co._29cm.homework.helper.TestHelper;
import kr.co._29cm.homework.order.dto.OrderRequestDto;
import kr.co._29cm.homework.order.dto.OrderResponseDto;
import kr.co._29cm.homework.product.Product;
import kr.co._29cm.homework.product.ProductRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.*;

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

    private static final int THREAD_COUNT = 100;

    private final CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);
    private final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);


    @BeforeEach
    void setup() {
        // 748943,디오디너리 데일리 세트 (Daily set),19000,89
        // 779989,버드와이저 HOME DJing 굿즈 세트,35000,43
        productRepository.save(Product.createProduct("748943", "디오디너리 데일리 세트 (Daily set)", 19000, 100));
        productRepository.save(Product.createProduct("779989", "버드와이저 HOME DJing 굿즈 세트", 35000, 20));
    }

    @AfterEach
    void deleteAll() {
        orderRepository.deleteAll();
        orderProductRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    @DisplayName("멀티 스레드 주문하기")
    void multi_thread_order() throws InterruptedException {
        OrderRequestDto testOrderRequestDto = TestHelper.multiTestOrderDeliveryIncludeRequestDto;

        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                orderService.order(testOrderRequestDto);
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();

        assertThat(productRepository.findByProductNo("748943").get().getStock()).isEqualTo(0);

//        assertThatThrownBy( () -> {
//            for (int i=0; i < 10; i++) {
//                try {
//                    executorService.submit(() -> orderService.order(testOrderRequestDto)).get();
//                    countDownLatch.countDown();
//                } catch (Exception e) {
//                    if (e.getCause() instanceof SoldOutException) {
//                        throw e.getCause();
//                    }
//                }
//            }
//        }).isInstanceOf(SoldOutException.class);
    }

    @Test
    @Transactional
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
    @Transactional
    @DisplayName("주문하기 : 배송비 추가")
    void orderDeliveryInclude() {
        OrderRequestDto testOrderRequestDto = TestHelper.testOrderDeliveryIncludeRequestDto;
        OrderResponseDto orderResponseDtos = orderService.order(testOrderRequestDto);

        assertThat(orderResponseDtos.getOrderProductDtos().get(0).getProductName()).isEqualTo("디오디너리 데일리 세트 (Daily set)");
        assertThat(orderResponseDtos.getDeliveryFee()).isEqualTo(2500);
    }

    @Test
    @Transactional
    @DisplayName("주문하기 : 재고부족 에러처리")
    void stockLess() {
        OrderRequestDto testOrderRequestDto = TestHelper.testOrderStockLessRequestDto;
        assertThatThrownBy(() -> orderService.order(testOrderRequestDto))
                .isInstanceOf(SoldOutException.class);
    }

    @Test
    @Transactional
    @DisplayName("주문하기 : 존재하지않는 ProductNo 에러처리")
    void notExistProductNo() {
        OrderRequestDto testOrderRequestDto = TestHelper.testOrderNotExistProductNoRequestDto;
        assertThatThrownBy(() -> orderService.order(testOrderRequestDto))
                .isInstanceOf(NotExistProductException.class);
    }

    @Test
    @Transactional
    @DisplayName("주문하기 : 상품 수량 0이하로 주문 시 에러처리")
    void lessThanZero() {
        OrderRequestDto testOrderRequestDto = TestHelper.testOrderProductQuantityZeroRequestDto;
        assertThatThrownBy(() -> orderService.order(testOrderRequestDto))
                .isInstanceOf(ProductQuantityInvalidException.class);
    }
}
