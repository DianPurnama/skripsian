package com.artivisi.payroll.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hari_libur")
public class HariLiburController {

    @GetMapping
    public String showList(){return "hari_libur/list";}

}
