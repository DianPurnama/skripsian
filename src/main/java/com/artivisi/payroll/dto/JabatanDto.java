package com.artivisi.payroll.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class JabatanDto {
    private String idJabatan;
    private String nameJabatan;
    private String description;
    private BigDecimal gajiPokok;
    private BigDecimal uangMakan;
    private BigDecimal uangTransport;
}
