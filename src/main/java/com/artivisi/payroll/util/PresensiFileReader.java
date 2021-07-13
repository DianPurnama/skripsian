package com.artivisi.payroll.util;

import com.artivisi.payroll.dto.PresensiDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PresensiFileReader {

    public static List<PresensiDto> readFile(MultipartFile file){
        List<PresensiDto> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            int no = 0;
            while ((line = br.readLine()) != null) {
                String[] arrayLine = line.split("\t");
                PresensiDto dto = new PresensiDto();

                dto.setFingerPrintId(arrayLine[0]);
                dto.setTanggalAbsen(arrayLine[2].split(" ")[0]);
                dto.setWaktuAbsen(arrayLine[2].split(" ")[1]);
                result.add(dto);
                no++;
            }
        } catch (IOException e) {
            log.error("======= ERROR READING PRESENSI FILE =======",e);
        }
        return result;
    }

}
