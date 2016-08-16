package com.controller.compare;

import com.services.compare.CompareServiceImpl;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * Created by user on 07.08.2016.
 */
@Controller
@RequestMapping("/compare")
public class CompareController {

    @Inject
    private CompareServiceImpl compareService;


    @RequestMapping(value = "/isflat/{flatId}",
            method = RequestMethod.GET)
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public @ResponseBody
    boolean isFlat(@PathVariable("flatId") long flatId) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return userName != null && compareService.isFlatId(userName, flatId);
    }

    @RequestMapping(value = "/save/{id}",
            method = RequestMethod.POST, consumes="application/json", produces="application/json",
            headers = {"Accept=text/xml, application/json"})
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public @ResponseBody
    boolean setCompareFlat(@PathVariable("id") long id) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return compareService.setCompareFlat(userName,id);
    }

    @RequestMapping(value = "/remove/{id}",
            method = RequestMethod.POST, consumes="application/json", produces="application/json",
            headers = {"Accept=text/xml, application/json"})
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public @ResponseBody
    boolean removeCompareFlat(@PathVariable("id") long id) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return compareService.removeCompareFlat(userName,id);
    }

}
