/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oos.store.utils;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Oluwaseun_Smart
 */
public class DateUtil {

    public static String getCurrentDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:dd");
        String format = formatter.format(localDateTime);
        return format;
    }
    
     public static String getCurrentDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String format = formatter.format(localDateTime);
        return format;
    }

    public static String getCurrentMonOfSell() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Month month = localDateTime.getMonth();
        return month + "";
    }

    public static String getCurrentYearOfSell() {
        LocalDateTime localDateTime = LocalDateTime.now();
        int year = localDateTime.getYear();
        return year + "";
    }

    public static String getCurrentYearAndMonOfSell() {
        LocalDateTime localDateTime = LocalDateTime.now();
        int year = localDateTime.getYear();
        Month month = localDateTime.getMonth();
        return month + " - " + year;
    }
}
