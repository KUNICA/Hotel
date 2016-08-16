package com.exel;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import java.io.InputStream;
import java.util.List;

/**
 * Created by user on 08.08.2016.
 */
public interface ExelParserImpl  {
    List getHotels(InputStream inputStream) throws InvalidFormatException, HotelFormatExelExeption;
}
