package kr.co._29cm.homework.program;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co._29cm.homework.common.ResponseError;
import kr.co._29cm.homework.common.ResponseObject;
import kr.co._29cm.homework.order.dto.OrderRequestDto;
import kr.co._29cm.homework.order.dto.OrderResponseDto;
import kr.co._29cm.homework.product.dto.ProductQuantityDto;
import kr.co._29cm.homework.product.dto.ProductResponseDto;
import kr.co._29cm.homework.program.view.InputView;
import kr.co._29cm.homework.program.view.OutputView;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

@RequiredArgsConstructor
@Component
@Profile("!test")
public class ProductOrderProgram {
    private final RestTemplate restTemplate = new RestTemplate();

    public void start() throws IOException {
        while (true) {
            String input = InputView.inputOrderOrQuit();

            if ("o".equals(input) || "order".equals(input)) {
                OutputView.printProducts(Objects.requireNonNull(getProductDtos().getData()));
                List<ProductQuantityDto> productQuantities = getProductQuantities();
                OrderResponseDto order = order(productQuantities);
                if (Objects.isNull(order)) {
                    continue;
                }
                OutputView.printOrderHistory(order);

            } else if ("q".equals(input) || "quit".equals(input)) {
                OutputView.printFinalMessage();
                break;
            }
        }
    }

    private OrderResponseDto order(List<ProductQuantityDto> productQuantities) throws IOException {
        final String ordersUrl = "http://localhost:8080/api/v1/order";
        OrderRequestDto orderRequestDto = new OrderRequestDto(productQuantities);
        HttpEntity<OrderRequestDto> request = new HttpEntity<>(orderRequestDto);

        try {
            return restTemplate.exchange(ordersUrl, HttpMethod.POST, request, new ParameterizedTypeReference<ResponseObject<OrderResponseDto>>() {
            }).getBody().getData();
        } catch (RestClientResponseException e) {
            changeErrorToOutputView(e);
            return null;
        }
    }

    private static void changeErrorToOutputView(RestClientResponseException e) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseError responseError = objectMapper.readValue(e.getResponseBodyAsByteArray(), new TypeReference<ResponseObject<ResponseError>>() {
        }).getErrors();
        OutputView.printErrorMessage(responseError);
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

    private ResponseObject<List<ProductResponseDto>> getProductDtos() {
        final String productsUrl = "http://localhost:8080/api/v1/products";
        return restTemplate.exchange(productsUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseObject<List<ProductResponseDto>>>() {
                }).getBody();
    }
}
