package kr.co._29cm.homework.product.csv;

import kr.co._29cm.homework.product.Product;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static List<Product> readCSVData(String filePath) {
        List<Product> products = new ArrayList<>();
        try (Reader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            for (CSVRecord csvRecord : csvParser) {
                String productCode = csvRecord.get("상품번호");
                String productName = csvRecord.get("상품명");
                int price = Integer.parseInt(csvRecord.get("판매가격"));
                int stockQuantity = Integer.parseInt(csvRecord.get("재고수량"));

                Product product = Product.createProduct(productCode, productName, price, stockQuantity);
                products.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

}