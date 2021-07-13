package com.artivisi.payroll.dto;

import lombok.Data;

@Data
public class PresensiDto {
    
    private String fingerPrintId;
    private String tanggalAbsen;
    private String waktuAbsen;

}
