package com.etrat.web.rest;

import com.etrat.domain.Transaction;
import com.etrat.domain.TransactionStatus;
import com.etrat.domain.UserType;
import com.etrat.security.SecurityUtils;
import com.etrat.service.TransactionService;
import com.etrat.util.EtratWarpperUtil;
import com.etrat.util.PaypingUtil;
import com.etrat.util.VariziHami;
import com.etrat.util.VerifyResponse;
import com.etrat.web.rest.errors.RefIdNotFoundException;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.ULocale;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * REST controller for managing {@link Transaction}.
 */
@Controller
@RequestMapping("/payment")
public class VerifyPaymentResource {
    @Autowired
    private PaypingUtil paypingUtil;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private EtratWarpperUtil etratWarpperUtil;

    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    @PostMapping("/verify-transaction")
    public String createTransaction(Model model, @RequestBody String paymentResponse) {
        Map<String, String> param = new HashMap<>();
        log.error(paymentResponse);
        Arrays
            .stream(paymentResponse.split("&"))
            .forEach(
                p -> {
                    String[] split = p.split("=");
                    if (split.length == 2) param.put(split[0], split[1]);
                }
            );
        log.error("ref-id" + param.get("clientrefid"));
        log.error("ref-id" + Long.valueOf(param.get("clientrefid")));
        //        Optional<Transaction> one = transactionService.findOne(137l);
        //        log.error("sample" + one.get().getId());

        Transaction transaction = transactionService
            .findOne(Long.valueOf(param.get("clientrefid")))
            .orElseThrow(() -> new RefIdNotFoundException());
        //        if (transaction.getAmount().intValue()!=Integer.valueOf(param.get("amount"))){
        //            throw new InvalidAmountException();
        //        }
        log.error("ref-id result " + transaction.getId());
        transaction.setRefrence(Long.valueOf(param.get("refid")));
        transaction.setTransactionStatus(TransactionStatus.PENDING_VERIFY);
        transaction.setCreateDate(Calendar.getInstance().getTime().getTime());
        transactionService.save(transaction);
        VerifyResponse refid = paypingUtil.verify(param.get("refid"), transaction.getAmount().intValue() / 10);
        VariziHami variziHami = new VariziHami();
        variziHami.setCodehami(transaction.getUser().getLogin());
        variziHami.setCodehesab(transaction.getType().getId());
        variziHami.setMablagh(String.valueOf(transaction.getAmount().intValue()));
        variziHami.setVariziType(transaction.getType().getId());
        ULocale locale = new ULocale("fa_IR@calendar=persian");
        DateFormat outputFormat = new SimpleDateFormat("yyyyMMdd", locale);
        variziHami.setTarixVarizi(outputFormat.format(new Date()));
        transaction.setTransactionStatus(TransactionStatus.SUCCESS_VERIFY);
        if (transaction.getUser().getUserType() == null || transaction.getUser().getUserType().equals(UserType.MEHMAN)) {
            try {
                Optional<String> currentUserLogin = SecurityUtils.getCurrentUserLogin();
                Long refrenceId = etratWarpperUtil.saveInEtratWrapper(variziHami);
            } catch (Exception e) {
                log.error(e.getMessage());
                //                transaction.setTransactionStatus(TransactionStatus.FAILED_NOTIFY_WRAPPER);
            }
        }
        transactionService.save(transaction);

        return "deeplink";
    }
    //
    //    @GetMapping("/verify-transaction")
    //    public void createTransactionGet( String  paymentResponse) {
    //        System.out.println(paymentResponse);
    //
    //    }

    //    @GetMapping("/test")
    //    public String main() {
    ////        model.addAttribute("message", "arsham");
    ////        model.addAttribute("tasks", tasks);
    //        return new MA("welcome"); //view
    //    }

}
