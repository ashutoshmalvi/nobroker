package com.nobroker.controller;

import com.nobroker.service.ExcelExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/excel")
public class ExcelExportController {
    @Autowired
    private ExcelExportService excelExportService;

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportToExcel() throws IOException{
        byte[] excelBytes = excelExportService.exportUsersToExcel();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment","users.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .body(excelBytes);


    }
}
