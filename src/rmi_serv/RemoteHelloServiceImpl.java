package rmi_serv;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;

public class RemoteHelloServiceImpl implements RemoteHelloService {
	public static final String BINDING_NAME = "sample/HelloService";
	 
	public Object sayHello(String name) {
		String string = "Hello, " + name + "! It is " + System.currentTimeMillis() + " ms now";
		try {
			System.out.println(name + " from " + UnicastRemoteObject.getClientHost());
		} catch (ServerNotActiveException e) {
			System.out.println(e.getMessage());
		}
		if ("Killer".equals(name)) {
			System.out.println("Shutting down...");
			System.exit(1);
		}
		return string;
	}
 
	public static void main(String... args) throws Exception {
		System.out.print("Starting registry...");
		final Registry registry = LocateRegistry.createRegistry(2099);
		System.out.println(" OK");
 
		final RemoteHelloService service = new RemoteHelloServiceImpl();
		Remote stub = UnicastRemoteObject.exportObject(service, 0);
 
		System.out.print("Binding service...");
		registry.bind(BINDING_NAME, stub);
		System.out.println(" OK");
 
		while (true) {
			Thread.sleep(Integer.MAX_VALUE);
		}
	}
}
