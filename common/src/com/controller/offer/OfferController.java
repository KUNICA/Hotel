package com.controller.offer;

import com.entity.OfferEntity;
import com.entity.OperationType;
import com.entity.OperationsEntity;
import com.services.SaveOrUpdateObjectInputServiceImpl;
import com.services.account.IPersonalDataService;
import com.services.alert.AlertServiceImpl;
import com.services.offer.OfferServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 12.08.2016.
 */
@Controller
@RequestMapping(value="/offer")
public class OfferController {
    @Inject
    private OfferServiceImpl offerService;

    @Inject
    protected SaveOrUpdateObjectInputServiceImpl saveOrUpdateObjectInputService;

    @Inject
    IPersonalDataService personalDataService;

    @Qualifier("alertService")
    @Autowired
    private AlertServiceImpl alertService;

    @RequestMapping(value="/create")
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String createOffer(Model model, HttpSession session) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        OfferEntity offer = (OfferEntity)offerService.create(userName);
        saveOrUpdateObjectInputService.inputObject(offer);
        List flats = offerService.getFlats(offer.getShoppingCart());
        model.addAttribute("listOffer",flats);
        model.addAttribute("totalPrice",offer.getOrderPrice());
        alertService.sendAlert(offer);
        model.addAttribute("personalData",personalDataService.getPersonalData(userName));
        return "offer";
    }

    @RequestMapping(value="/print")
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String getOffer(Model model, HttpSession session) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        OfferEntity offer = (OfferEntity)offerService.getOffer(userName);
        List flats = offerService.getFlats(offer.getShoppingCart());
        model.addAttribute("listOffer",flats);
        model.addAttribute("totalPrice",offer.getOrderPrice());
        model.addAttribute("personalData",personalDataService.getPersonalData(userName)) ;
        return "offer";
    }

    @RequestMapping(value="/isOffer", method = RequestMethod.POST)
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    @ResponseBody
    public Boolean getOfferActual() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        OfferEntity offer = (OfferEntity)offerService.getOffer(userName);
        return offer==null;
    }

}
