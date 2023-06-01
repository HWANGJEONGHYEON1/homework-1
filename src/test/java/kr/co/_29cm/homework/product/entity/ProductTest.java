package kr.co._29cm.homework.product.entity;

import kr.co._29cm.homework.exception.SoldOutException;
import kr.co._29cm.homework.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static kr.co._29cm.homework.helper.TestHelper.testProduct1;
import static org.assertj.core.api.Assertions.*;

class ProductTest {

    @Test
    @DisplayName("상품 생성")
    void create() {
        Product product = testProduct1;

        assertThat(product.getProductNo()).isEqualTo("111");
        assertThat(product.getName()).isEqualTo("무지티셔츠");
        assertThat(product.getPrice()).isEqualTo(7000);
        assertThat(product.getStock()).isEqualTo(7);
        assertThat(product.getStatus()).isEqualTo(Product.Status.SALE);
    }

    @Test
    @DisplayName("재고 감소")
    void decrease_stock() {
        Product product = testProduct1;
        product.removeStock(5);

        assertThat(product.getStock()).isEqualTo(2);
    }

    @Test
    @DisplayName("재고 부족 시 에러 발생")
    void decrease_stock_exception() {
        Product product = testProduct1;

        assertThatThrownBy(() -> product.removeStock(11))
                .isInstanceOf(SoldOutException.class);
    }
}