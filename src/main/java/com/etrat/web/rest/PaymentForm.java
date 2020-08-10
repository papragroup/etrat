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

    @GetMapping("/gateway/payment")
    public String main(@RequestParam(name = "code") String code, Model model) {
        model.addAttribute("code", code);
        return "paypingPaymentform";
    }
}
