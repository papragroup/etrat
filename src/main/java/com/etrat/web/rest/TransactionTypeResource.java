package com.etrat.web.rest;

import com.etrat.domain.TransactionType;
import com.etrat.service.TransactionTypeService;
import com.etrat.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.etrat.domain.TransactionType}.
 */
@RestController
@RequestMapping("/api")
public class TransactionTypeResource {

    private final Logger log = LoggerFactory.getLogger(TransactionTypeResource.class);

    private static final String ENTITY_NAME = "transactionType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TransactionTypeService transactionTypeService;

    public TransactionTypeResource(TransactionTypeService transactionTypeService) {
        this.transactionTypeService = transactionTypeService;
    }

    /**
     * {@code POST  /transaction-types} : Create a new transactionType.
     *
     * @param transactionType the transactionType to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new transactionType, or with status {@code 400 (Bad Request)} if the transactionType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/transaction-types")
    public ResponseEntity<TransactionType> createTransactionType(@RequestBody TransactionType transactionType) throws URISyntaxException {
        log.debug("REST request to save TransactionType : {}", transactionType);
        if (transactionType.getId() != null) {
            throw new BadRequestAlertException("A new transactionType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TransactionType result = transactionTypeService.save(transactionType);
        return ResponseEntity.created(new URI("/api/transaction-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /transaction-types} : Updates an existing transactionType.
     *
     * @param transactionType the transactionType to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated transactionType,
     * or with status {@code 400 (Bad Request)} if the transactionType is not valid,
     * or with status {@code 500 (Internal Server Error)} if the transactionType couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/transaction-types")
    public ResponseEntity<TransactionType> updateTransactionType(@RequestBody TransactionType transactionType) throws URISyntaxException {
        log.debug("REST request to update TransactionType : {}", transactionType);
        if (transactionType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TransactionType result = transactionTypeService.save(transactionType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, transactionType.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /transaction-types} : get all the transactionTypes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of transactionTypes in body.
     */
    @GetMapping("/transaction-types")
    public List<TransactionType> getAllTransactionTypes() {
        log.debug("REST request to get all TransactionTypes");
        return transactionTypeService.findAll();
    }

    /**
     * {@code GET  /transaction-types/:id} : get the "id" transactionType.
     *
     * @param id the id of the transactionType to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the transactionType, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/transaction-types/{id}")
    public ResponseEntity<TransactionType> getTransactionType(@PathVariable Long id) {
        log.debug("REST request to get TransactionType : {}", id);
        Optional<TransactionType> transactionType = transactionTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(transactionType);
    }

    /**
     * {@code DELETE  /transaction-types/:id} : delete the "id" transactionType.
     *
     * @param id the id of the transactionType to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/transaction-types/{id}")
    public ResponseEntity<Void> deleteTransactionType(@PathVariable Long id) {
        log.debug("REST request to delete TransactionType : {}", id);
        transactionTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
