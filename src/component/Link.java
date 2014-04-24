package component;

import java.util.ArrayList;
import java.util.List;

public class Link {
	private String Container = "NodeContainer";
	private String preValueName = "all_";
	private String Device = "NetDeviceContainer";
	private String preDeviceName = "ndc_";
	private String preCsmaName = "csma_";
	private String emupreValueName = "emu_";
	private String p2ppreValueName = "p2p_";
	private String br = ";\n";
	private int wifinum = 0;
	
	public List<String> bulid_emu_code(NetworkHardware n){
		List<String> addlist = new ArrayList<String>();
		List<String> codelist = new ArrayList<String>();
		
		addlist = n.getConnectedNodes();
		codelist.add(Container+" "+preValueName+n.getName()+br);
		for(String name : addlist){
			if(name.indexOf("ap")==0){
				
			}
			else if(name.indexOf("hub")==0){
				codelist.add(preValueName+n.getName()+".Add ("+preValueName+name+")"+br);
			}
			else{
				codelist.add(preValueName+n.getName()+".Add ("+name+")"+br);
			}
		}
		codelist.add(Device+" "+preDeviceName+n.getName()+
				" = "+emupreValueName+n.getName()+".Install ("+preValueName+n.getName()+")"+br);
		
		return codelist;
	}
	
	public List<String> bulid_tap_code(NetworkHardware n){
		List<String> addlist = new ArrayList<String>();
		List<String> codelist = new ArrayList<String>();
		
		addlist = n.getConnectedNodes();
		codelist.add(Container+" "+preValueName+n.getName()+br);
		for(String name : addlist){
			if(name.indexOf("ap")==0){
				
			}			
			else if(name.indexOf("hub")==0){
				codelist.add(preValueName+n.getName()+".Add ("+preValueName+name+")"+br);
			}
			else{
				codelist.add(preValueName+n.getName()+".Add ("+name+")"+br);
			}
		}
		codelist.add(Device+" "+preDeviceName+n.getName()+
				" = "+preCsmaName+n.getName()+".Install ("+preValueName+n.getName()+")"+br);
		
		return codelist;
	}
	
	public List<String> bulid_hub_code(NetworkHardware n){
		List<String> addlist = new ArrayList<String>();
		List<String> codelist = new ArrayList<String>();
		
		addlist = n.getConnectedNodes();
		codelist.add(Container+" "+preValueName+n.getName()+br);
		for(String name : addlist){
			if(name.indexOf("ap")==0){
				
			}			
			else if(name.indexOf("hub")==0){
				codelist.add(preValueName+n.getName()+".Add ("+preValueName+name+")"+br);
			}
			else{
				codelist.add(preValueName+n.getName()+".Add ("+name+")"+br);
			}
		}
		codelist.add(Device+" "+preDeviceName+n.getName()+
				" = "+preCsmaName+n.getName()+".Install ("+preValueName+n.getName()+")"+br);
		
		return codelist;
	}
	
