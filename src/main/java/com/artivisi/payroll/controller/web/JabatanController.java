package com.artivisi.payroll.controller.web;

import com.artivisi.payroll.dto.JabatanDto;
import com.artivisi.payroll.entity.Jabatan;
import com.artivisi.payroll.entity.Karyawan;
import com.artivisi.payroll.service.JabatanService;
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

@Controller @RequestMapping("/master/jabatan")
@Slf4j
public class JabatanController {
    @Autowired private JabatanService jabatanService;

    @GetMapping
    public String showList(@RequestParam(required = false) String name, ModelMap modelMap, @PageableDefault Pageable pageable){
        modelMap.addAttribute("name",name);
        modelMap.addAttribute("datas",jabatanService.getPageJabatan(name,pageable));
        return "master/jabatan/list";
    }

    @GetMapping("/form")
    public String showForm(@RequestParam(required = false) String id, ModelMap modelMap){
        modelMap.addAttribute("jabatan", StringUtils.hasText(id) ? jabatanService.getJabatan(id) : new Jabatan());
        return "master/jabatan/form";
    }

    @PostMapping("/form")
    public String processForm(@Valid Jabatan jabatan, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            log.error("JABATAN - BINDING RESULT ERROR : {}", bindingResult.getAllErrors());
            modelMap.addAttribute("jabatan", jabatan);
            return "master/jabatan/form";
        }

        try {
            jabatanService.saveJabatan(jabatan);
            redirectAttributes.addFlashAttribute("successMessage","Data is saved");
            return "redirect:/master/jabatan";
        }catch (Exception e){
            modelMap.addAttribute("errorMessage",e.getMessage());
            modelMap.addAttribute("jabatan", jabatan);
            return "master/jabatan/form";
        }
    }
}
