package com.etrat.web.rest;

import com.etrat.domain.Transaction;
import com.etrat.testverify.PaymentIFBindingLocator;
import com.etrat.testverify.PaymentIFBindingSoap;
import org.apache.axis.AxisProperties;
import org.springframework.web.bind.annotation.*;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

/**
 * REST controller for managing {@link Transaction}.
 */
@RestController
@RequestMapping("/payment")
public class VerifyPaymentResource {

    @PostMapping("/verify-transaction")
    public void createTransaction(@RequestBody String  paymentResponse) {
        String s1 = paymentResponse.split("RefNum=")[1].split("&")[0];
        PaymentIFBindingLocator paymentIFBindingSoapStub = new PaymentIFBindingLocator();
        PaymentIFBindingSoap paymentIFBinding = null;
        paymentIFBinding = getPaymentIFBindingSoap(paymentIFBindingSoapStub, paymentIFBinding);
        try {
            double d = paymentIFBinding.verifyTransaction(s1, "12130598");
            System.out.println(d);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        System.out.println(paymentResponse);

    }
    private PaymentIFBindingSoap getPaymentIFBindingSoap(PaymentIFBindingLocator paymentIFBindingSoapStub, PaymentIFBindingSoap paymentIFBinding) {
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

    @GetMapping("/verify-transaction")
    public void createTransactionGet( String  paymentResponse) {
        System.out.println(paymentResponse);

    }


}
