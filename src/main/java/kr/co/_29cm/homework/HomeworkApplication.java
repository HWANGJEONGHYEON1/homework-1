package kr.co._29cm.homework;

import kr.co._29cm.homework.program.ProductOrderProgram;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class HomeworkApplication {

    private final ProductOrderProgram productOrderProgram;
    public static void main(String[] args) {
        SpringApplication.run(HomeworkApplication.class, args);
    }


}
