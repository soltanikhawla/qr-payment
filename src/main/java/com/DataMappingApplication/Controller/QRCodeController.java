package com.DataMappingApplication.Controller;


import com.google.zxing.WriterException;
import com.DataMappingApplication.Service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/qr")
@CrossOrigin("http://localhost:4200")
public class QRCodeController {

    private final QRCodeService qrCodeService;

    @Autowired
    public QRCodeController(QRCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }
//    @GetMapping("/generate")
//    public ResponseEntity<byte[]> generateQRCode(@RequestParam String text) {
//        try {
//            byte[] qrCodeImage = qrCodeService.generateQRCode(text, 300, 300);
//            return ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"qrcode.png\"")
//                    .contentType(MediaType.IMAGE_PNG)
//                    .body(qrCodeImage);
//        } catch (WriterException | IOException e) {
//            return ResponseEntity.internalServerError().build();
//        }
//    }
    @GetMapping("/generate")
    public ResponseEntity<byte[]> generateQRCode(@RequestParam double amount) {
        try {
            // Convertir le montant en texte pour le QR code
            String qrContent = String.format("%.2f", amount);
            byte[] qrCodeImage = qrCodeService.generateQRCode(qrContent, 300, 300);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"qrcode.png\"")
                    .contentType(MediaType.IMAGE_PNG)
                    .body(qrCodeImage);
        } catch (WriterException | IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

//    @GetMapping("/generate")
//    public ResponseEntity<byte[]> generateQRCode(@RequestParam String text) {
//        try {
//            byte[] qrCodeImage = qrCodeService.generateQRCode(text, 300, 300);
//            return ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"qrcode.png\"")
//                    .contentType(MediaType.IMAGE_PNG)
//                    .body(qrCodeImage);
//        } catch (WriterException | IOException e) {
//            return ResponseEntity.internalServerError().build();
//        }
//    }
}
