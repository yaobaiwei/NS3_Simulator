package Server;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import Client.Parameter;

public class Runshell {
	
	public void Runbash(){

		Parameter P = new Parameter();
	    try{
	    	System.out.print("RUNNING THE SERVER SHELL!\n");
	    	
	    	Process ps = Runtime.getRuntime().exec(P.SERVER_BASH_FILE,null,new File(P.SERVER_BASH_DIRECTORY));  	
	    	ps.waitFor(); 
	    	
	    	BufferedReader errorReader = new BufferedReader(new InputStreamReader(  
	                ps.getInputStream()));  
	        String line = null;  
	        while ((line = errorReader.readLine()) != null) {  
	            System.out.println(line+"\n");  
	        }  
	    	
		}
		catch (IOException e){
        	e.printStackTrace();
        } 
		catch(InterruptedException e){
		   e.printStackTrace();
		}
	}

}
