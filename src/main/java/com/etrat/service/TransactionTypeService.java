package com.etrat.service;

import com.etrat.domain.TransactionType;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link TransactionType}.
 */
public interface TransactionTypeService {

    /**
     * Save a transactionType.
     *
     * @param transactionType the entity to save.
     * @return the persisted entity.
     */
    TransactionType save(TransactionType transactionType);

    /**
     * Get all the transactionTypes.
     *
     * @return the list of entities.
     */
    List<TransactionType> findAll();


    /**
     * Get the "id" transactionType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TransactionType> findOne(Long id);

    /**
     * Delete the "id" transactionType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