	/**
	 *   NodeContainer all_p2p_1;
         all_p2p_1.Add (station_0);
         all_p2p_1.Add (term_0);
         NetDeviceContainer ndc_p2p_1 = p2p_p2p_1.Install (all_p2p_1);
	 */
	public List<String> bulid_p2p_code(NetworkHardware n){
		List<String> addlist = new ArrayList<String>();
		List<String> codelist = new ArrayList<String>();
		
		addlist = n.getConnectedNodes();
		codelist.add(Container+" "+preValueName+n.getName()+br);
		for(String name : addlist){
			if(name.indexOf("ap")==0){
				
			}			
			else if(name.indexOf("hub")==0){
				codelist.add(preValueName+n.getName()+".Add ("+preValueName+name+")"+br);
			}
			else{
				codelist.add(preValueName+n.getName()+".Add ("+name+")"+br);
			}
		}
		codelist.add(Device+" "+preDeviceName+n.getName()+
				" = "+p2ppreValueName+n.getName()+".Install ("+preValueName+n.getName()+")"+br);
		
		return codelist;
	}
	/**
	 *   
  NodeContainer all_ap_0;
  NetDeviceContainer ndc_ap_0;
  Ssid ssid_ap_0 = Ssid ("wifi-default-0");
  WifiHelper wifi_ap_0 = WifiHelper::Default ();
  NqosWifiMacHelper wifiMac_ap_0 = NqosWifiMacHelper::Default ();
  wifi_ap_0.SetRemoteStationManager ("ns3::ArfWifiManager");
  wifiMac_ap_0.SetType ("ns3::StaWifiMac", 
     "Ssid", SsidValue (ssid_ap_0), 
     "ActiveProbing", BooleanValue (false));
  ndc_ap_0.Add (wifi_ap_0.Install (wifiPhy_ap_0, wifiMac_ap_0, ap_0));
  wifiMac_ap_0.SetType ("ns3::ApWifiMac",
     "Ssid", SsidValue (ssid_ap_0));
  ndc_ap_0.Add (wifi_ap_0.Install (wifiPhy_ap_0, wifiMac_ap_0, all_ap_0 ));
  MobilityHelper mobility_ap_0;
  mobility_ap_0.SetMobilityModel ("ns3::ConstantPositionMobilityModel");
  mobility_ap_0.Install (ap_0);
  mobility_ap_0.Install(all_ap_0);
	 * @param n
	 * @return
	 */
	public List<String> bulid_Ap_code(NetworkHardware n){
		List<String> addlist = new ArrayList<String>();
		List<String> codelist = new ArrayList<String>();
		
		addlist = n.getConnectedNodes();
		codelist.add(Container+" "+preValueName+n.getName()+br);
		codelist.add(Device+" "+preDeviceName+n.getName()+br);
		codelist.add("Ssid ssid_"+n.getName()+" = Ssid (\"wifi-default-"+String.valueOf(wifinum)+"\")"+br);
		codelist.add("WifiHelper wifi_"+n.getName()+" = WifiHelper::Default ()"+br);
        codelist.add("NqosWifiMacHelper wifiMac_"+n.getName()+" = NqosWifiMacHelper::Default ()"+br);
        codelist.add("wifi_"+n.getName()+".SetRemoteStationManager (\"ns3::ArfWifiManager\")"+br);
        codelist.add("wifiMac_"+n.getName()+".SetType (\"ns3::StaWifiMac\","+ 
        		"\"Ssid\", SsidValue (ssid_"+n.getName()+"),"+ 
        		"\"ActiveProbing\", BooleanValue (false))"+br);
        codelist.add("ndc_"+n.getName()+".Add (wifi_"+n.getName()+".Install (wifiPhy_"+n.getName()+", wifiMac_"+n.getName()+", "+n.getName()+"))"+br);
        codelist.add("wifiMac_"+n.getName()+".SetType (\"ns3::ApWifiMac\","+ 
        		"\"Ssid\", SsidValue (ssid_"+n.getName()+"))"+br);
        codelist.add("ndc_"+n.getName()+".Add (wifi_"+n.getName()+".Install (wifiPhy_"+n.getName()+", wifiMac_"+n.getName()+", "+preValueName+n.getName()+"))"+br);
        codelist.add("MobilityHelper mobility_"+n.getName()+br);
        codelist.add("mobility_"+n.getName()+".SetMobilityModel (\"ns3::ConstantPositionMobilityModel\")"+br);
        codelist.add("mobility_"+n.getName()+".Install ("+n.getName()+")"+br);
        codelist.add("mobility_"+n.getName()+".Install (all_"+n.getName()+")"+br);
		
        wifinum++;
		return codelist;
	}
	
	/**
	 * 
	   NodeContainer all_bridge_0;
  all_bridge_0.Add (station_0);
  all_bridge_0.Add (term_1);
  NetDeviceContainer terminalDevices_bridge_0;
  NetDeviceContainer BridgeDevices_bridge_0;
  for (int i = 0; i < 2; i++)
  {
   NetDeviceContainer link = csma_bridge_0.Install(NodeContainer(all_bridge_0.Get(i), bridge_0));
   terminalDevices_bridge_0.Add (link.Get(0));
   BridgeDevices_bridge_0.Add (link.Get(1));
  }
  BridgeHelper bridge_bridge_0;
  bridge_bridge_0.Install (bridge_0.Get(0), BridgeDevices_bridge_0);
  NetDeviceContainer ndc_bridge_0 = terminalDevices_bridge_0;
	 */
	public List<String> bulid_bridge_code(NetworkHardware n){
		List<String> addlist = new ArrayList<String>();
		List<String> codelist = new ArrayList<String>();
		
		addlist = n.getConnectedNodes();
		codelist.add(Container+" "+preValueName+n.getName()+br);
		int i = 0;
		for(String name : addlist){
			if(name.indexOf("ap")==0){
				
			}
			else if(name.indexOf("hub")==0){
				codelist.add(preValueName+n.getName()+".Add ("+preValueName+name+")"+br);
			}
			else{
				i++;
				codelist.add(preValueName+n.getName()+".Add ("+name+")"+br);
			}
		}
		codelist.add(Device+" terminalDevices_"+n.getName()+br);
		codelist.add(Device+" BridgeDevices_"+n.getName()+br);
		codelist.add("for (int i = 0; i < "+String.valueOf(i)+"; i++)\n");
		codelist.add("{\n");
		
		codelist.add("NetDeviceContainer link = csma_"+n.getName()+".Install(NodeContainer(all_"+n.getName()+".Get(i), "+n.getName()+"))"+br);
		codelist.add("terminalDevices_"+n.getName()+".Add (link.Get(0))"+br);
		codelist.add("BridgeDevices_"+n.getName()+".Add (link.Get(1))"+br);
		
		codelist.add("}\n");
		codelist.add("BridgeHelper bridge_"+n.getName()+br);
		codelist.add("bridge_"+n.getName()+".Install ("+n.getName()+".Get(0), BridgeDevices_"+n.getName()+")"+br);
		codelist.add("NetDeviceContainer ndc_"+n.getName()+" = terminalDevices_"+n.getName()+br);
		return codelist;
	}
	
	
}
