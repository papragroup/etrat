package com.etrat.testverify;//
// Decompiled by Procyon v0.5.36
//

import javax.xml.rpc.Stub;

public class RefClient
{
    private String endpointAddress;
    private String caStore;
    private String storePassword;
    private Stub stub;

    public RefClient(final String endpointAddress, final String caStore, final String storePassword) {
        this.endpointAddress = endpointAddress;
        this.caStore = caStore;
        this.storePassword = storePassword;
        this.stub = null;
    }

    public double verifyTransaction(final String s, final String s2) {
        try {
            if (s.equals("")) {
                System.out.println("Ref Num can't be empty");
                return -1.0;
            }
            if (s2.equals("")) {
                System.out.println("seller acc cant be empty");
                return -1.0;
            }
            this.stub = this.createProxy();
            System.setProperty("javax.net.ssl.trustStore", this.caStore);
            System.setProperty("javax.net.ssl.trustStorePassword", this.storePassword);
            this.stub._setProperty("javax.xml.rpc.service.endpoint.address", (Object)this.endpointAddress);
            final double verifyTransaction = ((PaymentIF)this.stub).verifyTransaction(s, s2);
            System.out.println("result is:");
            System.out.println(verifyTransaction);
            return verifyTransaction;
        }
        catch (Throwable t) {
            System.out.println("In verifyTransaction" + t.getMessage());
            t.printStackTrace();
            return -1.0;
        }
    }

    private Stub createProxy() {
        return (Stub)new ReferencePayment_Impl().getPaymentIFPort();
    }
}
