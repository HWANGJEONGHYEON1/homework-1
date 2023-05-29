package kr.co._29cm.homework.product.csv;


import kr.co._29cm.homework.product.Product;
import kr.co._29cm.homework.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductInitService {
    private final ProductRepository productRepository;

    @Transactional
    public void insertData(List<Product> products) {
        productRepository.saveAll(products);
    }
}