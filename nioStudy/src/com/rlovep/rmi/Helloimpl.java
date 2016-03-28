package com.rlovep.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Helloimpl extends UnicastRemoteObject implements IHello{

	protected Helloimpl() throws RemoteException {
		super();
		
	}

	/**
	* @Fields serialVersionUID : TODO
	*/
	
	private static final long serialVersionUID = 1L;

	@Override
	public String sayHello(String name) throws RemoteException {
		
		return "Hello"+name;
	}
	public static void main(String[] args) {
		try {
			IHello hello=new Helloimpl();
			LocateRegistry.createRegistry(1099);
			Naming.rebind("rmi://localhost:1099/hello",hello);
			System.out.println("Ready......");
		} catch (RemoteException e) {
			
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
