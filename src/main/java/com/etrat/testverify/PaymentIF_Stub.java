package com.etrat.testverify;//
// Decompiled by Procyon v0.5.36
//

import com.sun.xml.rpc.client.SenderException;
import com.sun.xml.rpc.client.StreamingSenderState;
import com.sun.xml.rpc.client.StubBase;
import com.sun.xml.rpc.encoding.*;
import com.sun.xml.rpc.soap.message.InternalSOAPMessage;
import com.sun.xml.rpc.soap.message.SOAPBlockInfo;
import com.sun.xml.rpc.streaming.XMLReader;

import javax.xml.namespace.QName;
import javax.xml.rpc.JAXRPCException;
import javax.xml.rpc.handler.HandlerChain;
import java.rmi.RemoteException;

public class PaymentIF_Stub extends StubBase implements PaymentIF
{
    private static final QName _portName;
    private static final int verifyTransaction_OPCODE = 0;
    private static final int reverseTransaction_OPCODE = 1;
    private static final QName ns1_verifyTransaction_verifyTransaction_QNAME;
    private static final QName ns1_verifyTransaction_TYPE_QNAME;
    private CombinedSerializer ns1_myPaymentIF_verifyTransaction_RequestStruct_SOAPSerializer;
    private static final QName ns1_verifyTransaction_verifyTransactionResponse_QNAME;
    private static final QName ns1_verifyTransactionResponse_TYPE_QNAME;
    private CombinedSerializer ns1_myPaymentIF_verifyTransaction_ResponseStruct_SOAPSerializer;
    private static final QName ns1_reverseTransaction_reverseTransaction_QNAME;
    private static final QName ns1_reverseTransaction_TYPE_QNAME;
    private CombinedSerializer ns1_myPaymentIF_reverseTransaction_RequestStruct_SOAPSerializer;
    private static final QName ns1_reverseTransaction_reverseTransactionResponse_QNAME;
    private static final QName ns1_reverseTransactionResponse_TYPE_QNAME;
    private CombinedSerializer ns1_myPaymentIF_reverseTransaction_ResponseStruct_SOAPSerializer;
    private static final String[] myNamespace_declarations;
    private static final QName[] understoodHeaderNames;

    public PaymentIF_Stub(final HandlerChain handlerChain) {
        super(handlerChain);
        this._setProperty("javax.xml.rpc.service.endpoint.address", (Object)"http://192.168.1.22:8080/ref-payment/ws/ReferencePayment");
    }

    public double verifyTransaction(final String string_1, final String string_2) throws RemoteException {
        try {
            final StreamingSenderState start = this._start(this._handlerChain);
            final InternalSOAPMessage request = start.getRequest();
            request.setOperationCode(0);
            final PaymentIF_verifyTransaction_RequestStruct value = new PaymentIF_verifyTransaction_RequestStruct();
            value.setString_1(string_1);
            value.setString_2(string_2);
            final SOAPBlockInfo body = new SOAPBlockInfo(PaymentIF_Stub.ns1_verifyTransaction_verifyTransaction_QNAME);
            body.setValue((Object)value);
            body.setSerializer((JAXRPCSerializer)this.ns1_myPaymentIF_verifyTransaction_RequestStruct_SOAPSerializer);
            request.setBody(body);
            start.getMessageContext().setProperty("http.soap.action", (Object)"");
            this._send((String)this._getProperty("javax.xml.rpc.service.endpoint.address"), start);
            final Object value2 = start.getResponse().getBody().getValue();
            PaymentIF_verifyTransaction_ResponseStruct paymentIF_verifyTransaction_ResponseStruct;
            if (value2 instanceof SOAPDeserializationState) {
                paymentIF_verifyTransaction_ResponseStruct = (PaymentIF_verifyTransaction_ResponseStruct)((SOAPDeserializationState)value2).getInstance();
            }
            else {
                paymentIF_verifyTransaction_ResponseStruct = (PaymentIF_verifyTransaction_ResponseStruct)value2;
            }
            return paymentIF_verifyTransaction_ResponseStruct.getResult();
        }
        catch (RemoteException ex) {
            throw ex;
        }
        catch (JAXRPCException cause) {
            throw new RemoteException(cause.getMessage(), (Throwable)cause);
        }
        catch (Exception cause2) {
            if (cause2 instanceof RuntimeException) {
                throw (RuntimeException)cause2;
            }
            throw new RemoteException(cause2.getMessage(), cause2);
        }
    }

