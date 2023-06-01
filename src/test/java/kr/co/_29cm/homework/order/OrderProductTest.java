package kr.co._29cm.homework.order;

import kr.co._29cm.homework.helper.TestHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class OrderProductTest {

    @Test
    @DisplayName("주문 상품 생성")
    void create() {
        OrderProduct testOrderProduct = TestHelper.testOrderProduct2;

        assertThat(testOrderProduct.getProduct()).isEqualTo(TestHelper.testProduct1);
        assertThat(testOrderProduct.getCount()).isEqualTo(3);
        assertThat(testOrderProduct.getOrderPrice()).isEqualTo(7000);
        assertThat(testOrderProduct.getProduct().getStock()).isEqualTo(7);

        assertThat(testOrderProduct.getTotalPrice()).isEqualTo(21000); // 상품 주문 전체 가격
    }
}
