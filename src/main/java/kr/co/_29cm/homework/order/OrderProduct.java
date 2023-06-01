package kr.co._29cm.homework.order;

import kr.co._29cm.homework.product.Product;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
@EqualsAndHashCode
public class OrderProduct {

    @Id @GeneratedValue
    @Column(name = "order_product_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private int orderPrice;
    private int count;

    public static OrderProduct createOrderProduct(Product product, int count) {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProduct(product);
        orderProduct.setOrderPrice(product.getPrice());
        orderProduct.setCount(count);

        product.removeStock(count);
        return orderProduct;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getTotalPrice() {
        return orderPrice * count;
    }
}
