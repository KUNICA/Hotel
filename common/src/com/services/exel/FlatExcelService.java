package com.services.exel;

        import com.dao.flats.FlatDaoImpl;
        import com.entity.*;
        import com.exel.ExelParserImpl;
        import com.exel.HotelExel;
        import com.exel.HotelFormatExelExeption;
        import com.fasterxml.jackson.databind.exc.InvalidFormatException;
        import com.google.common.base.Strings;
        import com.services.SaveOrUpdateObjectInputServiceImpl;
        import com.services.admin.RemoveServiceImpl;

        import javax.inject.Inject;
        import javax.inject.Named;
        import java.io.IOException;
        import java.io.InputStream;
        import java.lang.reflect.InvocationTargetException;
        import java.util.ArrayList;
        import java.util.Date;
        import java.util.List;
        import java.util.Map;

/**
 * Created by user on 09.08.2016.
 */
@Named
public class FlatExcelService implements FlatExcelServiceImpl {

    @Inject
    protected ExelParserImpl exelParser;

    @Inject
    protected SaveOrUpdateObjectInputServiceImpl saveOrUpdateObjectInputService;

    @Inject
    protected FlatDaoImpl flatDao;

    @Inject
    protected RemoveServiceImpl removeService;


    public boolean addFlat(InputStream inputStream,String userName) throws NumberFormatException, InvalidFormatException, NullPointerException,IOException,InvocationTargetException, IllegalArgumentException, HotelFormatExelExeption {
        List hotels = exelParser.getHotels(inputStream);
        List flats = getFlat(hotels,userName);
        saveOrUpdateObjectInputService.inputObject(flats.get(0)); //одну страницу
        return true;
    }

    private List getFlat(List hotels,String userName)throws NumberFormatException, NullPointerException,InvocationTargetException, IllegalArgumentException,IOException{
        ArrayList<FlatsEntity> flats = new ArrayList<FlatsEntity>();
        for (Object it: hotels) {
            HotelExel hotel = (HotelExel) it;
            Long id = !Strings.isNullOrEmpty(hotel.getId()) ? Long.parseLong(hotel.getId()) : null;
            FlatsEntity flat = (id!=null)? updateInit(id,hotel,userName) : saveInit(hotel,userName);
            flats.add(flat);
        }
        return flats;
    }

    private FlatsEntity updateInit(Long id,HotelExel hotel,String userName) throws NumberFormatException, NullPointerException, InvocationTargetException, IllegalArgumentException,IOException {
        FlatsEntity flatsEntity = (FlatsEntity)flatDao.getFlat(id);
        initFlat(flatsEntity,hotel);
        initAdress(flatsEntity.getAdress(),hotel);
        removeService.remove(flatsEntity,userName);
        flatsEntity.setOperationOut(null);
        setPhotos(flatsEntity,hotel,userName);
        setVideos(flatsEntity,hotel,userName);
        return flatsEntity;
    }

    private FlatsEntity saveInit(HotelExel hotel,String userName) throws NumberFormatException,NullPointerException,InvocationTargetException, IllegalArgumentException,IOException{
        FlatsEntity flatsEntity = setFlatParametrs(hotel,userName);
        flatsEntity.setAdress(getAdress(hotel,userName));
        setPhotos(flatsEntity,hotel,userName);
        setVideos(flatsEntity,hotel,userName);
        return flatsEntity;
    }

    private void setVideos(FlatsEntity flatsEntity,HotelExel hotel,String userName){
        int i=0;
        for(Map.Entry<String, String> e : hotel.getVideos().entrySet()) {
            VideoEntity videoEntity = new VideoEntity();
            videoEntity.setLink(e.getValue());
            videoEntity.setDescription(e.getKey());

            OperationsEntity operationIn = new OperationsEntity();
            operationIn.setDateOper(new Date());
            operationIn.setTypeOper(OperationType.OPERATION_IN);
            operationIn.setUserName(userName);
            videoEntity.setOperationIn(operationIn);

            flatsEntity.getVideos().add(videoEntity);
            i++;
        }

    }

