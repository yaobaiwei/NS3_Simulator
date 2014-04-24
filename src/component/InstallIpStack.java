package component;

import java.util.ArrayList;
import java.util.List;


/**
 *   
 *
  InternetStackHelper internetStackH;
  internetStackH.Install (term_0);
  internetStackH.Install (emu_0);
  internetStackH.Install (tap_0);
  internetStackH.Install (ap_0);
  internetStackH.Install (station_0);
  internetStackH.Install (router_0);
  internetStackH.Install (term_1);
 * @author root
 *
 */

public class InstallIpStack {
	
	public String getIpStackCode(String name){
		return "internetStackH.Install ("+name+");\n";
	}
	
	public List<String> bulidIpStackCode(List<Node> nodelist){
		List<String> arraylist = new ArrayList<String>();
		
		arraylist.add("InternetStackHelper internetStackH;\n");
		for(Node n : nodelist){
			String name = n.getName();
			if(name.indexOf("bridge")>=0)
			{
				
			}
			else{
				String ipstack = getIpStackCode(name);
				arraylist.add(ipstack);
			}
			
		}
		return arraylist;
	}
}
