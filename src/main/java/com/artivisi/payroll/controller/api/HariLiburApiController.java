package com.artivisi.payroll.controller.api;

import com.artivisi.payroll.entity.HariLibur;
import com.artivisi.payroll.service.HariLiburService;
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
@RequestMapping("/api/hari_libur")
public class HariLiburApiController {

    @Autowired private HariLiburService hariLiburService;

    @GetMapping
    public List<HariLibur> getHariLibur(@RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate){
        if(StringUtils.hasText(startDate) && StringUtils.hasText(endDate)){
            return hariLiburService.getHariLibur(startDate,endDate);
        }else{
            LocalDate start = YearMonth.now().atDay(1);
            LocalDate end   = YearMonth.now().atEndOfMonth();
            return hariLiburService.getHariLibur(start, end);
        }
    }

}
