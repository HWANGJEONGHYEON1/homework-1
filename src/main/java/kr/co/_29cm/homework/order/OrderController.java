package kr.co._29cm.homework.order;

import kr.co._29cm.homework.common.ResponseObject;
import kr.co._29cm.homework.order.dto.OrderRequestDto;
import kr.co._29cm.homework.order.dto.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/api/v1/order")
    public ResponseObject<OrderResponseDto> order(@RequestBody OrderRequestDto orderRequestDto) {
        return ResponseObject.toResponse(orderService.order(orderRequestDto));
    }
}
