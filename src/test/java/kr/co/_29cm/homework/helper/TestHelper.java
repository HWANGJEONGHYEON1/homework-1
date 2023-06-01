package kr.co._29cm.homework.helper;

import kr.co._29cm.homework.order.Order;
import kr.co._29cm.homework.order.OrderProduct;
import kr.co._29cm.homework.order.dto.OrderRequestDto;
import kr.co._29cm.homework.product.Product;
import kr.co._29cm.homework.product.dto.ProductQuantityDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class TestHelper {
    public static LocalDateTime orderDateTime = LocalDateTime.parse("2023-01-23 11:00:11", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    // 상품 테스트 data
    public static Product testProduct1 = Product.createProduct("111", "무지티셔츠", 7000, 10);
    public static Product testProduct2 = Product.createProduct("222", "청바지", 25000, 100);
    public static Product testProduct3 = Product.createProduct("333", "폴로카라티", 30000, 50);

    public static OrderProduct testOrderProduct1 = OrderProduct.createOrderProduct(testProduct1, 3);
    public static OrderProduct testOrderProduct2 = OrderProduct.createOrderProduct(testProduct2, 1);
    public static OrderProduct testOrderProduct3 = OrderProduct.createOrderProduct(testProduct3, 2);

    public static Order testOrder1 = Order.createOrder(orderDateTime, Arrays.asList(testOrderProduct1, testOrderProduct2));
    public static Order testOrder2 = Order.createOrder(orderDateTime, Arrays.asList(testOrderProduct1, testOrderProduct2, testOrderProduct3));

    public static List<ProductQuantityDto> productQuantityDtos = Arrays.asList(new ProductQuantityDto(testProduct1.getProductNo(), 5), new ProductQuantityDto(testProduct2.getProductNo(), 3));
    public static OrderRequestDto testOrderRequestDto = new OrderRequestDto(productQuantityDtos);
}