    public int reverseTransaction(final String string_1, final String string_2, final String string_3, final double double_4) throws RemoteException {
        try {
            final StreamingSenderState start = this._start(this._handlerChain);
            final InternalSOAPMessage request = start.getRequest();
            request.setOperationCode(1);
            final PaymentIF_reverseTransaction_RequestStruct value = new PaymentIF_reverseTransaction_RequestStruct();
            value.setString_1(string_1);
            value.setString_2(string_2);
            value.setString_3(string_3);
            value.setDouble_4(double_4);
            final SOAPBlockInfo body = new SOAPBlockInfo(PaymentIF_Stub.ns1_reverseTransaction_reverseTransaction_QNAME);
            body.setValue((Object)value);
            body.setSerializer((JAXRPCSerializer)this.ns1_myPaymentIF_reverseTransaction_RequestStruct_SOAPSerializer);
            request.setBody(body);
            start.getMessageContext().setProperty("http.soap.action", (Object)"");
            this._send((String)this._getProperty("javax.xml.rpc.service.endpoint.address"), start);
            final Object value2 = start.getResponse().getBody().getValue();
            PaymentIF_reverseTransaction_ResponseStruct paymentIF_reverseTransaction_ResponseStruct;
            if (value2 instanceof SOAPDeserializationState) {
                paymentIF_reverseTransaction_ResponseStruct = (PaymentIF_reverseTransaction_ResponseStruct)((SOAPDeserializationState)value2).getInstance();
            }
            else {
                paymentIF_reverseTransaction_ResponseStruct = (PaymentIF_reverseTransaction_ResponseStruct)value2;
            }
            return paymentIF_reverseTransaction_ResponseStruct.getResult();
        }
        catch (RemoteException ex) {
            throw ex;
        }
        catch (JAXRPCException cause) {
            throw new RemoteException(cause.getMessage(), (Throwable)cause);
        }
        catch (Exception cause2) {
            if (cause2 instanceof RuntimeException) {
                throw (RuntimeException)cause2;
            }
            throw new RemoteException(cause2.getMessage(), cause2);
        }
    }

    protected void _readFirstBodyElement(final XMLReader xmlReader, final SOAPDeserializationContext soapDeserializationContext, final StreamingSenderState streamingSenderState) throws Exception {
        final int operationCode = streamingSenderState.getRequest().getOperationCode();
        switch (operationCode) {
            case 0: {
                this._deserialize_verifyTransaction(xmlReader, soapDeserializationContext, streamingSenderState);
                break;
            }
            case 1: {
                this._deserialize_reverseTransaction(xmlReader, soapDeserializationContext, streamingSenderState);
                break;
            }
            default: {
                throw new SenderException("sender.response.unrecognizedOperation", Integer.toString(operationCode));
            }
        }
    }

    private void _deserialize_verifyTransaction(final XMLReader xmlReader, final SOAPDeserializationContext soapDeserializationContext, final StreamingSenderState streamingSenderState) throws Exception {
        final Object deserialize = this.ns1_myPaymentIF_verifyTransaction_ResponseStruct_SOAPSerializer.deserialize(PaymentIF_Stub.ns1_verifyTransaction_verifyTransactionResponse_QNAME, xmlReader, soapDeserializationContext);
        final SOAPBlockInfo body = new SOAPBlockInfo(PaymentIF_Stub.ns1_verifyTransaction_verifyTransactionResponse_QNAME);
        body.setValue(deserialize);
        streamingSenderState.getResponse().setBody(body);
    }

