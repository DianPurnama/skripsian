package com.artivisi.payroll.controller.web;

import com.artivisi.payroll.dto.SlipGajiSearchDto;
import com.artivisi.payroll.service.SlipGajiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.Month;

@Controller
@RequestMapping("/slip_gaji")
public class SlipGajiController {

    @Autowired private SlipGajiService slipGajiService;

    @GetMapping
    public String showForm(SlipGajiSearchDto params,
                           ModelMap modelMap){
        if(params.getMonth() != null && params.getYear() != null){
            modelMap.addAttribute("datas",slipGajiService.getSlipGaji(params.getMonth(),params.getYear()));
        }else{
            params.setMonth(LocalDate.now().getMonthValue());
            params.setYear(LocalDate.now().getYear());
        }
        modelMap.addAttribute("params",params);
        return "slip_gaji/list";
    }

    @GetMapping("/generate")
    public String generateSlipGaji(SlipGajiSearchDto params,
                                   RedirectAttributes redirectAttributes){

        try {
            slipGajiService.generateSlipGajis(params.getMonth(),params.getYear());
            redirectAttributes.addFlashAttribute("successMessage","Slip Gaji "+ Month.of(params.getMonth()).name() +" "+params.getYear()+" berhasil digenerate");
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage",e.getMessage());
        }

        redirectAttributes.addAttribute("month",params.getMonth());
        redirectAttributes.addAttribute("year",params.getYear());
        return "redirect:/slip_gaji";
    }

    @GetMapping("/pdf")
    public String exportPdf(@RequestParam String id, ModelMap modelMap){
        modelMap.addAttribute("slipGaji", slipGajiService.getSlipgaji(id));
        return "slip_gaji/slip_gaji_pdf";
    }
}
