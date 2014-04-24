package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;

import Client.Parameter;
import RMI.UserRmiInterface;
import component.FileOperation;

public class SendTrace implements UserRmiInterface { 
	
	private FileOperation file = new FileOperation();
	private Parameter P= new Parameter();
	private Runshell runshell = new Runshell();
	
    public SendTrace() throws RemoteException { 
    } 
    
    private static final long serialVersionUID = -3111492742628447261L; 
    
    	public String getxml(String code) throws RemoteException,IOException{
    		file.Inputfile(P.SERVER_SCRATCH+P.SERVER_CCTargetFileName,code,false);
    		runshell.Runbash();    		
    		return file.FileRead(P.SERVER_XML_FILE);
    	}
    	
    	public String gettrace()throws IOException{
    		return file.FileRead(P.SERVER_TRACE_FILE);
    	}
}
