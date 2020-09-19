package com.etrat.web.rest;

import com.etrat.domain.Transaction;
import com.etrat.domain.TransactionStatus;
import com.etrat.service.TransactionService;
import com.etrat.testverify.PaymentIFBindingLocator;
import com.etrat.testverify.PaymentIFBindingSoap;
import com.etrat.util.EtratWarpperUtil;
import com.etrat.util.PaypingUtil;
import com.etrat.util.VariziHami;
import com.etrat.util.VerifyResponse;
import com.etrat.web.rest.errors.InvalidAmountException;
import com.etrat.web.rest.errors.RefIdNotFoundException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.xml.rpc.ServiceException;
import org.apache.axis.AxisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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

    @PostMapping("/verify-transaction")
    public String createTransaction(Model model, @RequestBody String paymentResponse) {
        Map<String, String> param = new HashMap<>();
        Arrays
            .stream(paymentResponse.split("&"))
            .forEach(
                p -> {
                    String[] split = p.split("=");
                    param.put(split[0], split[1]);
                }
            );
        Transaction transaction = transactionService
            .findOne(Long.valueOf(param.get("clientrefid")))
            .orElseThrow(() -> new RefIdNotFoundException());
        //        if (transaction.getAmount().intValue()!=Integer.valueOf(param.get("amount"))){
        //            throw new InvalidAmountException();
        //        }
        transaction.setRefrence(Long.valueOf(param.get("refid")));
        transaction.setTransactionStatus(TransactionStatus.PENDING_VERIFY);
        transaction.setCreateDate(Calendar.getInstance().getTime().getTime());
        transactionService.save(transaction);
        VerifyResponse refid = paypingUtil.verify(param.get("refid"), transaction.getAmount().intValue());
        VariziHami variziHami = new VariziHami();
        variziHami.setCodehami(transaction.getUser().getLogin());
        variziHami.setCodehesab(transaction.getType().getId());
        variziHami.setMablagh(String.valueOf(transaction.getAmount().intValue()));
        variziHami.setVariziType("21");
        variziHami.setTarixVarizi("13990520");
        try {
            Long refrenceId = etratWarpperUtil.saveInEtratWrapper(variziHami);
            transaction.setTransactionStatus(TransactionStatus.SUCCESS_VERIFY);
        } catch (Exception e) {
            transaction.setTransactionStatus(TransactionStatus.FAILED_NOTIFY_WRAPPER);
        }
        transactionService.save(transaction);
        //        PaymentIFBindingLocator paymentIFBindingSoapStub = new PaymentIFBindingLocator();
        //        PaymentIFBindingSoap paymentIFBinding = null;
        //        paymentIFBinding = getPaymentIFBindingSoap(paymentIFBindingSoapStub, paymentIFBinding);
        //        try {
        //            double d = paymentIFBinding.verifyTransaction(s1, "12130598");
        //            System.out.println(d);
        //        } catch (RemoteException e) {
        //            e.printStackTrace();
        //        }
        //        System.out.println(paymentResponse);

        return "deeplink";
    }

    private PaymentIFBindingSoap getPaymentIFBindingSoap(
        PaymentIFBindingLocator paymentIFBindingSoapStub,
        PaymentIFBindingSoap paymentIFBinding
    ) {
        try {
            AxisProperties.getProperties().put("proxySet", "true");
            AxisProperties.setProperty("http.proxyHost", "us-east-1-static-hopper.statica.io");
            AxisProperties.setProperty("http.proxyPort", "9293");
            AxisProperties.setProperty("http.proxyUser", "statica4181");
            AxisProperties.setProperty("http.proxyPassword", "06361dccd7ec80fb");
            paymentIFBinding = (PaymentIFBindingSoap) paymentIFBindingSoapStub.getPort(PaymentIFBindingSoap.class);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return paymentIFBinding;
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
