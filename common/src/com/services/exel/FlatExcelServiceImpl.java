package com.services.exel;

import com.exel.HotelFormatExelExeption;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by user on 09.08.2016.
 */
public interface FlatExcelServiceImpl {
    boolean addFlat(InputStream inputStream,String userName) throws InvalidFormatException,InvocationTargetException, IllegalArgumentException,HotelFormatExelExeption, IOException;
}
