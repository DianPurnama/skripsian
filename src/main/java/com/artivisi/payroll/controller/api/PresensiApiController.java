package com.artivisi.payroll.controller.api;

import com.artivisi.payroll.entity.Presensi;
import com.artivisi.payroll.service.PresensiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/api/presensi")
public class PresensiApiController {

    @Autowired private PresensiService presensiService;

    @GetMapping
    public List<Presensi> getPresensi(@RequestParam(required = false) String startDate,
                                      @RequestParam(required = false) String endDate,
                                      @RequestParam(required = false) String idKaryawan){
        if(StringUtils.hasText(startDate) && StringUtils.hasText(endDate)){
            if(StringUtils.hasText(idKaryawan)){
                return presensiService.getPresensi(startDate,endDate,idKaryawan);
            }else{
                return presensiService.getPresensi(startDate,endDate,null);
            }
        }else{
            LocalDate start = YearMonth.now().atDay(1);
            LocalDate end   = YearMonth.now().atEndOfMonth();

            if(StringUtils.hasText(idKaryawan)){
                return presensiService.getPresensi(start,end,idKaryawan);
            }else{
                return presensiService.getPresensi(start,end,null);
            }
        }
    }

    @PostMapping("/form")
    public void processForm(Presensi presensi, RedirectAttributes redirectAttributes){

    }

}
