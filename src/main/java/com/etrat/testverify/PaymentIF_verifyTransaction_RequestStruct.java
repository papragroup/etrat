package com.etrat.testverify;//
// Decompiled by Procyon v0.5.36
//

public class PaymentIF_verifyTransaction_RequestStruct
{
    protected String string_1;
    protected String string_2;

    public PaymentIF_verifyTransaction_RequestStruct() {
    }

    public PaymentIF_verifyTransaction_RequestStruct(final String string_1, final String string_2) {
        this.string_1 = string_1;
        this.string_2 = string_2;
    }

    public String getString_1() {
        return this.string_1;
    }

    public void setString_1(final String string_1) {
        this.string_1 = string_1;
    }

    public String getString_2() {
        return this.string_2;
    }

    public void setString_2(final String string_2) {
        this.string_2 = string_2;
    }
}
