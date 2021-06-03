package prswe2.ss21.ue08.demo.rmi.rmibank.contract;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BankChangedListener extends Remote {
    void onBankChanged(int account) throws RemoteException;
}
