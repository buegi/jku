package prswe2.ss21.ue08.demo.deadlock;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import prswe2.ss21.ue08.demo.deadlock.Client.ClientInterface;

public class Server {

	static interface ServerInterface extends Remote {
		void firstMethod(ClientInterface client) throws RemoteException;

		void secondMethod() throws RemoteException;
	}

	static class ServerImpl extends UnicastRemoteObject implements ServerInterface {

		private static final long serialVersionUID = 1L;

		protected ServerImpl(int port) throws RemoteException {
			super(port);
		}

		@Override
		public void firstMethod(ClientInterface client) throws RemoteException {
			System.out.println("Before firstMethod()::CriticalRegion");
			synchronized (this) {
				System.out.println("In firstMethod()::CriticalRegion");
				client.callBack();
			}
			System.out.println("After firstMethod()::CriticalRegion");
		}

		@Override
		public void secondMethod() throws RemoteException {
			System.out.println("Before secondMethod()::CriticalRegion");
			synchronized (this) {
				System.out.println("In secondMethod()::CriticalRegion");
			}
			System.out.println("After secondMethod()::CriticalRegion");
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
