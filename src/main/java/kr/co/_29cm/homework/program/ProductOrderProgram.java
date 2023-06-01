package kr.co._29cm.homework.program;

import kr.co._29cm.homework.product.dto.ProductQuantityDto;
import kr.co._29cm.homework.product.dto.ProductResponseDto;
import kr.co._29cm.homework.program.view.InputView;
import kr.co._29cm.homework.program.view.OutputView;
import kr.co._29cm.homework.program.view.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RequiredArgsConstructor
@Component
@Profile("!test")
public class ProductOrderProgram {
    private final RestTemplate restTemplate = new RestTemplate();

    public void start() {
        while (true) {
            String input = InputView.inputOrderOrQuit();

            if ("o".equals(input) || "order".equals(input)) {
                OutputView.printProducts(Objects.requireNonNull(getProductDtos().getBody()));
                List<ProductQuantityDto> productQuantities = getProductQuantities();

                OutputView.printOrderHistory();

            } else if ("q".equals(input) || "quit".equals(input)) {
                OutputView.printFinalMessage();
                break;
            }
        }
    }

    private List<ProductQuantityDto> getProductQuantities() {
        List<ProductQuantityDto> productQuantityDtos = new ArrayList<>();
        while (true) {
            String productNo = InputView.inputProductNo();

            if (productNo.equals("next")) {
                break;
            }

            String quantity = InputView.inputQuantity();
            if (quantity.equals("next")) {
                break;
            }

            try {
                productQuantityDtos.add(new ProductQuantityDto(productNo, Integer.parseInt(quantity)));
            } catch (Exception e) {
                System.out.println("상품 정보를 다시 입력해주세요.");
            }
        }
        return productQuantityDtos;
    }

    private ResponseEntity<List<ProductResponseDto>> getProductDtos() {
        final String productsUrl = "http://localhost:8080/api/v1/products";
        return restTemplate.exchange(productsUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
    }
}
