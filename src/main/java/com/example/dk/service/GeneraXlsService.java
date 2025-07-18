package com.example.dk.service;

import com.example.dk.model.Usuario;
import com.example.dk.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneraXlsService {
    private final UsuarioRepository usuarioRepository;

    public byte[] generateXls() {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Usuarios");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("USUARIOS REGISTRADOS");
            headerRow.createCell(1).setCellValue("ID");
            headerRow.createCell(2).setCellValue("username");
            headerRow.createCell(3).setCellValue("Tipo de Puesto");
            headerRow.createCell(4).setCellValue("Password");

            List<Usuario> usuarios = usuarioRepository.findAll();
            int rowNum = 1;
            for (Usuario usuario : usuarios) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(usuario.getId());
                row.createCell(1).setCellValue(usuario.getUsername());
                row.createCell(2).setCellValue(usuario.getTipoPuesto());
                row.createCell(3).setCellValue(usuario.getContrasena());
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
            // Manejo de excepciones
            e.printStackTrace();
            return null;
        }

    }
}
