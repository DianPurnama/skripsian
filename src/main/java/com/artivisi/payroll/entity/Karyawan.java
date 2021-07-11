package com.artivisi.payroll.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Entity
@Table
public class Karyawan extends BaseEntity {

    @NotEmpty private String fullname;

    @Column(unique = true)
    @NotEmpty private String email;

    @Column(unique = true)
    @NotEmpty private String phone;

    @Column(unique = true)
    @NotEmpty private String fingerPrintId;

    private String address;

    @NotNull private BigDecimal gajiPokok;

    // uang tunjangan
    @NotNull private BigDecimal uangMakan;
    @NotNull private BigDecimal uangTransport;

    public BigDecimal getTotalGaji(){
        BigDecimal gp = this.gajiPokok != null ? this.gajiPokok : BigDecimal.ZERO;
        BigDecimal um = this.uangMakan != null ? this.uangMakan : BigDecimal.ZERO;
        BigDecimal ut = this.uangTransport != null ? this.uangTransport : BigDecimal.ZERO;

        return gp.add(um).add(ut);
    }
}
