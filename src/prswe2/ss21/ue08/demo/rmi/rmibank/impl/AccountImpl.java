package prswe2.ss21.ue08.demo.rmi.rmibank.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import prswe2.ss21.ue08.demo.rmi.rmibank.contract.Account;

final class AccountImpl extends UnicastRemoteObject implements Account {

    private double balance;

    AccountImpl() throws RemoteException {
        this.balance = 0.0;
    }

    @Override
    public synchronized double getBalance() {
        return balance;
    }

    synchronized void changeBalance(double amount) {
        balance += amount;
    }
}
