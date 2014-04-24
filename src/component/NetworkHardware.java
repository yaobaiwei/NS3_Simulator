package component;

import java.util.ArrayList;
import java.util.List;

/*
 * 
 * <networkHardware>
 * 	<hidden>true</hidden>
 * 	<type>Hub</type>
 * 	<name>hub_0</name>
 * 	<dataRate>100000000</dataRate>
 * 	<linkDelay>10000</linkDelay>
 * 	<enableTrace>false</enableTrace>
 * 	<tracePromisc>false</tracePromisc>
 * 	<none/>	
 * 	<connectedNodes>
 * 		<name>router_0</name>
 * 		<name>term_0</name>
 * 	</connectedNodes>
 * </networkHardware>
 *
 */
public class NetworkHardware {
	private boolean hidden;
	private String type;
	private String name;
	private long dataRate;
	private int linkDelay;
	private boolean enableTrace;
	private boolean tracePromisc;
	private String iface;
	private boolean mobility;
	private String hubpos;
	
	private List<String> connectnodes = new ArrayList<String>();
	
	
	private String csmaStructName = "CsmaHelper";
	private String csmapreValueName = "csma_";
	private String wifiStructName = "YansWifiPhyHelper";
	private String wifipreValueName = "wifiPhy_";
	private String wifiChannelName = "YansWifiChannelHelper";
	private String wifiChannelpreValueName = "wifiChannel_";
	private String p2pStructName = "PointToPointHelper";
	private String p2ppreValueName = "p2p_";
	private String emuStructName = "EmuHelper";
	private String emupreValueName = "emu_";
	
	private String settrace = new String();
	private String filename = "ns3"; //设置要生成的trace文件的名称
	

	
	private String br = ";\n";
	
	public boolean getHidden(){
		return this.hidden;
	}
	public void setHidden(boolean hidden){
		this.hidden = hidden;
	}
	
	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type = type;
	}
	
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	public long getDataRate(){
		return this.dataRate;
	}
	public void setDataRate(long datarate){
		this.dataRate = datarate;
	}

	public int getLinkDelay(){
		return this.linkDelay;
	}
	public void setLinkDelay(int linkdelay){
		this.linkDelay = linkdelay;
	}
	
	public boolean getEnableTrace(){
		return this.enableTrace;
	}
	public void setEnableTrace(boolean enabletrace){
		this.enableTrace = enabletrace;
	}
	
	public boolean getTracePromisc(){
		return this.tracePromisc;
	}
	public void setTracePromisc(boolean t){
		this.tracePromisc = t;
	}
	
	public String getIface(){
		return this.iface;
	}	
	public void setIface(String iface){
		this.iface = iface;
	}
	
	public boolean getMobility(){
		return this.mobility;
	}
	public void setMobility(boolean content){
		this.mobility = content;
	}
	
	public String getHubPos(){
		return this.hubpos;
	}	
	public void setHubPos(String hub){
		this.hubpos = hub;
	}
	
	public List<String> getConnectedNodes(){
		return this.connectnodes;
	}
	public void addConnectedNodes(String cnn1){
		this.connectnodes.add(cnn1);
	}
	
	public String getTraceCode(){
		return this.settrace;
	}

	
	/**
	 * 
	 * @return
	 *   CsmaHelper csma_hub_0;
  		 csma_hub_0.SetChannelAttribute ("DataRate", DataRateValue (100000000));
  		 csma_hub_0.SetChannelAttribute ("Delay",  TimeValue (MilliSeconds (10000)));
	 */
	public String bulidHub_Tap_Bridge_Code(long DataRate, int Delay){
		
		this.settrace = csmapreValueName+this.name+".EnableAsciiAll (ascii.CreateFileStream (\""+this.filename+".tr\"));\n";
		
		return csmaStructName+" "+csmapreValueName+this.name+br
				+csmapreValueName+this.name
				+".SetChannelAttribute (\"DataRate\", DataRateValue ("+DataRate+"))"+br
				+csmapreValueName+this.name
				+".SetChannelAttribute (\"Delay\", TimeValue (MilliSeconds ("+Delay+")))"+br;
	}
	
	/**
	 *  YansWifiPhyHelper wifiPhy_ap_0 = YansWifiPhyHelper::Default ();
  		YansWifiChannelHelper wifiChannel_ap_0 = YansWifiChannelHelper::Default ();
  		wifiPhy_ap_0.SetChannel (wifiChannel_ap_0.Create ());
	 * @return
	 */
	public String bulidApCode(){
		
		this.settrace = wifipreValueName+this.name+".EnableAsciiAll (ascii.CreateFileStream (\""+this.filename+".tr\"));\n";
		
		return wifiStructName+" "+wifipreValueName+this.name
				+" = "+wifiStructName+"::Default ()"+br
				+wifiChannelName+" "+wifiChannelpreValueName+this.name
				+" = "+wifiChannelName+"::Default ()"+br
				+wifipreValueName+this.name+".SetChannel ("+wifiChannelpreValueName
				+this.name+".Create ())"+br;
	}
	
	/**
	 *   PointToPointHelper p2p_p2p_0;
  		 p2p_p2p_0.SetDeviceAttribute ("DataRate", DataRateValue (100000000));
  		 p2p_p2p_0.SetChannelAttribute ("Delay", TimeValue (MilliSeconds (10000)));
	 * @return
	 */
	public String bulidP2PCode(long DataRate, int Delay){
		
		this.settrace = p2ppreValueName+this.name+".EnableAsciiAll (ascii.CreateFileStream (\""+this.filename+".tr\"));\n";
		
		return p2pStructName+" "+p2ppreValueName+this.name+br
				+p2ppreValueName+this.name+
				".SetDeviceAttribute (\"DataRate\", DataRateValue ("+DataRate+"))"+br
				+p2ppreValueName+this.name
				+".SetChannelAttribute (\"Delay\", TimeValue (MilliSeconds ("+Delay+")))"+br;
	}
	
	/**
	 *   EmuHelper emu_0;
  		 emu_0.SetAttribute ("DeviceName", StringValue (emuDevice_emu_0));
	 * @return
	 */
	public String bulidEmuCode(){
		
		this.settrace = emupreValueName+this.name+".EnableAsciiAll (ascii.CreateFileStream (\""+this.filename+".tr\"));\n";
		
		return emuStructName+" "+emupreValueName+this.name+br
				+emupreValueName+this.name+
				".SetAttribute (\"DeviceName\", StringValue (emuDevice_"+this.name+"))"+br;
	}
	
    public String bulidCode(){
    	if(this.name.indexOf("emu")>=0){
    		return bulidEmuCode();
    	}else if(this.name.indexOf("tap")>=0){
    		return bulidHub_Tap_Bridge_Code(this.dataRate,this.linkDelay);
    	}else if(this.name.indexOf("ap")>=0){
    		return bulidApCode();
    	}else if(this.name.indexOf("hub")>=0){
    		return bulidHub_Tap_Bridge_Code(this.dataRate,this.linkDelay);
    	}else if(this.name.indexOf("p2p")>=0){
    		return bulidP2PCode(this.dataRate,this.linkDelay);
    	}else if(this.name.indexOf("bridge")>=0){
    		return bulidHub_Tap_Bridge_Code(this.dataRate,this.linkDelay);
    	}else{
    		return "";
    	}
    }
    
    public String traceCode(){
    	return this.settrace;
    }
	
}
