package com.etrat.testverify;//
// Decompiled by Procyon v0.5.36
//

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PaymentIF extends Remote
{
    int reverseTransaction(final String p0, final String p1, final String p2, final double p3) throws RemoteException;

    double verifyTransaction(final String p0, final String p1) throws RemoteException;
}
