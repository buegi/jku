package prswe2.ss21.ue08.demo.callbacks;

import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import prswe2.ss21.ue08.demo.callbacks.Server.ServerInterface;

public class Client {
    static interface ClientInterface extends Remote {
        void sayHelloOnClientRemote() throws RemoteException;

        String getClientName() throws RemoteException;
    }

    static class ClientImpl extends UnicastRemoteObject implements ClientInterface {

        private static final long serialVersionUID = 1L;

        protected ClientImpl() throws RemoteException {
            super();
        }

        @Override
        public void sayHelloOnClientRemote() throws RemoteException {
            System.out.println("Hello From Client");
        }

        @Override
        public String getClientName() throws RemoteException {

            return "default";
        }

    }

    public static void main(String[] args) throws NoSuchObjectException {
        ClientImpl c = null;
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1"/* localhost */, 49152);
            ServerInterface fromStub = (ServerInterface) registry.lookup("ServerObject");
            c = new ClientImpl();
            fromStub.sayHelloOnServerRemote(c);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } finally {
            UnicastRemoteObject.unexportObject(c, true);
        }
    }
}
