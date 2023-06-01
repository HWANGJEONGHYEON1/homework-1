package kr.co._29cm.homework.order;

import kr.co._29cm.homework.order.dto.OrderRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/api/v1/order")
    public void order(@RequestBody OrderRequestDto orderRequestDto) {
        orderService.order(orderRequestDto);
    }
}
