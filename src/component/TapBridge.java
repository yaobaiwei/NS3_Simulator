package component;

import java.util.ArrayList;
import java.util.List;

public class TapBridge {
	
	String TapBridgeHelper = new String("TapBridgeHelper");
	String preValueName = "tapBridge_";
	String br = ";\n";
	
	/**
	 *  TapBridgeHelper tapBridge_tap_0 (iface_ndc_tap_0.GetAddress(1));
  		tapBridge_tap_0.SetAttribute ("Mode", StringValue (mode_tap_0));
  		tapBridge_tap_0.SetAttribute ("DeviceName", StringValue (tapName_tap_0));
  		tapBridge_tap_0.Install (tap_0.Get(0), ndc_tap_0.Get(0));
	 * @param n
	 * @return
	 */
	public List<String> bulid_tapBridge_code(NetworkHardware n){
		
		List<String> codelist = new ArrayList<String>();
		
		codelist.add(TapBridgeHelper+" "+preValueName+n.getName()+" (iface_ndc_"+n.getName()+".GetAddress(1))"+br);
		codelist.add(preValueName+n.getName()+".SetAttribute (\"Mode\", StringValue (mode_"+n.getName()+"))"+br);
		codelist.add(preValueName+n.getName()+".SetAttribute (\"DeviceName\", StringValue (tapName_"+n.getName()+"))"+br);
		codelist.add(preValueName+n.getName()+".Install ("+n.getName()+".Get(0), ndc_"+n.getName()+".Get(0))"+br);
		
		return codelist;
	}

}
