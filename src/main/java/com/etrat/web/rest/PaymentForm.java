package com.etrat.web.rest;

import com.etrat.domain.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing {@link Transaction}.
 */
@Controller
public class PaymentForm {

    @GetMapping("/gateway/payment")
    public String main(Model model) {
        model.addAttribute("message", "arsham");
        //        model.addAttribute("tasks", tasks);
        return "paymentform"; //view
    }
}
