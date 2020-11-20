package com.etrat.web.rest;

import com.etrat.domain.Transaction;
import com.etrat.domain.TransactionStatus;
import com.etrat.domain.TransactionType;
import com.etrat.domain.User;
import com.etrat.repository.TransactionRepository;
import com.etrat.repository.TransactionTypeRepository;
import com.etrat.repository.UserRepository;
import com.etrat.security.SecurityUtils;
import com.etrat.service.TransactionService;
import com.etrat.service.dto.HesabDTO;
import com.etrat.service.dto.TransactionToken;
import com.etrat.util.PaypingUtil;
import com.etrat.web.rest.errors.BadRequestAlertException;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.ULocale;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * REST controller for managing {@link com.etrat.domain.Transaction}.
 */
@RestController
@RequestMapping("/api")
public class TransactionResource {
    private final Logger log = LoggerFactory.getLogger(TransactionResource.class);

    private static final String ENTITY_NAME = "transaction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TransactionService transactionService;

    @Autowired
    private HesabDTO hesabDTO;

    public TransactionResource(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * {@code POST  /transactions} : Ceate a new transaction.
     *
     * @param transaction the transaction to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new transaction, or with status {@code 400 (Bad Request)} if the transaction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */

    @Autowired
    private TransactionRepository transactionRepository;

    @PostMapping("/transactions")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) throws URISyntaxException {
        log.debug("REST request to save Transaction : {}", transaction);
        if (transaction.getId() != null) {
            throw new BadRequestAlertException("A new transaction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Transaction result = transactionRepository.save(transaction);
        return ResponseEntity
            .created(new URI("/api/transactions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PostMapping("/verify-transaction")
    public void createTransaction(String paymentResponse) {
        System.out.println(paymentResponse);
    }

    /**
     * {@code PUT  /transactions} : Updates an existing transaction.
     *
     * @param transaction the transaction to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated transaction,
     * or with status {@code 400 (Bad Request)} if the transaction is not valid,
     * or with status {@code 500 (Internal Server Error)} if the transaction couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/transactions")
    public ResponseEntity<Transaction> updateTransaction(@RequestBody Transaction transaction) throws URISyntaxException {
        log.debug("REST request to update Transaction : {}", transaction);
        if (transaction.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Transaction result = transactionService.save(transaction);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, transaction.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /transactions} : get all the transactions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of transactions in body.
     */
    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getAllTransactions(Pageable pageable) {
        log.debug("REST request to get a page of Transactions");
        Page<Transaction> page = transactionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/transactions/user")
    public ResponseEntity<List<Transaction>> getAllTransactionsUser(Pageable pageable) {
        log.debug("REST request to get a page of Transactions");
        String user = SecurityUtils.getCurrentUserLogin().get();
        User currentUser = userRepository.findOneByLogin(user).get();
        Page<Transaction> page = transactionService.findByUser(currentUser, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/payment-type")
    public ResponseEntity<HesabDTO> getPaymentType() {
        return ResponseEntity.ok().body(hesabDTO);
    }

    /**
     * {@code GET  /transactions/:id} : get the "id" transaction.
     *
     * @param id the id of the transaction to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the transaction, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/transactions/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable Long id) {
        log.debug("REST request to get Transaction : {}", id);
        Optional<Transaction> transaction = transactionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(transaction);
    }

    @Autowired
    private PaypingUtil paypingUtil;

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/transactions/code")
    public ResponseEntity<TransactionToken> getTrancactionCode(
        @RequestParam(name = "amount") Integer amount,
        @RequestParam(name = "type-id") String tarnsactionType
    ) {
        Optional<String> currentUserLogin = SecurityUtils.getCurrentUserLogin();
        Optional<User> oneByLogin = userRepository.findOneByLogin(currentUserLogin.get());
        TransactionType transactionType = transactionTypeRepository.findById(tarnsactionType).get();
        Transaction transaction = new Transaction();
        transaction.setAmount(new BigDecimal(amount));
        transaction.setTransactionStatus(TransactionStatus.CREATE);
        transaction.setUser(oneByLogin.get());
        transaction.setType(transactionType);
        transaction.setCreateDate(new Date().getTime());
        Transaction save = transactionService.save(transaction);
        String code = paypingUtil.genrateCode(amount, save.getId());
        TransactionToken transactionToken = new TransactionToken();
        transactionToken.setToken(code);
        return ResponseUtil.wrapOrNotFound(Optional.of(transactionToken));
    }

    /**
     * {@code DELETE  /transactions/:id} : delete the "id" transaction.
     *
     * @param id the id of the transaction to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/transactions/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        log.debug("REST request to delete Transaction : {}", id);
        transactionService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
