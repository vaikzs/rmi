package rmi_serv;

import java.rmi.*;

public interface RemoteHelloService extends Remote {

	Object sayHello(String name) throws RemoteException;
	
}