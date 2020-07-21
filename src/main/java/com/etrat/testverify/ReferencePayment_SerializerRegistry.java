package com.etrat.testverify;//
// Decompiled by Procyon v0.5.36
//

import com.sun.xml.rpc.client.BasicService;
import com.sun.xml.rpc.encoding.*;
import com.sun.xml.rpc.soap.SOAPVersion;

import javax.xml.namespace.QName;
import javax.xml.rpc.encoding.*;

public class ReferencePayment_SerializerRegistry implements SerializerConstants
{
    public TypeMappingRegistry getRegistry() {
        final TypeMappingRegistry standardTypeMappingRegistry = BasicService.createStandardTypeMappingRegistry();
        standardTypeMappingRegistry.getTypeMapping("http://www.w3.org/2002/06/soap-encoding");
        final TypeMapping typeMapping = standardTypeMappingRegistry.getTypeMapping("http://schemas.xmlsoap.org/soap/encoding/");
        standardTypeMappingRegistry.getTypeMapping("");
        final QName qName = new QName("urn:Foo", "reverseTransaction");
        registerSerializer(typeMapping, PaymentIF_reverseTransaction_RequestStruct.class, qName, (Serializer)new ReferenceableSerializerImpl(false, (CombinedSerializer)new PaymentIF_reverseTransaction_RequestStruct_SOAPSerializer(qName, false, true, "http://schemas.xmlsoap.org/soap/encoding/"), SOAPVersion.SOAP_11));
        final QName qName2 = new QName("urn:Foo", "verifyTransaction");
        registerSerializer(typeMapping, PaymentIF_verifyTransaction_RequestStruct.class, qName2, (Serializer)new ReferenceableSerializerImpl(false, (CombinedSerializer)new PaymentIF_verifyTransaction_RequestStruct_SOAPSerializer(qName2, false, true, "http://schemas.xmlsoap.org/soap/encoding/"), SOAPVersion.SOAP_11));
        final QName qName3 = new QName("urn:Foo", "reverseTransactionResponse");
        registerSerializer(typeMapping, PaymentIF_reverseTransaction_ResponseStruct.class, qName3, (Serializer)new ReferenceableSerializerImpl(false, (CombinedSerializer)new PaymentIF_reverseTransaction_ResponseStruct_SOAPSerializer(qName3, false, true, "http://schemas.xmlsoap.org/soap/encoding/"), SOAPVersion.SOAP_11));
        final QName qName4 = new QName("urn:Foo", "verifyTransactionResponse");
        registerSerializer(typeMapping, PaymentIF_verifyTransaction_ResponseStruct.class, qName4, (Serializer)new ReferenceableSerializerImpl(false, (CombinedSerializer)new PaymentIF_verifyTransaction_ResponseStruct_SOAPSerializer(qName4, false, true, "http://schemas.xmlsoap.org/soap/encoding/"), SOAPVersion.SOAP_11));
        return standardTypeMappingRegistry;
    }

    private static void registerSerializer(final TypeMapping typeMapping, final Class clazz, final QName qName, final Serializer serializer) {
        typeMapping.register(clazz, qName, (SerializerFactory)new SingletonSerializerFactory(serializer), (DeserializerFactory)new SingletonDeserializerFactory((Deserializer)serializer));
    }
}
