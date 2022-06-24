package com.artivisi.payroll.controller.web;

import com.artivisi.payroll.entity.CutiKaryawan;
import com.artivisi.payroll.service.CutiService;
import com.artivisi.payroll.service.KaryawanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cuti_karyawan")
public class CutiKaryawanController {

    @Autowired private CutiService CutiKaryawanService;
    @Autowired private KaryawanService karyawanService;

    @GetMapping
    public String showList(ModelMap modelMap){
        modelMap.addAttribute("karyawans", karyawanService.getKaryawan());
        return "cuti_karyawan/list";
    }

    @GetMapping("/form")
    public String showFormKry(ModelMap modelMap, Authentication authentication){
        modelMap.addAttribute("karyawans", karyawanService.getKaryawan());
        modelMap.addAttribute("karyawan", karyawanService.getKaryawanByEmail(authentication.getName()).get());
        return "cuti_karyawan/form";
    }

    @PostMapping("/form")
    public String processCutiKaryawan(CutiKaryawan CutiKaryawan, RedirectAttributes redirectAttributes){
        try {
            CutiKaryawanService.save(CutiKaryawan);
            redirectAttributes.addFlashAttribute("successMessage","Data is saved");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("errorMessage",e.getMessage());
        }
        return "redirect:/cuti_karyawan";
    }

    @GetMapping("/delete")
    public String deleteCutiKaryawan(@RequestParam String id, RedirectAttributes redirectAttributes){
        try {
            CutiKaryawanService.delete(id);
            redirectAttributes.addFlashAttribute("successMessage","Data is deleted");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("errorMessage",e.getMessage());
        }
        return "redirect:/cuti_karyawan";
    }
}
