package com.etrat.testverify;//
// Decompiled by Procyon v0.5.36
//

import com.sun.xml.rpc.client.BasicService;
import com.sun.xml.rpc.client.HandlerChainImpl;
import com.sun.xml.rpc.client.ServiceExceptionImpl;
import com.sun.xml.rpc.util.exception.LocalizableExceptionAdapter;
import com.sun.xml.rpc.util.localization.Localizable;

import javax.xml.namespace.QName;
import javax.xml.rpc.JAXRPCException;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.handler.HandlerChain;
import java.rmi.Remote;

public class ReferencePayment_Impl extends BasicService implements ReferencePayment
{
    private static final QName serviceName;
    private static final QName ns1_PaymentIFPort_QNAME;
    private static final Class paymentIF_PortClass;

    public ReferencePayment_Impl() {
        super(ReferencePayment_Impl.serviceName, new QName[] { ReferencePayment_Impl.ns1_PaymentIFPort_QNAME }, new ReferencePayment_SerializerRegistry().getRegistry());
    }

    public Remote getPort(final QName qName, final Class clazz) throws ServiceException {
        try {
            if (qName.equals(ReferencePayment_Impl.ns1_PaymentIFPort_QNAME) && clazz.equals(ReferencePayment_Impl.paymentIF_PortClass)) {
                return this.getPaymentIFPort();
            }
        }
        catch (Exception ex) {
            throw new ServiceExceptionImpl((Localizable)new LocalizableExceptionAdapter((Throwable)ex));
        }
        return super.getPort(qName, clazz);
    }

    public Remote getPort(final Class clazz) throws ServiceException {
        try {
            if (clazz.equals(ReferencePayment_Impl.paymentIF_PortClass)) {
                return this.getPaymentIFPort();
            }
        }
        catch (Exception ex) {
            throw new ServiceExceptionImpl((Localizable)new LocalizableExceptionAdapter((Throwable)ex));
        }
        return super.getPort(clazz);
    }

    public PaymentIF getPaymentIFPort() {
        final String[] roles = new String[0];
        final HandlerChainImpl handlerChainImpl = new HandlerChainImpl(this.getHandlerRegistry().getHandlerChain(ReferencePayment_Impl.ns1_PaymentIFPort_QNAME));
        handlerChainImpl.setRoles(roles);
        final PaymentIF_Stub paymentIF_Stub = new PaymentIF_Stub((HandlerChain)handlerChainImpl);
        try {
            paymentIF_Stub._initialize(super.internalTypeRegistry);
        }
        catch (JAXRPCException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new JAXRPCException(ex2.getMessage(), (Throwable)ex2);
        }
        return paymentIF_Stub;
    }

    static {
        serviceName = new QName("urn:Foo", "ReferencePayment");
        ns1_PaymentIFPort_QNAME = new QName("urn:Foo", "PaymentIFPort");
        paymentIF_PortClass = PaymentIF.class;
    }
}
