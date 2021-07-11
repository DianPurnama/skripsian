package com.artivisi.payroll.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/slip_gaji")
public class SlipGajiController {

    @GetMapping
    public String showForm(){return "slip_gaji/form";}

}
