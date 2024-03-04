package com.nobroker.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.nobroker.entity.User;
import com.nobroker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PdfReportService {

    @Autowired
    private UserRepository userRepository;

    public byte[] generateReport() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        List<User> users = userRepository.findAll();

        // Add content to the PDF
        for (User user : users) {
            document.add(new Paragraph("ID: " + user.getId()));
            document.add(new Paragraph("Name: " + user.getName()));
            document.add(new Paragraph("Email: " + user.getEmail()));
            document.add(new Paragraph("Mobile: " + user.getMobile()));
            document.add(new Paragraph("Email Verified: " + (user.isEmailVerified() ? "Yes" : "No")));
            document.add(new Paragraph("\n")); // Add some space between each user
        }

        // Close the document
        document.close();

        return outputStream.toByteArray();
    }
}
