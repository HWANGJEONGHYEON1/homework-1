package kr.co._29cm.homework.order;

import javax.persistence.*;
import java.util.List;

@Entity(name = "orders")
public class Order {

    @Id @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
}
