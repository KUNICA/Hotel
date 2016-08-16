package com.controller;

import com.entity.OfferEntity;
import com.entity.OperationType;
import com.entity.OperationsEntity;
import com.services.SaveOrUpdateObjectInputServiceImpl;
import com.services.account.IPersonalDataService;
import com.services.offer.OfferServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 09.07.2016.
 */
@Controller
public class HomeController {

    @Inject
    IPersonalDataService personalDataService;

    @Inject
    SaveOrUpdateObjectInputServiceImpl saveOrUpdateObjectInputServiceImpl;

    @Inject
    OfferServiceImpl offerServiceImpl;

    @RequestMapping("/home")
    public String showHome(Model model, HttpSession session) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            model.addAttribute("personalData",personalDataService.getPersonalData(userName)) ;
        }
        return "home";
    }

    @RequestMapping("/authorize")
    public void authorize(Model model, HttpServletResponse httpServletResponse) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            model.addAttribute("personalData",personalDataService.getPersonalData(userName)) ;
        }
         httpServletResponse.setHeader("Location", "/home");
    }

    @RequestMapping("/killSession")
    public String killSession(HttpServletRequest request, Model model, HttpSession session) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            Assert.notNull(request, "HttpServletRequest required");
            session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            SecurityContextHolder.clearContext();
        }
        return "home";
    }

    @RequestMapping("/home/remove")
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String remove() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        OfferEntity offer = (OfferEntity)offerServiceImpl.getOffer(userName);
        OperationsEntity operationOut = new OperationsEntity();
        operationOut.setDateOper(new Date());
        operationOut.setTypeOper(OperationType.OPERATION_OUT);
        operationOut.setUserName(userName);
        offer.setOperationOut(operationOut);
        saveOrUpdateObjectInputServiceImpl.inputObject(offer);
        return "home";
    }


    @RequestMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @RequestMapping("/registration")
    public String showRegistrationForm(Model model) {


        return "registerNewUser";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerSpitter( Model model) {

        return "registrationCompleted";
    }

}
