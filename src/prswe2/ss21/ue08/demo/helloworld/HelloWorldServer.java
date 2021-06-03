package prswe2.ss21.ue08.demo.helloworld;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class HelloWorldServer {

	// directly as unicast remote object or explicitly export via
	// HelloWorld stub = (HelloWorld) UnicastRemoteObject.exportObject(remoteObject,
	// );
	//
	// advantage the unicast remote object already knows what to do
	static class Hello extends UnicastRemoteObject implements HelloWorld {

		private static final long serialVersionUID = 7441997817700172717L;

		protected Hello(int port) throws RemoteException {
			super(port);
		}

		@Override
		public String produceHelloString() {
			Thread current = Thread.currentThread();
			StringBuilder sb = new StringBuilder();
			sb.append("Hello World from thread").append(current.getName()).append(" !");
			return sb.toString();
		}

	}

	public static void main(String[] args) {
		try {
			Hello remoteObject = new Hello(49152);
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
