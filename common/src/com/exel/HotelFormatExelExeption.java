package com.exel;

/**
 * Created by user on 08.08.2016.
 */
public class HotelFormatExelExeption extends Exception {
    private String fildName = "Ошибка! Не правильно сформированы данные в exel поле:";
    private int sheet;

    public HotelFormatExelExeption(int sheet, String fildName) {
        this.fildName += fildName + " страница:" + sheet;
    }

    public String getFildName() {
        return fildName;
    }
}
