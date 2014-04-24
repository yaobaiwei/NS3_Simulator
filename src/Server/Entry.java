package Server;

import java.rmi.AlreadyBoundException; 
import java.rmi.RemoteException; 
import java.rmi.registry.LocateRegistry; 
import java.rmi.registry.Registry; 
import java.rmi.server.UnicastRemoteObject; 

import RMI.UserRmiInterface;

public class Entry {
	
    public static void main(String []args) throws AlreadyBoundException, RemoteException{ 
 	 
    	SendTrace send=new  SendTrace(); 
    	UserRmiInterface sendInterface=(UserRmiInterface)UnicastRemoteObject.exportObject(send,0); 
        Registry registry = LocateRegistry.createRegistry(522); 
        registry.rebind("send", sendInterface); 
        System.out.print("SERVER IS RUNNING!!!\n");
   } 
}
