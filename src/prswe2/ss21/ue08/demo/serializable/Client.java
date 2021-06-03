package prswe2.ss21.ue08.demo.serializable;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import prswe2.ss21.ue08.demo.serializable.Server.Contract;
import prswe2.ss21.ue08.demo.serializable.Server.Transmittable;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1"/* localhost */, 49152);
            Contract fromStub = (Contract) registry.lookup("ServerObject");
            Transmittable t = fromStub.trasmit();
            System.out.println("T received?" + (t != null));
            t.foo();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}
