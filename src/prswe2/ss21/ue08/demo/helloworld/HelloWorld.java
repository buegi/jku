package prswe2.ss21.ue08.demo.helloworld;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloWorld extends Remote {
	String produceHelloString() throws RemoteException;
}
