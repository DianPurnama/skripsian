package com.artivisi.payroll.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Table
@Entity
@Data
public class GajiKaryawan extends BaseEntity{

    @OneToOne
    @JoinColumn(name = "id_karyawan", nullable = false)
    private Karyawan karyawan;

    @NotNull
    private BigDecimal gajiPokok;

    // uang tunjangan
    @NotNull private BigDecimal uangMakan;
    @NotNull private BigDecimal uangTransport;

    public BigDecimal getTotalGaji(){
        BigDecimal gp = this.gajiPokok != null ? this.gajiPokok : BigDecimal.ZERO;
        BigDecimal um = this.uangMakan != null ? this.uangMakan : BigDecimal.ZERO;
        BigDecimal ut = this.uangTransport != null ? this.uangTransport : BigDecimal.ZERO;

        return gp.add(um).add(ut);
    }

    public BigDecimal getGajiSatuHariKerja(){
        return getTotalGaji().divide(new BigDecimal(20));
    }
}
