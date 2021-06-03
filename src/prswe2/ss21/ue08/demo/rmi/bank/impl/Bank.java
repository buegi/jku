package prswe2.ss21.ue08.demo.rmi.bank.impl;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private final List<Account> accounts;

    public Bank() {
        accounts = new ArrayList<>();
    }

    public Account createAccount() {
        final Account account = new Account();
        accounts.add(account);
        return account;
    }

    public Account getAccount(int account) {
        return accounts.get(account);
    }

    public void deposit(Account account, double amount) {
        account.changeBalance(amount);
    }

    public void transfer(Account from, Account to, double amount) {
        from.changeBalance(-amount);
        to.changeBalance(amount);
    }
}