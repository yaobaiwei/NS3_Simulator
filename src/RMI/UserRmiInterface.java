package RMI;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
	 
	 
public interface UserRmiInterface extends Remote{ 
	  public String getxml(String name) throws RemoteException,IOException; 
	  public String gettrace() throws IOException; 
}