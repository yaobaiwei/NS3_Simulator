package component;

import java.util.ArrayList;
import java.util.List;

public class Body {
	
	
	/**
	 * using namespace ns3;

		int main(int argc, char *argv[])
	{
  		std::string emuDevice_emu_0 = "eth0";

  		std::string mode_tap_0 = "ConfigureLocal";

  		std::string tapName_tap_0 = "tap0";

  		CommandLine cmd;
  		cmd.AddValue("deviceName_emu_0", "device name", emuDevice_emu_0);
  		cmd.AddValue ("mode_tap_0", "Mode Setting of TapBridge", mode_tap_0);
  		cmd.AddValue ("tapName_tap_0", "Name of the OS tap device", tapName_tap_0);
  		cmd.Parse (argc, argv);

  		\/* Configuration. *\/
  		GlobalValue::Bind ("SimulatorImplementationType", StringValue ("ns3::RealtimeSimulatorImpl"));
  		GlobalValue::Bind ("ChecksumEnabled", BooleanValue (true));
	 * @param nodelist
	 * @return
	 */
	public List<String> build_body_code(List<Node> nodelist,String[] EmuName, String[] TapName){
		List<String> codelist = new ArrayList<String>();
		int i=0,j=0;
		boolean check = false;
		codelist.add("using namespace ns3;\n\n");
		codelist.add("int main(int argc, char *argv[])\n");
		codelist.add("{\n");
		codelist.add("CommandLine cmd;\n");
		for(Node node : nodelist){
			if(node.getName().indexOf("emu")>=0){
				check = true;
				codelist.add("std::string emuDevice_"+node.getName()+" = \""+EmuName[i++]+"\";\n");
	            codelist.add("cmd.AddValue(\"deviceName_"+node.getName()+"\", \"device name\", emuDevice_"+node.getName()+");\n");		
			}
			else if(node.getName().indexOf("tap")>=0){
				check = true;
				codelist.add("std::string mode_"+node.getName()+" = \"ConfigureLocal\";\n");
				codelist.add("std::string tapName_"+node.getName()+" = \""+TapName[j++]+"\";\n");
				codelist.add("cmd.AddValue (\"mode_"+node.getName()+"\", \"Mode Setting of TapBridge\", mode_"+node.getName()+");\n");
	            codelist.add("cmd.AddValue (\"tapName_"+node.getName()+"\", \"Name of the OS tap device\", tapName_"+node.getName()+");\n");		
			}
		}
		codelist.add("cmd.Parse (argc, argv);\n\n");
		if(check){
			codelist.add("\n  /* Configuration. */\n");
			codelist.add("GlobalValue::Bind (\"SimulatorImplementationType\", StringValue (\"ns3::RealtimeSimulatorImpl\"));\n");
			codelist.add("GlobalValue::Bind (\"ChecksumEnabled\", BooleanValue (true));\n");
		}
		return codelist;
	} 
	
}
