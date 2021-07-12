package com.artivisi.payroll.controller.web;

import com.artivisi.payroll.entity.HariLibur;
import com.artivisi.payroll.service.HariLiburService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/hari_libur")
public class HariLiburController {

    @Autowired private HariLiburService hariLiburService;

    @GetMapping
    public String showList(){return "hari_libur/list";}

    @PostMapping("/form")
    public String processHariLibur(HariLibur hariLibur, RedirectAttributes redirectAttributes){
        try {
            hariLiburService.save(hariLibur);
            redirectAttributes.addFlashAttribute("successMessage","Data is saved");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("errorMessage",e.getCause().getMessage());
        }
        return "redirect:/hari_libur";
    }

    @GetMapping("/delete")
    public String deletehariLibur(@RequestParam String id, RedirectAttributes redirectAttributes){
        try {
            hariLiburService.delete(id);
            redirectAttributes.addFlashAttribute("successMessage","Data is deleted");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("errorMessage",e.getCause().getMessage());
        }
        return "redirect:/hari_libur";
    }
}
