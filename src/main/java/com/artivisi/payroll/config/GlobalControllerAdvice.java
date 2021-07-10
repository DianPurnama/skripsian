package com.artivisi.payroll.config;

import com.artivisi.payroll.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@ControllerAdvice(basePackages = {"com.artivisi.payroll.controller.web"})
public class GlobalControllerAdvice {

    @Autowired private UserDao userDao;

    @ModelAttribute
    public void setLoggedUser(Principal principal, ModelMap modelMap){
        if(principal != null){
            modelMap.addAttribute("loggedUser",userDao.findByUsername(principal.getName()).get());
        }
    }

}
