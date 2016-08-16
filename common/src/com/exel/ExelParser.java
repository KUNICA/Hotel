package com.exel;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.google.common.base.Strings;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import javax.inject.Named;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by user on 08.08.2016.
 */
@Named
@Component
public class ExelParser implements ExelParserImpl{

    private static final int ID_ROW_NUMBER = 0; //Наименование
    private static final int NAME_ROW_NUMBER = 1; //Наименование
    private static final int ADDRESS_ROW_CITY = 2; //Город
    private static final int STREET_ROW_NUMBER = 3; //Улица
    private static final int HOUSE_ROW_NUMBER = 4; //дом
    private static final int HOUSING_ROW_NUMBER = 5; //корпус
    private static final int APARTMENT_ROW_NUMBER = 6; //квартира
    private static final int SLEEPS_ROW_NUMBER = 7; //колличество персон
    private static final int COUNT_ROOMS_ROW_NUMBER = 8; //колличество комнат
    private static final int COUNT_FLOORS_ROW_NUMBER = 9; //колличество этажей
    private static final int COUNT_BATHROOMS_ROW_NUMBER = 10; //колличество ванных комнат
    private static final int PRICE_ROW_NUMBER = 11; //цена
    private static final int DESCRIPTION_ROW_NUMBER = 12; //описание

    private static final String ID_ROW_NUMBER_HEADER = "Идентификатор"; //Наименование
    private static final String NAME_ROW_NUMBER_HEADER = "Наименование"; //Наименование
    private static final String ADDRESS_ROW_CITY_HEADER  = "Город"; //Город
    private static final String STREET_ROW_NUMBER_HEADER  = "Улица"; //Улица
    private static final String HOUSE_ROW_NUMBER_HEADER  = "Дом"; //дом
    private static final String HOUSING_ROW_NUMBER_HEADER  = "Корпус"; //корпус
    private static final String APARTMENT_ROW_NUMBER_HEADER  = "Квартира"; //квартира
    private static final String SLEEPS_ROW_NUMBER_HEADER  = "Колличество людей"; //колличество персон
    private static final String COUNT_ROOMS_ROW_NUMBER_HEADER  = "Колличество комнат"; //колличество комнат
    private static final String COUNT_FLOORS_ROW_NUMBER_HEADER  = "Колличество этажей"; //колличество этажей
    private static final String COUNT_BATHROOMS_ROW_NUMBER_HEADER  = "Колличество ванных комнат"; //колличество ванных комнат
    private static final String PRICE_ROW_NUMBER_HEADER  = "Цена"; //цена
    private static final String DESCRIPTION_ROW_NUMBER_HEADER  = "Описание"; //описание


    private static final String PHOTO_ROW = "photo"; //фотографии
    private static final String VIDEO_ROW = "video"; //видео

    private static final int NAME_COLUMN_FIELD = 0; //наименование поля
    private static final int DATA_COLUMN_FIELD = 1; //данные поля




