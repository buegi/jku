package prswe2.ss21.ue08.demo.rmi.rmibank.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import prswe2.ss21.ue08.demo.rmi.rmibank.contract.Account;
import prswe2.ss21.ue08.demo.rmi.rmibank.contract.BankChangedListener;
import prswe2.ss21.ue08.demo.rmi.rmibank.contract.Bank;

public class RMIClient {

    private final Bank bank;
    private final MyBankChangedListener listener;

    RMIClient(Bank bank) throws RemoteException {
        this.bank = bank;
        this.listener = new MyBankChangedListener();
    }

    void doActions() throws RemoteException {
        System.err.println("create first account");
        final int firstAccount = bank.createAccount();

        System.err.println("create second account");
        final int secondAccount = bank.createAccount();

        System.err.println("register listener");
        bank.addListener(listener);

        System.err.println("deposit 500$ to first account");
        bank.deposit(firstAccount, 500);

        System.err.println("deposit 600$ to second account");
        bank.deposit(secondAccount, 600);

        System.err.println("transfer 100$ from first account to second account");
        bank.transfer(firstAccount, secondAccount, 100);

        System.err.println("print balance");
        printAccount("firstAccount", bank.getAccount(firstAccount));
        printAccount("secondAccount", bank.getAccount(secondAccount));

        bank.removeListener(listener);
        UnicastRemoteObject.unexportObject(listener, false);
    }

    private final class MyBankChangedListener extends UnicastRemoteObject implements BankChangedListener {

        protected MyBankChangedListener() throws RemoteException {
        }

        @Override
        public void onBankChanged(int account) throws RemoteException {
            System.err.println("getting account in listener");
            Account accountObj = bank.getAccount(account);

            System.err.println("printing account in listener");
            printAccount(String.valueOf(account), accountObj);
        }
    }

    public static void main(String[] args) throws RemoteException, NotBoundException {
        final Registry registry = LocateRegistry.getRegistry(RMIConstants.HOST, RMIConstants.PORT);
        Bank bank = (Bank) registry.lookup(RMIConstants.BANK_EXPORT_NAME);
        new RMIClient(bank).doActions();
    }

    private static void printAccount(String name, Account account) throws RemoteException {
        System.out.printf("Account %s contains %.2f$%n", name, account.getBalance());
    }
}
