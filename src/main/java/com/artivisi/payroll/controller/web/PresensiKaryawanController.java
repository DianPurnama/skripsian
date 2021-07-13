package com.artivisi.payroll.controller.web;

import com.artivisi.payroll.entity.HariLibur;
import com.artivisi.payroll.entity.Presensi;
import com.artivisi.payroll.service.KaryawanService;
import com.artivisi.payroll.service.PresensiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/presensi_karyawan")
public class PresensiKaryawanController {

    @Autowired private KaryawanService karyawanService;
    @Autowired private PresensiService presensiService;

    @GetMapping
    public String showCalendar(ModelMap modelMap){
        modelMap.addAttribute("karyawans", karyawanService.getKaryawan());
        return "presensi_karyawan/list";
    }

    @PostMapping("/form")
    public String processHariLibur(Presensi presensi, RedirectAttributes redirectAttributes){
        try {
            presensiService.save(presensi);
            redirectAttributes.addFlashAttribute("successMessage","Data is saved");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("errorMessage",e.getMessage());
        }
        return "redirect:/presensi_karyawan";
    }

    @PostMapping("/upload")
    public String processUpload(MultipartFile file, RedirectAttributes redirectAttributes){
        try {
            presensiService.processUpload(file);
            redirectAttributes.addFlashAttribute("successMessage","Data is uploaded and saved");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("errorMessage",e.getMessage());
        }
        return "redirect:/presensi_karyawan";
    }
}