    public List getHotels(InputStream inputStream) throws InvalidFormatException, HotelFormatExelExeption {
        XSSFWorkbook workBook = null; // Получаем workbook
        POIFSFileSystem fileSystem = null; //Открываем документу
        List<HotelExel> hotels = new ArrayList<HotelExel>();
        try {
            //fileSystem = new POIFSFileSystem(inputStream);
            workBook = new XSSFWorkbook(inputStream);

            for(int i=0; i< workBook.getNumberOfSheets();i++){

                HotelExel  hotel = new HotelExel();

        XSSFSheet sheet = workBook.getSheetAt(i); // Проверяем страницу

        Iterator<Row> rows = sheet.rowIterator(); // Перебираем все строки

        // Перебираем все строки начиная со второй до тех пор, пока документ не закончитс
            int rowIter = 0;
        while (rows.hasNext()) {
            XSSFRow row = (XSSFRow) rows.next();
            //Получаем ячейки из строки по номерам столбцов
            XSSFCell nameFieldXSSFCell = row.getCell(NAME_COLUMN_FIELD);
            String nameField = nameFieldXSSFCell.getStringCellValue();
            XSSFCell dataFieldXSSFCell = row.getCell(DATA_COLUMN_FIELD);
            DataFormatter formatter = new DataFormatter();
            String dataField = formatter.formatCellValue(dataFieldXSSFCell);

            switch(rowIter){
                case ID_ROW_NUMBER:
                    if(!Strings.isNullOrEmpty(nameField) && nameField.contains(ID_ROW_NUMBER_HEADER)) {
                        hotel.setId(dataField);
                    }
                    break;
                case NAME_ROW_NUMBER:
                    if(!Strings.isNullOrEmpty(nameField) && nameField.contains(NAME_ROW_NUMBER_HEADER)) {
                        hotel.setName(dataField);
                    }else{
                        throw new HotelFormatExelExeption(i,NAME_ROW_NUMBER_HEADER);
                    }
                    break;
                case ADDRESS_ROW_CITY:
                    if(!Strings.isNullOrEmpty(nameField) && nameField.contains(ADDRESS_ROW_CITY_HEADER)) {
                        hotel.setCity(dataField);
                    }else{
                        throw new HotelFormatExelExeption(i,ADDRESS_ROW_CITY_HEADER);
                    }
                    break;
                case STREET_ROW_NUMBER:
                    if(!Strings.isNullOrEmpty(nameField) && nameField.contains(STREET_ROW_NUMBER_HEADER)) {
                        hotel.setStreet(dataField);
                    }else{
                        throw new HotelFormatExelExeption(i,STREET_ROW_NUMBER_HEADER);
                    }
                    break;
                case HOUSE_ROW_NUMBER:
                    if(!Strings.isNullOrEmpty(nameField) && nameField.contains(HOUSE_ROW_NUMBER_HEADER)) {
                        hotel.setHouse(dataField);
                    }else{
                        throw new HotelFormatExelExeption(i,HOUSE_ROW_NUMBER_HEADER);
                    }
                    break;
                case HOUSING_ROW_NUMBER:
                    if(!Strings.isNullOrEmpty(nameField) && nameField.contains(HOUSING_ROW_NUMBER_HEADER)) {
                        hotel.setHousing(dataField);
                    }else{
                        throw new HotelFormatExelExeption(i,HOUSING_ROW_NUMBER_HEADER);
                    }
                    break;
                case APARTMENT_ROW_NUMBER:
                    if(!Strings.isNullOrEmpty(nameField) && nameField.contains(APARTMENT_ROW_NUMBER_HEADER)) {
                        hotel.setApartment(dataField);
                    }else{
                        throw new HotelFormatExelExeption(i,APARTMENT_ROW_NUMBER_HEADER);
                    }
                    break;
                case SLEEPS_ROW_NUMBER:
                    if(!Strings.isNullOrEmpty(nameField) && nameField.contains(SLEEPS_ROW_NUMBER_HEADER)) {
                        hotel.setSleeps(dataField);
                    }else{
                        throw new HotelFormatExelExeption(i,SLEEPS_ROW_NUMBER_HEADER);
                    }
                    break;
                case COUNT_ROOMS_ROW_NUMBER:
                    if(!Strings.isNullOrEmpty(nameField) && nameField.contains(COUNT_ROOMS_ROW_NUMBER_HEADER)) {
                        hotel.setCountRooms(dataField);
                    }else{
                        throw new HotelFormatExelExeption(i,COUNT_ROOMS_ROW_NUMBER_HEADER);
                    }
                    break;
                case COUNT_FLOORS_ROW_NUMBER:
                    if(!Strings.isNullOrEmpty(nameField) && nameField.contains(COUNT_FLOORS_ROW_NUMBER_HEADER)) {
                        hotel.setCountFloors(dataField);
                    }else{
                        throw new HotelFormatExelExeption(i,COUNT_FLOORS_ROW_NUMBER_HEADER);
                    }
                    break;
                case COUNT_BATHROOMS_ROW_NUMBER:
                    if(!Strings.isNullOrEmpty(nameField) && nameField.contains(COUNT_BATHROOMS_ROW_NUMBER_HEADER)) {
                        hotel.setCountBathrooms(dataField);
                    }else{
                        throw new HotelFormatExelExeption(i,COUNT_BATHROOMS_ROW_NUMBER_HEADER);
                    }
                    break;
                case PRICE_ROW_NUMBER:
                    if(!Strings.isNullOrEmpty(nameField) && nameField.contains(PRICE_ROW_NUMBER_HEADER)) {
                        hotel.setPrice(dataField);
                    }else{
                        throw new HotelFormatExelExeption(i,PRICE_ROW_NUMBER_HEADER);
                    }
                    break;
                case DESCRIPTION_ROW_NUMBER:
                    if(!Strings.isNullOrEmpty(nameField) && nameField.contains(DESCRIPTION_ROW_NUMBER_HEADER)) {
                        hotel.setShortDescription(dataField);
                    }else{
                        throw new HotelFormatExelExeption(i,DESCRIPTION_ROW_NUMBER_HEADER);
                    }
                    break;
                default:
                    // Считываем фото и видио
                    if(!Strings.isNullOrEmpty(nameField) && nameField.contains(PHOTO_ROW)) {
                        hotel.getPhotos().put(nameField,dataField);
                    }else if(nameField.contains(VIDEO_ROW)){
                        hotel.getVideos().put(nameField,dataField);
                    }

                break;
            }
            rowIter++;
        }
                hotels.add(hotel);
            }
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return hotels;
    }

}
