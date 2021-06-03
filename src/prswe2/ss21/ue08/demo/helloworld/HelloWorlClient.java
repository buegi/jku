package prswe2.ss21.ue08.demo.helloworld;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class HelloWorlClient {

	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.getRegistry("127.0.0.1"/* localhost */,49152);
			HelloWorld fromStub = (HelloWorld) registry.lookup("ServerObject");
			System.out.printf("Server says %s  [current thread=%s]\n", fromStub.produceHelloString(),
					Thread.currentThread().getName());
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
}
