package kr.co._29cm.homework.order;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(value = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    private LocalDateTime orderDate;
    private long deliveryFee;

    public void addOrderItem(OrderProduct orderItem) {
        orderProducts.add(orderItem);
        orderItem.setOrder(this);
    }

    public static Order createOrder(LocalDateTime localDateTime, List<OrderProduct> orderItems) {
        Order order = new Order();
        for (OrderProduct orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }

        order.setDeliveryFee(order.getTotalPrice() >= 50000 ? 0 : 2500);
        order.setOrderDate(localDateTime);
        return order;
    }

    // 주문가격 조회
    public int getTotalPrice() {
        return orderProducts.stream()
                .mapToInt(OrderProduct::getTotalPrice)
                .sum();
    }
}
