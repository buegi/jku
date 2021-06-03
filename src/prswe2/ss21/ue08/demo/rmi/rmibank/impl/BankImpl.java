package prswe2.ss21.ue08.demo.rmi.rmibank.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import prswe2.ss21.ue08.demo.rmi.rmibank.contract.Account;
import prswe2.ss21.ue08.demo.rmi.rmibank.contract.BankChangedListener;
import prswe2.ss21.ue08.demo.rmi.rmibank.contract.Bank;

public final class BankImpl extends UnicastRemoteObject implements Bank {

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    private final List<AccountImpl> accounts = new ArrayList<>();
    private final List<BankChangedListener> listeners;

    public BankImpl() throws RemoteException {
        listeners = Collections.synchronizedList(new ArrayList<>());
    }

    @Override
    public synchronized int createAccount() throws RemoteException {
        accounts.add(new AccountImpl());
        fireBankChanged(accounts.size());
        return accounts.size() - 1;
    }

    @Override
    public synchronized Account getAccount(int account) throws RemoteException {
        return accounts.get(account);
    }

    @Override
    public synchronized void deposit(int account, double amount) throws RemoteException {
        accounts.get(account).changeBalance(amount);
        fireBankChanged(account);
    }

    @Override
    public synchronized void transfer(int from, int to, double amount) throws RemoteException {
        accounts.get(from).changeBalance(-amount);
        accounts.get(to).changeBalance(amount);
        fireBankChanged(from);
        fireBankChanged(to);
    }

    @Override
    public void addListener(BankChangedListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(BankChangedListener listener) {
        listeners.remove(listener);
    }

    private void fireBankChanged(int account) throws RemoteException {
        for (BankChangedListener l : listeners) {
            executorService.submit(() -> {
                try {
                    l.onBankChanged(account);
                } catch (RemoteException e) {
                    throw new IllegalStateException(e);
                }
            });
        }
    }
}
