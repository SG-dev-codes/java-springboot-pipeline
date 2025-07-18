package com.example.dk.controller;

import com.example.dk.service.GeneraXlsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/generaXls")
@RequiredArgsConstructor
public class GeneraXlsController {
    private final GeneraXlsService generaXlsService;

    @GetMapping
    public ResponseEntity<byte[]> generateXls() {
        byte[] xlsData = generaXlsService.generateXls();
        if (xlsData != null) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"usuarios.xlsx\"")
                    .contentType(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM
                    )
                    .body(xlsData);
        } else {
            return ResponseEntity.status(500).build();
        }
    }
}
