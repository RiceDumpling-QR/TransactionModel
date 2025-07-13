package com.NextTier.Transaction.repositories;

import com.NextTier.Transaction.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
