package com.etrat.testverify;//
// Decompiled by Procyon v0.5.36
//

import com.sun.xml.rpc.encoding.*;
import com.sun.xml.rpc.streaming.XMLReader;
import com.sun.xml.rpc.streaming.XMLReaderUtil;
import com.sun.xml.rpc.streaming.XMLWriter;
import com.sun.xml.rpc.wsdl.document.schema.SchemaConstants;

import javax.xml.namespace.QName;

public class PaymentIF_verifyTransaction_ResponseStruct_SOAPSerializer extends ObjectSerializerBase implements Initializable
{
    private static final QName ns1_result_QNAME;
    private static final QName ns2_double_TYPE_QNAME;
    private CombinedSerializer ns2_myns2__double__double_Double_Serializer;
    private static final int myRESULT_INDEX = 0;

    public PaymentIF_verifyTransaction_ResponseStruct_SOAPSerializer(final QName qName, final boolean b, final boolean b2, final String s) {
        super(qName, b, b2, s);
    }

    public void initialize(final InternalTypeMappingRegistry internalTypeMappingRegistry) throws Exception {
        this.ns2_myns2__double__double_Double_Serializer = (CombinedSerializer)internalTypeMappingRegistry.getSerializer("http://schemas.xmlsoap.org/soap/encoding/", (Class)Double.TYPE, PaymentIF_verifyTransaction_ResponseStruct_SOAPSerializer.ns2_double_TYPE_QNAME);
    }

    public Object doDeserialize(final SOAPDeserializationState soapDeserializationState, final XMLReader xmlReader, final SOAPDeserializationContext soapDeserializationContext) throws Exception {
        final PaymentIF_verifyTransaction_ResponseStruct paymentIF_verifyTransaction_ResponseStruct = new PaymentIF_verifyTransaction_ResponseStruct();
        final boolean b = true;
        xmlReader.nextElementContent();
        if (xmlReader.getState() == 1) {
            paymentIF_verifyTransaction_ResponseStruct.setResult((Double) this.ns2_myns2__double__double_Double_Serializer.deserialize((QName)null, xmlReader, soapDeserializationContext));
            xmlReader.nextElementContent();
        }
        xmlReader.getName();
        XMLReaderUtil.verifyReaderState(xmlReader, 2);
        return b ? paymentIF_verifyTransaction_ResponseStruct : soapDeserializationState;
    }

    public void doSerializeInstance(final Object o, final XMLWriter xmlWriter, final SOAPSerializationContext soapSerializationContext) throws Exception {
        this.ns2_myns2__double__double_Double_Serializer.serialize((Object)new Double(((PaymentIF_verifyTransaction_ResponseStruct)o).getResult()), PaymentIF_verifyTransaction_ResponseStruct_SOAPSerializer.ns1_result_QNAME, (SerializerCallback)null, xmlWriter, soapSerializationContext);
    }

    protected void verifyName(final XMLReader xmlReader, final QName qName) throws Exception {
    }

    static {
        ns1_result_QNAME = new QName("", "result");
        ns2_double_TYPE_QNAME = SchemaConstants.QNAME_TYPE_DOUBLE;
    }
}
