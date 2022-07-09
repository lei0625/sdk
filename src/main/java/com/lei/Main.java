package com.lei;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * @author lei
 * @create 2022-07-06-12:44 PM
 */
public class Main {

    public static void main(String[] args) throws UnsupportedEncodingException {


        Library library = new Library("http://localhost:8686/api/events", 2);
        library.fillParameters(1.23456f).fillParameters(6.54321f).fillParameters(System.currentTimeMillis()).fillParameters("test");
        library.log();


    }
}
