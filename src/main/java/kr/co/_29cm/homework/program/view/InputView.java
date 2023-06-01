package kr.co._29cm.homework.program.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputView {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final String START_LINE_MESSAGE = "입력(o[order]: 주문, q[quit]: 종료) : ";
    private static final String PRODUCT_NO_MESSAGE = "상품번호 : ";
    private static final String STOCK_MESSAGE = "수량 : ";

    public static String inputOrderOrQuit() {
        System.out.print(START_LINE_MESSAGE);
        return getInput().toLowerCase().trim();
    }

    public static String inputProductNo() {
        System.out.print(PRODUCT_NO_MESSAGE);
        String input = getInput();
        if (" ".equals(input)) {
            return "next";
        }

        return input.toLowerCase().trim();
    }

    public static String  inputQuantity() {
        System.out.print(STOCK_MESSAGE);
        String input = getInput();
        if (" ".equals(input)) {
            return "next";
        }

        return input.toLowerCase().trim();
    }

    private static String getInput() {
        String input = null;
        try {
            input = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return input;
    }
}
