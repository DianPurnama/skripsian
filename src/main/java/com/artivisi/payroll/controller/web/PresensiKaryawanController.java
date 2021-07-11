package com.artivisi.payroll.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/presensi_karyawan")
public class PresensiKaryawanController {

    @GetMapping
    public String showCalendar(){return "presensi_karyawan/list";}

    @PostMapping("/upload")
    public String processUpload(){return "redirect:/presensi_karyawan";}
}
