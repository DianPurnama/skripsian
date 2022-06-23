package com.artivisi.payroll.controller.web;

import com.artivisi.payroll.entity.Karyawan;
import com.artivisi.payroll.service.KaryawanService;
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
@RequestMapping("/master/karyawan")
public class KaryawanController {

    @Autowired private KaryawanService karyawanService;

    @GetMapping
    public String showList(@RequestParam(required = false) String name, ModelMap modelMap, @PageableDefault Pageable pageable){
        modelMap.addAttribute("name",name);
        modelMap.addAttribute("datas",karyawanService.getPageKaryawan(name,pageable));
        return "master/karyawan/list";
    }

    @GetMapping("/form")
    public String showForm(@RequestParam(required = false) String id, ModelMap modelMap){
        modelMap.addAttribute("karyawan", StringUtils.hasText(id) ? karyawanService.getKaryawan(id).get() : new Karyawan());
        return "master/karyawan/form";
    }

    @PostMapping("/form")
    public String processForm(@Valid Karyawan karyawan, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            log.error("KARYAWAN - BINDING RESULT ERROR : {}", bindingResult.getAllErrors());
            modelMap.addAttribute("karyawan", karyawan);
            return "master/karyawan/form";
        }

        try {
            karyawanService.saveKaryawan(karyawan);
            redirectAttributes.addFlashAttribute("successMessage","Data is saved");
            return "redirect:/master/karyawan";
        }catch (Exception e){
            modelMap.addAttribute("errorMessage",e.getMessage());
            modelMap.addAttribute("karyawan", karyawan);
            return "master/karyawan/form";
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam String id, RedirectAttributes redirectAttributes, ModelMap mm) {
        try {
            karyawanService.deleteKaryawan(id);
            redirectAttributes.addFlashAttribute("successMessage", "Data telah dihapus" );
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",e.getMessage());
        }
        return "redirect:/master/karyawan";
    }

}
