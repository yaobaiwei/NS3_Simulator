package Client;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Runshell {
	public void Runbash(){
		Parameter P = new Parameter();
		
	    try{
	    	
	    	Process ps = Runtime.getRuntime().exec(P.CLIENT_XMLBASH_FILE,null,new File(P.CLIENT_XMLBASH_DIRECTORY));  
	    	ps.waitFor(); 
	    	
	    	BufferedReader errorReader = new BufferedReader(new InputStreamReader(  
	                ps.getInputStream()));  
	        String line = null;  
	        while ((line = errorReader.readLine()) != null) {  
	            System.out.println(line+"\n");  
	        } 
	        
	    	ps = Runtime.getRuntime().exec(P.CLIENT_TRBASH_FILE,null,new File(P.CLIENT_TRBASH_DIRECTORY));  
	    	ps.waitFor(); 
	    	
	    	errorReader = new BufferedReader(new InputStreamReader(ps.getInputStream()));   
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
