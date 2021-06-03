package prswe2.ss21.ue08.demo.deadlock;

import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import prswe2.ss21.ue08.demo.deadlock.Server.ServerInterface;

public class Client {
	static interface ClientInterface extends Remote {
		void callBack() throws RemoteException;
	}

	static class ClientImpl extends UnicastRemoteObject implements ClientInterface {
		ServerInterface fromStub;
		private static final long serialVersionUID = 1L;

		protected ClientImpl() throws RemoteException {
			super();
		}

		public void setFromStub(ServerInterface fromStub) {
			this.fromStub = fromStub;
		}

		@Override
		public void callBack() throws RemoteException {
			System.out.println("In client callback");
			if (fromStub != null) {
				fromStub.secondMethod();
			}
		}

	}

	public static void main(String[] args) throws NoSuchObjectException {
		ClientImpl c = null;
		try {
			Registry registry = LocateRegistry.getRegistry("127.0.0.1"/* localhost */, 49152);
			ServerInterface fromStub = (ServerInterface) registry.lookup("ServerObject");
			c = new ClientImpl();
			c.setFromStub(fromStub);
			fromStub.firstMethod(c);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		} finally {
			UnicastRemoteObject.unexportObject(c, true);
		}
	}
}
