package com.etrat.testverify;

public class useit
{
	public static void main( String[] args ){

        String s = "website=etrat-fatemi.com&ResNum=1&State=OK&Amount=1000&StateCode=0&MID=12130598&TRACENO=23439&SecurePan=504172******9214&RefNum=GmshtyjwKSuBkmHqHAFXup4y8UHgjCY7C6CZ4E5U%2FM&CID=7574FC079046E4833EC75AAC52839CBC1340C8936B2F151E481E97182D9FB527&RRN=17683778682\n";
        String s1 = s.split("RefNum=")[1].split("&")[0];
        System.out.println(s1);

    }
	public void verify(String refNum,String mid){
        RefClient myclient = new RefClient("https://acquirer.samanepay.com/payments/referencepayment.asmx","/etc/saman.jks","changeit");
        double res ;
        res = myclient.verifyTransaction(refNum, "12130598" );
        if( res < 0 )
        {
            System.out.println("verify failed "+res);
        }
        else
        {
            System.out.println("verify succeded, amount is:");
            System.out.println(res);
        }
    }
}
