package com.etrat.web.rest;

import com.etrat.domain.Transaction;
import com.etrat.domain.TransactionType;
import com.etrat.repository.TransactionRepository;
import com.etrat.repository.TransactionTypeRepository;
import com.etrat.service.TransactionService;
import com.etrat.util.PaypingUtil;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing {@link Transaction}.
 */
@Controller
public class PaymentForm {
    @Autowired
    private PaypingUtil paypingUtil;

    @Autowired
    TransactionService transactionService;

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    @GetMapping("/gateway/payment")
    public String main(@RequestParam(name = "amount") Integer amount, Model model) {
        model.addAttribute("amount", amount);
        TransactionType transactionType = transactionTypeRepository.findById("00").get();
        Transaction transaction = new Transaction();
        transaction.setAmount(new BigDecimal(amount));
        transaction.setType(transactionType);
        Transaction save = transactionService.save(transaction);
        String code = paypingUtil.genrateCode(amount, save.getId());
        model.addAttribute("code", code);
        return "paypingPaymentform";
    }
}
