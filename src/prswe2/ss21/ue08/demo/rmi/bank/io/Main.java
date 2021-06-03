package prswe2.ss21.ue08.demo.rmi.bank.io;

import prswe2.ss21.ue08.demo.rmi.bank.impl.Account;
import prswe2.ss21.ue08.demo.rmi.bank.impl.Bank;

public class Main {

    public static void main(String[] args) {
        Bank bank = new Bank();

        System.err.println("create first account");
        Account firstAccount = bank.createAccount();

        System.err.println("create second account");
        Account secondAccount = bank.createAccount();

        System.err.println("deposit 500$ to first account");
        bank.deposit(firstAccount, 500);

        System.err.println("deposit 600$ to second account");
        bank.deposit(secondAccount, 600);

        System.err.println("transfer 100$ from first account to second account");
        bank.transfer(firstAccount, secondAccount, 100);

        System.err.println("print balance");
        printAccount("firstAccount", firstAccount);
        printAccount("secondAccount", secondAccount);
    }

    private static void printAccount(String name, Account account) {
        System.out.printf("Account %s contains %.2f$%n", name, account.getBalance());
    }
}
