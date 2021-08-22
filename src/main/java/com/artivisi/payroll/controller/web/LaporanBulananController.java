package com.artivisi.payroll.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/laporan")
public class LaporanBulananController {

    @GetMapping
    public String showList(){
        return "laporan_bulanan/list";
    }

}
