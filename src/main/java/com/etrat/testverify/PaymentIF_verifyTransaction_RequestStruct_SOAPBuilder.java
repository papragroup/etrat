package com.etrat.testverify;//
// Decompiled by Procyon v0.5.36
//

import com.sun.xml.rpc.encoding.DeserializationException;
import com.sun.xml.rpc.encoding.SOAPInstanceBuilder;
import com.sun.xml.rpc.util.exception.LocalizableExceptionAdapter;
import com.sun.xml.rpc.util.localization.Localizable;

public class PaymentIF_verifyTransaction_RequestStruct_SOAPBuilder implements SOAPInstanceBuilder
{
    private PaymentIF_verifyTransaction_RequestStruct _instance;
    private String string_1;
    private String string_2;
    private static final int mySTRING_1_INDEX = 0;
    private static final int mySTRING_2_INDEX = 1;

    public void setString_1(final String string_1) {
        this.string_1 = string_1;
    }

    public void setString_2(final String string_2) {
        this.string_2 = string_2;
    }

    public int memberGateType(final int n) {
        switch (n) {
            case 0: {
                return 6;
            }
            case 1: {
                return 6;
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }

    public void construct() {
    }

    public void setMember(final int n, final Object o) {
        try {
            switch (n) {
                case 0: {
                    this._instance.setString_1((String)o);
                    break;
                }
                case 1: {
                    this._instance.setString_2((String)o);
                    break;
                }
                default: {
                    throw new IllegalArgumentException();
                }
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new DeserializationException((Localizable)new LocalizableExceptionAdapter((Throwable)ex2));
        }
    }

    public void initialize() {
    }

    public void setInstance(final Object o) {
        this._instance = (PaymentIF_verifyTransaction_RequestStruct)o;
    }

    public Object getInstance() {
        return this._instance;
    }
}
