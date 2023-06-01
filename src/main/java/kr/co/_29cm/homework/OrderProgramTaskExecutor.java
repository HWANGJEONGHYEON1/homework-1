package kr.co._29cm.homework;

import kr.co._29cm.homework.product.Product;
import kr.co._29cm.homework.product.csv.CSVReader;
import kr.co._29cm.homework.product.csv.ProductInitService;
import kr.co._29cm.homework.program.ProductOrderProgram;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@RequiredArgsConstructor
@Component
@Profile("!test")
public class OrderProgramTaskExecutor implements ApplicationRunner {

    private final ProductInitService dataInsertService;
    private final ProductOrderProgram productOrderProgram;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        File file = new ClassPathResource("data/items.csv").getFile();

        List<Product> products = CSVReader.readCSVData(file.getPath());
        dataInsertService.insertData(products);

        productOrderProgram.start();
    }
}