    private void _deserialize_reverseTransaction(final XMLReader xmlReader, final SOAPDeserializationContext soapDeserializationContext, final StreamingSenderState streamingSenderState) throws Exception {
        final Object deserialize = this.ns1_myPaymentIF_reverseTransaction_ResponseStruct_SOAPSerializer.deserialize(PaymentIF_Stub.ns1_reverseTransaction_reverseTransactionResponse_QNAME, xmlReader, soapDeserializationContext);
        final SOAPBlockInfo body = new SOAPBlockInfo(PaymentIF_Stub.ns1_reverseTransaction_reverseTransactionResponse_QNAME);
        body.setValue(deserialize);
        streamingSenderState.getResponse().setBody(body);
    }

    public String _getDefaultEnvelopeEncodingStyle() {
        return null;
    }

    public String _getImplicitEnvelopeEncodingStyle() {
        return "";
    }

    public String _getEncodingStyle() {
        return "http://schemas.xmlsoap.org/soap/encoding/";
    }

    public void _setEncodingStyle(final String s) {
        throw new UnsupportedOperationException("cannot set encoding style");
    }

    protected String[] _getNamespaceDeclarations() {
        return PaymentIF_Stub.myNamespace_declarations;
    }

    public QName[] _getUnderstoodHeaders() {
        return PaymentIF_Stub.understoodHeaderNames;
    }

    public void _initialize(final InternalTypeMappingRegistry internalTypeMappingRegistry) throws Exception {
        super._initialize(internalTypeMappingRegistry);
        this.ns1_myPaymentIF_reverseTransaction_RequestStruct_SOAPSerializer = (CombinedSerializer)internalTypeMappingRegistry.getSerializer("http://schemas.xmlsoap.org/soap/encoding/", PaymentIF_reverseTransaction_RequestStruct.class, PaymentIF_Stub.ns1_reverseTransaction_TYPE_QNAME);
        this.ns1_myPaymentIF_reverseTransaction_ResponseStruct_SOAPSerializer = (CombinedSerializer)internalTypeMappingRegistry.getSerializer("http://schemas.xmlsoap.org/soap/encoding/", PaymentIF_reverseTransaction_ResponseStruct.class, PaymentIF_Stub.ns1_reverseTransactionResponse_TYPE_QNAME);
        this.ns1_myPaymentIF_verifyTransaction_ResponseStruct_SOAPSerializer = (CombinedSerializer)internalTypeMappingRegistry.getSerializer("http://schemas.xmlsoap.org/soap/encoding/", PaymentIF_verifyTransaction_ResponseStruct.class, PaymentIF_Stub.ns1_verifyTransactionResponse_TYPE_QNAME);
        this.ns1_myPaymentIF_verifyTransaction_RequestStruct_SOAPSerializer = (CombinedSerializer)internalTypeMappingRegistry.getSerializer("http://schemas.xmlsoap.org/soap/encoding/", PaymentIF_verifyTransaction_RequestStruct.class, PaymentIF_Stub.ns1_verifyTransaction_TYPE_QNAME);
    }

    static {
        _portName = new QName("urn:Foo", "PaymentIFPort");
        ns1_verifyTransaction_verifyTransaction_QNAME = new QName("urn:Foo", "verifyTransaction");
        ns1_verifyTransaction_TYPE_QNAME = new QName("urn:Foo", "verifyTransaction");
        ns1_verifyTransaction_verifyTransactionResponse_QNAME = new QName("urn:Foo", "verifyTransactionResponse");
        ns1_verifyTransactionResponse_TYPE_QNAME = new QName("urn:Foo", "verifyTransactionResponse");
        ns1_reverseTransaction_reverseTransaction_QNAME = new QName("urn:Foo", "reverseTransaction");
        ns1_reverseTransaction_TYPE_QNAME = new QName("urn:Foo", "reverseTransaction");
        ns1_reverseTransaction_reverseTransactionResponse_QNAME = new QName("urn:Foo", "reverseTransactionResponse");
        ns1_reverseTransactionResponse_TYPE_QNAME = new QName("urn:Foo", "reverseTransactionResponse");
        myNamespace_declarations = new String[] { "ns0", "urn:Foo" };
        understoodHeaderNames = new QName[0];
    }
}
