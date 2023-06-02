package kr.co._29cm.homework.product;

import kr.co._29cm.homework.exception.ErrorCode;
import kr.co._29cm.homework.exception.SoldOutException;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(unique = true)
    private String productNo;

    private String name;
    private int price;
    private int stock;

    @Enumerated(EnumType.STRING)
    private Status status;

    public void removeStock(int quantity) {
        int restStock = this.stock - quantity;
        if (restStock < 0) {
            throw new SoldOutException(ErrorCode.SOLD_OUT);
        }
        this.stock = restStock;
    }

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        SALE("판매중"),
        SALES_END("판매종료");

        private final String description;
    }

    public static Product createProduct(String productNo, String name, int price, int stock) {
        if (StringUtils.isBlank(productNo)) {
            throw new IllegalArgumentException("empty productNo");
        }
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("empty productName");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("empty price");
        }
        if (stock <= 0) {
            throw new IllegalArgumentException("empty stock");
        }

        Product product = new Product();
        product.setProductNo(productNo);
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);
        product.setStatus(Status.SALE);
        return product;
    }
}