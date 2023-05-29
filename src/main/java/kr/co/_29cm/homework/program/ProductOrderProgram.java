package kr.co._29cm.homework.program;

import kr.co._29cm.homework.product.Product;
import kr.co._29cm.homework.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ProductOrderProgram {

    private final ProductRepository productRepository;

    public void start() {
        List<Product> all = productRepository.findAll();
        for (Product product : all) {
            System.out.println(product);
        }
    }
}
