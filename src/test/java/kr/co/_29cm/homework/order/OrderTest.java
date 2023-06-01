package kr.co._29cm.homework.order;

import kr.co._29cm.homework.helper.TestHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class OrderTest {

    @Test
    @DisplayName("주문 생성 : 주문금액 50000원 이하일 경우 배송비추가")
    public void create_delivery_fee() {
        Order testOrder = TestHelper.testOrder1;

        assertThat(testOrder.getTotalPrice()).isEqualTo(46000);
        assertThat(testOrder.getDeliveryFee()).isEqualTo(2500);
        assertThat(testOrder.getOrderProducts().size()).isEqualTo(2);
        assertThat(testOrder.getOrderProducts()).contains(TestHelper.testOrderProduct1, TestHelper.testOrderProduct2);
    }

    @Test
    @DisplayName("주문 생성 : 주문금액 50000원 이상일 경우 배송비없음")
    public void create_no_delivery_fee() {
        Order testOrder = TestHelper.testOrder2;

        assertThat(testOrder.getTotalPrice()).isEqualTo(106000);
        assertThat(testOrder.getDeliveryFee()).isEqualTo(0);
        assertThat(testOrder.getOrderProducts().size()).isEqualTo(3);
        assertThat(testOrder.getOrderProducts()).contains(TestHelper.testOrderProduct1, TestHelper.testOrderProduct2, TestHelper.testOrderProduct3);
    }
}
