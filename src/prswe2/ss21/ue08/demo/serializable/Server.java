package prswe2.ss21.ue08.demo.serializable;

import java.io.Serializable;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    static interface Transmittable {
        void foo();
    }

    static class NonSerializableMember implements Transmittable, Serializable {
        transient final Serializable o;

        public NonSerializableMember() {
            o = new Serializable() {
                private static final long serialVersionUID = 1L;
            };
        }

        @Override
        public void foo() {
            System.out.println(o.hashCode());
        }
    }

    static interface Contract extends Remote {
        Transmittable trasmit() throws RemoteException;
    }

    static class RemoteServerObject extends UnicastRemoteObject implements Contract {

        private static final long serialVersionUID = 6272650931125147084L;

        protected RemoteServerObject(int port) throws RemoteException {
            super(port);
        }

        @Override
        public Transmittable trasmit() throws RemoteException {
            return new NonSerializableMember();
        }

    }

    public static void main(String[] args) {
        try {
            Contract remoteObject = new RemoteServerObject(49152);
            Registry registry = LocateRegistry.createRegistry(49152);
            registry.bind("ServerObject", remoteObject);
            System.out.println("Server Ready...");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }

}
