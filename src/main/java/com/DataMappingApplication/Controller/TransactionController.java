package com.DataMappingApplication.Controller;



import com.DataMappingApplication.Service.TransactionService;
import com.DataMappingApplication.Entity.Transaction;
import com.DataMappingApplication.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestParam double amount, @RequestParam String qrCode) {
        Transaction transaction = transactionService.createTransaction(amount, qrCode);
        return ResponseEntity.ok(transaction);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional<Transaction>> getTransaction(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.getTransaction(id));
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<Transaction> completeTransaction(@PathVariable Long id) {
        Transaction transaction = transactionService.completeTransaction(id);
        return ResponseEntity.ok(transaction);
    }
}