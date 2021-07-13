package com.artivisi.payroll.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SlipGajiSearchDto {

    @NotNull private Integer month;
    @NotNull private Integer year;

}
