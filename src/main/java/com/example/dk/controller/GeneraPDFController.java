package com.example.dk.controller;

import com.example.dk.service.GeneraPDFService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/generaPdf")
@RequiredArgsConstructor
public class GeneraPDFController {
    private final GeneraPDFService generaPDFService;

    @GetMapping
    public  ResponseEntity<byte[]> generatePdf() {
        byte[] pdfData = generaPDFService.generatePdf();
        if (pdfData != null) {
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"usuarios.pdf\"")
                    .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                    .body(pdfData);
        } else {
            return ResponseEntity.status(500).build();
        }
    }
}
