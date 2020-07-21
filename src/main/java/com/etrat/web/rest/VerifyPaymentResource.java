package com.etrat.web.rest;

import com.etrat.domain.Transaction;
import com.etrat.repository.TransactionRepository;
import com.etrat.service.TransactionService;
import com.etrat.service.dto.HesabDTO;
import com.etrat.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link Transaction}.
 */
@RestController
@RequestMapping("/payment")
public class VerifyPaymentResource {

    @PostMapping("/verify-transaction")
    public void createTransaction(@RequestBody String  paymentResponse) {
        String s1 = paymentResponse.split("RefNum=")[1].split("&")[0];
        useit u=new useit();
        u.verify(s1,null);
        System.out.println(sq);
        System.out.println(paymentResponse);

    }

    @GetMapping("/verify-transaction")
    public void createTransactionGet( String  paymentResponse) {
        System.out.println(paymentResponse);

    }


}
