
package com.NextTier.Transaction.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import com.NextTier.Transaction.models.Transaction;

@RequestMapping("/transactions")
@RestController
public class TransactionController {

    private final ConcurrentLinkedQueue<Transaction> localStorage = new ConcurrentLinkedQueue<>();

    // POST: store transaction
    @PostMapping
    public ResponseEntity<String> receiveTransaction(@RequestBody Transaction txn) {
        localStorage.add(txn);
        return ResponseEntity.ok("Transaction stored locally.");
    }

    // GET: batch and clear (called by agent every 30s)

    @GetMapping("/batch")
    public ResponseEntity<List<Transaction>> getAndClearTransactions() {
        List<Transaction> batch = new ArrayList<>();
        Transaction txn;
        while ((txn = localStorage.poll()) != null) {
            batch.add(txn);
        }
        return ResponseEntity.ok(batch);
    }
}
