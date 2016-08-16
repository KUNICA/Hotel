package com.controller.admin;

import com.exel.HotelFormatExelExeption;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.services.SaveOrUpdateObjectInputServiceImpl;
import com.services.account.IPersonalDataService;
import com.services.admin.FlatServiceImpl;
import com.services.admin.ImageFileServiceImpl;
import com.services.admin.ImageUploadException;
import com.services.exel.FlatExcelServiceImpl;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by user on 08.08.2016.
 */
@Controller
@RequestMapping(value="/admin")
public class AdminController {

    private static final String fileUploadControllerURL = "upload";
    private static final String fileSaveControllerURL = "save";

    @Inject
     FlatExcelServiceImpl flatExelService;

    @Inject
    private FlatServiceImpl flatServiceImpl;

    @Inject
    private ImageFileServiceImpl imageFileServiceImpl;

    @Inject
    IPersonalDataService personalDataService;


    @RequestMapping(value="/run")
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String admin(Model model, HttpSession session) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("fileUploadControllerURL", fileUploadControllerURL);
        model.addAttribute("fileSaveControllerURL", fileSaveControllerURL);
        model.addAttribute("tableInformation",flatServiceImpl.getFlats(userName));
        model.addAttribute("personalData",personalDataService.getPersonalData(userName)) ;
        return "admin";
    }

    @RequestMapping(value="/upload", method = RequestMethod.POST)
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String addHotel(@RequestParam("file") MultipartFile file, Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("tableInformation",flatServiceImpl.getFlats(userName));
        model.addAttribute("fileUploadControllerURL", fileUploadControllerURL);
        model.addAttribute("fileSaveControllerURL", fileSaveControllerURL);
        model.addAttribute("personalData",personalDataService.getPersonalData(userName)) ;
        if(file.isEmpty()){
            model.addAttribute("error","Выберите файл");
            return "admin";
        }
            try {
                flatExelService.addFlat(file.getInputStream(),userName);
            } catch (HotelFormatExelExeption hotelFormatExelExeption) {
                model.addAttribute("error",hotelFormatExelExeption.getFildName());
                return "admin";
            } catch (InvocationTargetException | IOException | NullPointerException e) {
                model.addAttribute("error",e.fillInStackTrace());
                return "admin";
            }
        model.addAttribute("error","Файл добавлен");
        return "redirect:/admin/run";
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String saveImage(@RequestParam("fileImage") MultipartFile file, Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("fileUploadControllerURL", fileUploadControllerURL);
        model.addAttribute("fileSaveControllerURL", fileSaveControllerURL);
        model.addAttribute("tableInformation",flatServiceImpl.getFlats(userName));
        model.addAttribute("personalData",personalDataService.getPersonalData(userName)) ;
        if(file.isEmpty()){
            model.addAttribute("errorImage","Выберите файл");
            return "admin";
        }
        try {
            if(!file.isEmpty()) {
                imageFileServiceImpl.validateImage(file);
                imageFileServiceImpl.saveFileImage(file.getOriginalFilename(),file); // Сохранить файл
            }
        } catch (ImageUploadException e) {
            model.addAttribute("errorImage",e.fillInStackTrace());
            return "admin";
        }
        model.addAttribute("errorImage","Файл добавлен");
        return "admin";
    }

    @RequestMapping(value="/remove/{flatId}",method = RequestMethod.POST)
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String remove(@PathVariable("flatId") long flatId,Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        flatServiceImpl.removeFlat(flatId,userName);
        model.addAttribute("fileUploadControllerURL", fileUploadControllerURL);
        model.addAttribute("fileSaveControllerURL", fileSaveControllerURL);
        model.addAttribute("tableInformation",flatServiceImpl.getFlats(userName));
        model.addAttribute("personalData",personalDataService.getPersonalData(userName)) ;
        return "admin";
    }

    @RequestMapping(value="/flats")
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String showHome( Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("tableInformation",flatServiceImpl.getFlats(userName));
        model.addAttribute("personalData",personalDataService.getPersonalData(userName)) ;
        return "admin";
    }
}
