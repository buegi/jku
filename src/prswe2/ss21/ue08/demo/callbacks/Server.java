package prswe2.ss21.ue08.demo.callbacks;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import prswe2.ss21.ue08.demo.callbacks.Client.ClientInterface;

public class Server {

    static interface ServerInterface extends Remote {
        void sayHelloOnServerRemote(ClientInterface client) throws RemoteException;
    }

    static class ServerImpl extends UnicastRemoteObject implements ServerInterface {

        private static final long serialVersionUID = 1L;

        protected ServerImpl(int port) throws RemoteException {
            super(port);
        }

        private Map<String, ClientInterface> clients = new HashMap<>();

        @Override
        public void sayHelloOnServerRemote(ClientInterface client) throws RemoteException {
            if (clients.containsKey(client.getClientName())) {

            } else {

            }
        }

        void callClients() {
            for (ClientInterface client : clients.values()) {
                try {
                    client.sayHelloOnClientRemote();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            ServerImpl remoteObject = new ServerImpl(49152);
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
