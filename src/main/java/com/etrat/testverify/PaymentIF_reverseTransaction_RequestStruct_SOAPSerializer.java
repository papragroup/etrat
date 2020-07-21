package com.etrat.testverify;//
// Decompiled by Procyon v0.5.36
//

import com.sun.xml.rpc.encoding.*;
import com.sun.xml.rpc.streaming.XMLReader;
import com.sun.xml.rpc.streaming.XMLReaderUtil;
import com.sun.xml.rpc.streaming.XMLWriter;
import com.sun.xml.rpc.wsdl.document.schema.SchemaConstants;

import javax.xml.namespace.QName;

public class PaymentIF_reverseTransaction_RequestStruct_SOAPSerializer extends ObjectSerializerBase implements Initializable
{
    private static final QName ns1_String_1_QNAME;
    private static final QName ns2_string_TYPE_QNAME;
    private CombinedSerializer ns2_myns2_string__java_lang_String_String_Serializer;
    private static final QName ns1_String_2_QNAME;
    private static final QName ns1_String_3_QNAME;
    private static final QName ns1_double_4_QNAME;
    private static final QName ns2_double_TYPE_QNAME;
    private CombinedSerializer ns2_myns2__double__double_Double_Serializer;
    private static final int mySTRING_1_INDEX = 0;
    private static final int mySTRING_2_INDEX = 1;
    private static final int mySTRING_3_INDEX = 2;
    private static final int myDOUBLE_4_INDEX = 3;

    public PaymentIF_reverseTransaction_RequestStruct_SOAPSerializer(final QName qName, final boolean b, final boolean b2, final String s) {
        super(qName, b, b2, s);
    }

    public void initialize(final InternalTypeMappingRegistry internalTypeMappingRegistry) throws Exception {
        this.ns2_myns2_string__java_lang_String_String_Serializer = (CombinedSerializer)internalTypeMappingRegistry.getSerializer("http://schemas.xmlsoap.org/soap/encoding/", String.class, PaymentIF_reverseTransaction_RequestStruct_SOAPSerializer.ns2_string_TYPE_QNAME);
        this.ns2_myns2__double__double_Double_Serializer = (CombinedSerializer)internalTypeMappingRegistry.getSerializer("http://schemas.xmlsoap.org/soap/encoding/", (Class)Double.TYPE, PaymentIF_reverseTransaction_RequestStruct_SOAPSerializer.ns2_double_TYPE_QNAME);
    }

