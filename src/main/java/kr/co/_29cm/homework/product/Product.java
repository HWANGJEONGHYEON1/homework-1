package kr.co._29cm.homework.product;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "product")
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String no;
    private String name;
    private Long price;
    private int stock;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        SALE("판매중"),
        SALES_END("판매종료");

        private final String description;
    }

    @Builder
    public Product(String no, String name, Long price, int stock) {
        if (StringUtils.isBlank(no)) {
            throw new IllegalArgumentException("empty productNo");
        }
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("empty productName");
        }
        if (Objects.isNull(price)) {
            throw new IllegalArgumentException("empty price");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("empty stock");
        }

        this.no = no;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.status = Status.SALE;
    }
}