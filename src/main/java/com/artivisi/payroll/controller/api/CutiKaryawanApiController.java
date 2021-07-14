package com.artivisi.payroll.controller.api;

import com.artivisi.payroll.entity.CutiKaryawan;
import com.artivisi.payroll.service.CutiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/api/cuti_karyawan")
public class CutiKaryawanApiController {

    @Autowired private CutiService CutiKaryawanService;

    @GetMapping
    public List<CutiKaryawan> getCutiKaryawan(@RequestParam(required = false) String startDate,
                                              @RequestParam(required = false) String endDate,
                                              @RequestParam(required = false) String idKaryawan){
        if(StringUtils.hasText(startDate) && StringUtils.hasText(endDate)){
            return CutiKaryawanService.getCutiKaryawan(startDate,endDate, idKaryawan);
        }else{
            LocalDate start = YearMonth.now().atDay(1);
            LocalDate end   = YearMonth.now().atEndOfMonth();
            return CutiKaryawanService.getCutiKaryawan(start, end, idKaryawan);
        }
    }

}
