package com.etrat.repository;

import com.etrat.domain.Transaction;
import com.etrat.domain.TransactionStatus;
import com.etrat.domain.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Transaction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Page<Transaction> findByUserAndTransactionStatus(User user, TransactionStatus status, Pageable page);
}
