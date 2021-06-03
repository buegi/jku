package prswe2.ss21.ue08.demo.rmi.rmibank.contract;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Bank extends Remote {

    int createAccount() throws RemoteException;

    Account getAccount(int account) throws RemoteException;

    void deposit(int account, double amount) throws RemoteException;

    void transfer(int from, int to, double amount) throws RemoteException;

    void addListener(BankChangedListener listener) throws RemoteException;

    void removeListener(BankChangedListener listener) throws RemoteException;
}