    private void setPhotos(FlatsEntity flatsEntity,HotelExel hotel,String userName){
        int i=0;
        for(Map.Entry<String, String> e : hotel.getPhotos().entrySet()) {
            ImagesEntity imagesEntity = new ImagesEntity();
            imagesEntity.setImg(e.getValue());
            imagesEntity.setDescription(e.getKey());
            if(i==0){
                imagesEntity.setMain(true);
            }

            OperationsEntity operationIn = new OperationsEntity();
            operationIn.setDateOper(new Date());
            operationIn.setTypeOper(OperationType.OPERATION_IN);
            operationIn.setUserName(userName);
            imagesEntity.setOperationIn(operationIn);

            flatsEntity.getPhotos().add(imagesEntity);
            i++;
        }
    }

    private void initFlat(FlatsEntity flatsEntity,HotelExel hotel) throws NumberFormatException{
        if(!Strings.isNullOrEmpty(hotel.getName())){
            flatsEntity.setNameImage(hotel.getName());
        }
        if(!Strings.isNullOrEmpty(hotel.getSleeps())){
            flatsEntity.setSlleeps(Long.parseLong(hotel.getSleeps()));
        }
        if(!Strings.isNullOrEmpty(hotel.getCountRooms())){
            flatsEntity.setCountRoums(Long.parseLong(hotel.getCountRooms()));
        }
        if(!Strings.isNullOrEmpty(hotel.getCountFloors())){
            flatsEntity.setCountFloors(Long.parseLong(hotel.getCountFloors()));
        }
        if(!Strings.isNullOrEmpty(hotel.getCountBathrooms())){
            flatsEntity.setBathrooms(Long.parseLong(hotel.getCountBathrooms()));
        }
        if(!Strings.isNullOrEmpty(hotel.getPrice())){
            flatsEntity.setPrice(Double.parseDouble(hotel.getPrice()));
        }
        if(!Strings.isNullOrEmpty(hotel.getShortDescription())){
            flatsEntity.setShortDescription(hotel.getShortDescription());
        }
    }

    private FlatsEntity setFlatParametrs(HotelExel hotel,String userName) throws NumberFormatException{
        FlatsEntity flatsEntity = new FlatsEntity();
        initFlat(flatsEntity,hotel);
        OperationsEntity operationIn = new OperationsEntity();
        operationIn.setDateOper(new Date());
        operationIn.setTypeOper(OperationType.OPERATION_IN);
        operationIn.setUserName(userName);
        flatsEntity.setOperationIn(operationIn);
        return flatsEntity;
    }

    private void initAdress(AdressEntity adressEntity,HotelExel hotel) throws NumberFormatException{
           String remark = "";
           if (!Strings.isNullOrEmpty(hotel.getCity())) {
               adressEntity.setCity(hotel.getCity());
               remark += hotel.getCity();
           }
           if (!Strings.isNullOrEmpty(hotel.getStreet())) {
               adressEntity.setStreet(hotel.getStreet());
               remark += remark.equals("") ? hotel.getStreet() : " ,улица " + hotel.getStreet();
           }
           if (!Strings.isNullOrEmpty(hotel.getHouse())) {
               adressEntity.setBuildingNum(Integer.parseInt(hotel.getHouse()));
               remark += remark.equals("") ? hotel.getHouse() : " , дом " + hotel.getHouse();
           }
           if (!Strings.isNullOrEmpty(hotel.getHousing())) {
               adressEntity.setСorpNum(Integer.parseInt(hotel.getHousing()));
               remark += remark.equals("") ? hotel.getHousing() : " , корпус " + hotel.getHousing();
           }
           if (!Strings.isNullOrEmpty(hotel.getApartment())) {
               adressEntity.setFlatNum(Integer.parseInt(hotel.getApartment()));
               remark += remark.equals("") ? hotel.getApartment() : " ,квартира " + hotel.getApartment();
           }
           adressEntity.setRemark(remark);
    }

    private AdressEntity getAdress(HotelExel hotel, String userName) throws NumberFormatException{
        AdressEntity adressEntity = new AdressEntity();
        initAdress(adressEntity,hotel);
        OperationsEntity operationAdressIn = new OperationsEntity();
        operationAdressIn.setDateOper(new Date());
        operationAdressIn.setTypeOper(OperationType.OPERATION_IN);
        operationAdressIn.setUserName(userName);
        adressEntity.setOperationIn(operationAdressIn);
        return adressEntity;
    }

}



