package com.example.dk.service;

import com.example.dk.model.Usuario;
import com.example.dk.repository.UsuarioRepository;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneraPDFService {
    private final UsuarioRepository usuarioRepository;

    public byte[] generatePdf() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);
            document.open();

            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Paragraph title = new Paragraph("Usuarios Registrados", font);
            title.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);

            //Tabla de usuarios

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.addCell("ID");
            table.addCell("Username");
            table.addCell("Tipo de Puesto");
            table.addCell("Password");


            List<Usuario> usuario = usuarioRepository.findAll();
            for (Usuario user : usuario) {
                table.addCell(String.valueOf(user.getId()));
                table.addCell(user.getUsername());
                table.addCell(user.getTipoPuesto());
                table.addCell(user.getContrasena());

            }
            document.add(table);
            document.close();
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
