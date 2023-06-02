package kr.co._29cm.homework.product;

import kr.co._29cm.homework.common.ResponseObject;
import kr.co._29cm.homework.product.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/api/v1/products")
    public ResponseObject<List<ProductResponseDto>> products() {
        return ResponseObject.toResponse(productService.products());
    }
}
