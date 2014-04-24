package component;

import java.util.ArrayList;
import java.util.List;

/**
 *  
  Ipv4AddressHelper ipv4;
  ipv4.SetBase ("10.0.0.0", "255.255.255.0");
  Ipv4InterfaceContainer iface_ndc_emu_0 = ipv4.Assign (ndc_emu_0);
  ipv4.SetBase ("10.0.1.0", "255.255.255.0");
  Ipv4InterfaceContainer iface_ndc_tap_0 = ipv4.Assign (ndc_tap_0);
  ipv4.SetBase ("10.0.2.0", "255.255.255.0");
  Ipv4InterfaceContainer iface_ndc_ap_0 = ipv4.Assign (ndc_ap_0);
  ipv4.SetBase ("10.0.3.0", "255.255.255.0");
  Ipv4InterfaceContainer iface_ndc_hub_0 = ipv4.Assign (ndc_hub_0);
  ipv4.SetBase ("10.0.4.0", "255.255.255.0");
  Ipv4InterfaceContainer iface_ndc_bridge_0 = ipv4.Assign (ndc_bridge_0);
  ipv4.SetBase ("10.0.5.0", "255.255.255.0");
  Ipv4InterfaceContainer iface_ndc_hub_1 = ipv4.Assign (ndc_hub_1);
  ipv4.SetBase ("10.0.6.0", "255.255.255.0");
  Ipv4InterfaceContainer iface_ndc_hub_2 = ipv4.Assign (ndc_hub_2);
  ipv4.SetBase ("10.0.7.0", "255.255.255.0");
  Ipv4InterfaceContainer iface_ndc_hub_3 = ipv4.Assign (ndc_hub_3);
  ipv4.SetBase ("10.0.8.0", "255.255.255.0");
  Ipv4InterfaceContainer iface_ndc_hub_4 = ipv4.Assign (ndc_hub_4);
  ipv4.SetBase ("10.0.9.0", "255.255.255.0");
  Ipv4InterfaceContainer iface_ndc_hub_5 = ipv4.Assign (ndc_hub_5);
 * @author root
 *
 */
public class IpAssign {
	
	public String getIpAssignCode(int i , String name){
		return   "ipv4.SetBase (\"10.0."+i+".0\", \"255.255.255.0\");\n"+
		  "Ipv4InterfaceContainer iface_ndc_"+name+" = ipv4.Assign (ndc_"+name+");\n";
	}
	
	public List<String> bulidIpAssignCode(List<NetworkHardware> networklist){
		List<String> arraylist = new ArrayList<String>();
			
		arraylist.add("  Ipv4AddressHelper ipv4;\n");
		int i = 0;
		for(NetworkHardware n : networklist){
			String name = n.getName();

			String ipstack = getIpAssignCode(i++,name);
			arraylist.add(ipstack);			
		}
		return arraylist;
	}

}
