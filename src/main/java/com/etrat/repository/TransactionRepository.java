package com.etrat.repository;

import com.etrat.domain.Transaction;
import com.etrat.domain.TransactionStatus;
import com.etrat.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Transaction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Page<Transaction> findByUserAndTransactionStatus(User user, TransactionStatus status, Pageable page);

    @Query("SELECT sum(e.amount) from Transaction e where e.user.login= :d")
    Long sumCreditAmount(@Param("d") String login);
}
