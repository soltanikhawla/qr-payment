package com.DataMappingApplication.Service;

import com.DataMappingApplication.Entity.Transaction;
import com.DataMappingApplication.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction createTransaction(double amount, String qrCode) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setQrCode(qrCode);
        return transactionRepository.save(transaction);
    }


    public Transaction createTransaction( Transaction transaction) {

        return transactionRepository.save(transaction);
    }

    public Optional<Transaction> getTransaction(Long id) {
        return transactionRepository.findById(id);
    }

    public Transaction completeTransaction(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        transaction.setStatus("completed");
        return transactionRepository.save(transaction);
    }
}