    public Object doDeserialize(SOAPDeserializationState soapDeserializationState, final XMLReader xmlReader, final SOAPDeserializationContext soapDeserializationContext) throws Exception {
        final PaymentIF_reverseTransaction_RequestStruct paymentIF_reverseTransaction_RequestStruct = new PaymentIF_reverseTransaction_RequestStruct();
        Object o = null;
        boolean b = true;
        xmlReader.nextElementContent();
        for (int i = 0; i < 4; ++i) {
            final QName name = xmlReader.getName();
            if (xmlReader.getState() == 2) {
                break;
            }
            if (name.equals(PaymentIF_reverseTransaction_RequestStruct_SOAPSerializer.ns1_String_1_QNAME)) {
                final Object deserialize = this.ns2_myns2_string__java_lang_String_String_Serializer.deserialize(PaymentIF_reverseTransaction_RequestStruct_SOAPSerializer.ns1_String_1_QNAME, xmlReader, soapDeserializationContext);
                if (deserialize instanceof SOAPDeserializationState) {
                    if (o == null) {
                        o = new PaymentIF_reverseTransaction_RequestStruct_SOAPBuilder();
                    }
                    soapDeserializationState = ObjectSerializerBase.registerWithMemberState((Object)paymentIF_reverseTransaction_RequestStruct, soapDeserializationState, deserialize, 0, (SOAPInstanceBuilder)o);
                    b = false;
                }
                else {
                    paymentIF_reverseTransaction_RequestStruct.setString_1((String)deserialize);
                }
                xmlReader.nextElementContent();
            }
            else if (name.equals(PaymentIF_reverseTransaction_RequestStruct_SOAPSerializer.ns1_String_2_QNAME)) {
                final Object deserialize2 = this.ns2_myns2_string__java_lang_String_String_Serializer.deserialize(PaymentIF_reverseTransaction_RequestStruct_SOAPSerializer.ns1_String_2_QNAME, xmlReader, soapDeserializationContext);
                if (deserialize2 instanceof SOAPDeserializationState) {
                    if (o == null) {
                        o = new PaymentIF_reverseTransaction_RequestStruct_SOAPBuilder();
                    }
                    soapDeserializationState = ObjectSerializerBase.registerWithMemberState((Object)paymentIF_reverseTransaction_RequestStruct, soapDeserializationState, deserialize2, 1, (SOAPInstanceBuilder)o);
                    b = false;
                }
                else {
                    paymentIF_reverseTransaction_RequestStruct.setString_2((String)deserialize2);
                }
                xmlReader.nextElementContent();
            }
            else if (name.equals(PaymentIF_reverseTransaction_RequestStruct_SOAPSerializer.ns1_String_3_QNAME)) {
                final Object deserialize3 = this.ns2_myns2_string__java_lang_String_String_Serializer.deserialize(PaymentIF_reverseTransaction_RequestStruct_SOAPSerializer.ns1_String_3_QNAME, xmlReader, soapDeserializationContext);
                if (deserialize3 instanceof SOAPDeserializationState) {
                    if (o == null) {
                        o = new PaymentIF_reverseTransaction_RequestStruct_SOAPBuilder();
                    }
                    soapDeserializationState = ObjectSerializerBase.registerWithMemberState((Object)paymentIF_reverseTransaction_RequestStruct, soapDeserializationState, deserialize3, 2, (SOAPInstanceBuilder)o);
                    b = false;
                }
                else {
                    paymentIF_reverseTransaction_RequestStruct.setString_3((String)deserialize3);
                }
                xmlReader.nextElementContent();
            }
            else {
                if (!name.equals(PaymentIF_reverseTransaction_RequestStruct_SOAPSerializer.ns1_double_4_QNAME)) {
                    throw new DeserializationException("soap.unexpectedElementName", new Object[] { PaymentIF_reverseTransaction_RequestStruct_SOAPSerializer.ns1_double_4_QNAME, name });
                }
                paymentIF_reverseTransaction_RequestStruct.setDouble_4((Double) this.ns2_myns2__double__double_Double_Serializer.deserialize(PaymentIF_reverseTransaction_RequestStruct_SOAPSerializer.ns1_double_4_QNAME, xmlReader, soapDeserializationContext));
                xmlReader.nextElementContent();
            }
        }
        XMLReaderUtil.verifyReaderState(xmlReader, 2);
        return b ? paymentIF_reverseTransaction_RequestStruct : soapDeserializationState;
    }

    public void doSerializeInstance(final Object o, final XMLWriter xmlWriter, final SOAPSerializationContext soapSerializationContext) throws Exception {
        final PaymentIF_reverseTransaction_RequestStruct paymentIF_reverseTransaction_RequestStruct = (PaymentIF_reverseTransaction_RequestStruct)o;
        this.ns2_myns2_string__java_lang_String_String_Serializer.serialize((Object)paymentIF_reverseTransaction_RequestStruct.getString_1(), PaymentIF_reverseTransaction_RequestStruct_SOAPSerializer.ns1_String_1_QNAME, (SerializerCallback)null, xmlWriter, soapSerializationContext);
        this.ns2_myns2_string__java_lang_String_String_Serializer.serialize((Object)paymentIF_reverseTransaction_RequestStruct.getString_2(), PaymentIF_reverseTransaction_RequestStruct_SOAPSerializer.ns1_String_2_QNAME, (SerializerCallback)null, xmlWriter, soapSerializationContext);
        this.ns2_myns2_string__java_lang_String_String_Serializer.serialize((Object)paymentIF_reverseTransaction_RequestStruct.getString_3(), PaymentIF_reverseTransaction_RequestStruct_SOAPSerializer.ns1_String_3_QNAME, (SerializerCallback)null, xmlWriter, soapSerializationContext);
        this.ns2_myns2__double__double_Double_Serializer.serialize((Object)new Double(paymentIF_reverseTransaction_RequestStruct.getDouble_4()), PaymentIF_reverseTransaction_RequestStruct_SOAPSerializer.ns1_double_4_QNAME, (SerializerCallback)null, xmlWriter, soapSerializationContext);
    }

    static {
        ns1_String_1_QNAME = new QName("", "String_1");
        ns2_string_TYPE_QNAME = SchemaConstants.QNAME_TYPE_STRING;
        ns1_String_2_QNAME = new QName("", "String_2");
        ns1_String_3_QNAME = new QName("", "String_3");
        ns1_double_4_QNAME = new QName("", "double_4");
        ns2_double_TYPE_QNAME = SchemaConstants.QNAME_TYPE_DOUBLE;
    }
}
