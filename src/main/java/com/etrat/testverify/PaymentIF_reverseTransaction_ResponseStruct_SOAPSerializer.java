package com.etrat.testverify;//
// Decompiled by Procyon v0.5.36
//

import com.sun.xml.rpc.encoding.*;
import com.sun.xml.rpc.streaming.XMLReader;
import com.sun.xml.rpc.streaming.XMLReaderUtil;
import com.sun.xml.rpc.streaming.XMLWriter;
import com.sun.xml.rpc.wsdl.document.schema.SchemaConstants;

import javax.xml.namespace.QName;

public class PaymentIF_reverseTransaction_ResponseStruct_SOAPSerializer extends ObjectSerializerBase implements Initializable
{
    private static final QName ns1_result_QNAME;
    private static final QName ns2_int_TYPE_QNAME;
    private CombinedSerializer ns2_myns2__int__int_Int_Serializer;
    private static final int myRESULT_INDEX = 0;

    public PaymentIF_reverseTransaction_ResponseStruct_SOAPSerializer(final QName qName, final boolean b, final boolean b2, final String s) {
        super(qName, b, b2, s);
    }

    public void initialize(final InternalTypeMappingRegistry internalTypeMappingRegistry) throws Exception {
        this.ns2_myns2__int__int_Int_Serializer = (CombinedSerializer)internalTypeMappingRegistry.getSerializer("http://schemas.xmlsoap.org/soap/encoding/", (Class)Integer.TYPE, PaymentIF_reverseTransaction_ResponseStruct_SOAPSerializer.ns2_int_TYPE_QNAME);
    }

    public Object doDeserialize(final SOAPDeserializationState soapDeserializationState, final XMLReader xmlReader, final SOAPDeserializationContext soapDeserializationContext) throws Exception {
        final PaymentIF_reverseTransaction_ResponseStruct paymentIF_reverseTransaction_ResponseStruct = new PaymentIF_reverseTransaction_ResponseStruct();
        final boolean b = true;
        xmlReader.nextElementContent();
        if (xmlReader.getState() == 1) {
            paymentIF_reverseTransaction_ResponseStruct.setResult((Integer) this.ns2_myns2__int__int_Int_Serializer.deserialize((QName)null, xmlReader, soapDeserializationContext));
            xmlReader.nextElementContent();
        }
        xmlReader.getName();
        XMLReaderUtil.verifyReaderState(xmlReader, 2);
        return b ? paymentIF_reverseTransaction_ResponseStruct : soapDeserializationState;
    }

    public void doSerializeInstance(final Object o, final XMLWriter xmlWriter, final SOAPSerializationContext soapSerializationContext) throws Exception {
        this.ns2_myns2__int__int_Int_Serializer.serialize((Object)new Integer(((PaymentIF_reverseTransaction_ResponseStruct)o).getResult()), PaymentIF_reverseTransaction_ResponseStruct_SOAPSerializer.ns1_result_QNAME, (SerializerCallback)null, xmlWriter, soapSerializationContext);
    }

    protected void verifyName(final XMLReader xmlReader, final QName qName) throws Exception {
    }

    static {
        ns1_result_QNAME = new QName("", "result");
        ns2_int_TYPE_QNAME = SchemaConstants.QNAME_TYPE_INT;
    }
}
