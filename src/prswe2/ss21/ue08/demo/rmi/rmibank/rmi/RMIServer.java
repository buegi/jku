package prswe2.ss21.ue08.demo.rmi.rmibank.rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import prswe2.ss21.ue08.demo.rmi.rmibank.contract.Bank;
import prswe2.ss21.ue08.demo.rmi.rmibank.impl.BankImpl;

public class RMIServer {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        Bank bank = new BankImpl();
        Registry registry = LocateRegistry.createRegistry(RMIConstants.PORT);
        registry.bind(RMIConstants.BANK_EXPORT_NAME, bank);
        System.out.println("started server");
    }
}
