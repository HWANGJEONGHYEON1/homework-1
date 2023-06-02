package kr.co._29cm.homework.product;

import kr.co._29cm.homework.product.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductResponseDto> products() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductResponseDto(
                        product.getProductNo(),
                        product.getName(),
                        product.getPrice(),
                        product.getStock()
                )).collect(Collectors.toList());
    }
}
