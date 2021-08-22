package com.artivisi.payroll.controller.web;

import com.artivisi.payroll.dao.KaryawanDao;
import com.artivisi.payroll.entity.GajiKaryawan;
import com.artivisi.payroll.service.GajiKaryawanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/master/gaji")
public class GajiKaryawanController {

    @Autowired private GajiKaryawanService gajiKaryawanService;
    @Autowired private KaryawanDao karyawanDao;

    @GetMapping
    public String showList(@RequestParam(required = false) String name, ModelMap modelMap, @PageableDefault Pageable pageable){
        modelMap.addAttribute("name",name);
        modelMap.addAttribute("datas",gajiKaryawanService.getPageGajiKaryawan(name,pageable));
        return "master/gaji_karyawan/list";
    }

    @GetMapping("/form")
    public String showForm(@RequestParam(required = false) String id, ModelMap modelMap){
        modelMap.addAttribute("gajiKaryawan", StringUtils.hasText(id) ? gajiKaryawanService.getGajiKaryawan(id).get() : new GajiKaryawan());
        modelMap.addAttribute("karyawans", karyawanDao.findAll());
        return "master/gaji_karyawan/form";
    }

    @PostMapping("/form")
    public String processForm(@Valid GajiKaryawan gajiKaryawan, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            log.error("GajiKaryawan - BINDING RESULT ERROR : {}", bindingResult.getAllErrors());
            modelMap.addAttribute("gajiKaryawan", gajiKaryawan);
            modelMap.addAttribute("karyawans", karyawanDao.findAll());
            return "master/gaji_karyawan/form";
        }

        try {
            gajiKaryawanService.saveGajiKaryawan(gajiKaryawan);
            redirectAttributes.addFlashAttribute("successMessage","Data is saved");
            return "redirect:/master/gaji";
        }catch (Exception e){
            modelMap.addAttribute("errorMessage",e.getMessage());
            modelMap.addAttribute("gajiKaryawan", gajiKaryawan);
            modelMap.addAttribute("karyawans", karyawanDao.findAll());
            return "master/gaji_karyawan/form";
        }
    }


}
