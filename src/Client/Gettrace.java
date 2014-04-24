package Client;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import saxpaser.Getfile;
import RMI.UserRmiInterface;

import component.FileOperation;

public class Gettrace {
	
    public static void main(String []args){ 
        
        try { 
        	Parameter P =new Parameter();
        	Getfile file = new Getfile(P);
        	String filecode = file.getfile();
        	Runshell runshell = new Runshell();
        	FileOperation fileoperation = new FileOperation();
            Registry registry = LocateRegistry.getRegistry("localhost",522); 
            UserRmiInterface trace = (UserRmiInterface) registry.lookup("send"); 
           
            System.out.print("CLIENT IS RUNNING!\n");
            
            String xml = trace.getxml(filecode);
            String tr = trace.gettrace();
            fileoperation.Inputfile(P.CLIENT_XML_FILE,xml,false);
            fileoperation.Inputfile(P.CLIENT_TRACE_FILE,tr,false);
            
            System.out.print("RESULT IS BACK!\n");
            runshell.Runbash();
            
        } catch (RemoteException e) { 
            e.printStackTrace(); 
        } catch (NotBoundException e) { 
            e.printStackTrace(); 
        } catch (IOException e) {
        	e.printStackTrace();
        }  
    }

}
