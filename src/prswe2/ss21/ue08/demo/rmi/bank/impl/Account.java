package prswe2.ss21.ue08.demo.rmi.bank.impl;

public class Account {
    
    private double balance;

    public Account() {
        this.balance = 0.0;
    }

    public double getBalance() {
        return balance;
    }

    void changeBalance(double amount) {
        balance += amount;
    }
}
