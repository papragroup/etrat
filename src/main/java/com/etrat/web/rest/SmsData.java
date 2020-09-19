package com.etrat.web.rest;

public abstract class SmsData {
    public String body =
        "<x:Envelope\n" +
        "    xmlns:x=\"http://schemas.xmlsoap.org/soap/envelope/\"\n" +
        "    xmlns:tem=\"http://tempuri.org/\">\n" +
        "    <x:Header/>\n" +
        "    <x:Body>\n" +
        "        <tem:SendSms>\n" +
        "            <tem:cUserName>etrat</tem:cUserName>\n" +
        "            <tem:cPassword>sms@123</tem:cPassword>\n" +
        "            <tem:cBody></tem:cBody>\n" +
        "            <tem:cSmsnumber></tem:cSmsnumber>\n" +
        "            <tem:cGetid>0</tem:cGetid>\n" +
        "            <tem:nCMessage>0</tem:nCMessage>\n" +
        "            <tem:nTypeSent>0</tem:nTypeSent>\n" +
        "            <tem:m_SchedulDate>?</tem:m_SchedulDate>\n" +
        "            <tem:cDomainname>etrat</tem:cDomainname>\n" +
        "            <tem:cFromNumber>30004604602600</tem:cFromNumber>\n" +
        "            <tem:nSpeedsms>0</tem:nSpeedsms>\n" +
        "            <tem:nPeriodmin>0</tem:nPeriodmin>\n" +
        "            <tem:cstarttime>?</tem:cstarttime>\n" +
        "            <tem:cEndTime>?</tem:cEndTime>\n" +
        "        </tem:SendSms>\n" +
        "    </x:Body>\n" +
        "</x:Envelope>";
}
