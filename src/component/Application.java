package component;

import java.util.ArrayList;
import java.util.List;

public class Application {
	
	private String type;
	private String name;
	private String sender;
	private String receiver;
	private double startTime;
	private double endTime;
	private int port;
	
	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type = type;
	}
	
	public String getName(){
		return this.name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getSender(){
		return this.sender;
	}
	public void setSender(String sender){
		this.sender = sender;
	}
	
	public String getReceiver(){
		return this.receiver;
	}
	public void setReceiver(String n){
		this.receiver = n;
	}
	
	public double getStartTime(){
		return this.startTime;
	}
	public void setStartTime(double t){
		this.startTime = t;
	}
	
	public double getEndTime(){
		return this.endTime;
	}
	public void setEndTime(double t){
		this.endTime = t;
	}
	
	public int getPort(){
		return this.port;
	}
	public void setPort(int p){
		this.port = p;
	}
	
	
	/**
	 *   
  InetSocketAddress dst_ping_0 = InetSocketAddress (iface_ndc_hub_0.GetAddress(2));
  OnOffHelper onoff_ping_0 = OnOffHelper ("ns3::Ipv4RawSocketFactory", dst_ping_0);
  onoff_ping_0.SetAttribute ("OnTime", StringValue ("ns3::ConstantRandomVariable[Constant=1]"));
  onoff_ping_0.SetAttribute ("OffTime", StringValue ("ns3::ConstantRandomVariable[Constant=0]"));
  ApplicationContainer apps_ping_0 = onoff_ping_0.Install(term_1.Get(0));
  apps_ping_0.Start (Seconds (3.1));
  apps_ping_0.Stop (Seconds (5.1));
  PacketSinkHelper sink_ping_0 = PacketSinkHelper ("ns3::Ipv4RawSocketFactory", dst_ping_0);
  apps_ping_0 = sink_ping_0.Install (term_5.Get(0));
  apps_ping_0.Start (Seconds (3.0));
  apps_ping_0.Stop (Seconds (5.2));
  V4PingHelper ping_ping_0 = V4PingHelper(iface_ndc_hub_0.GetAddress(2));
  apps_ping_0 = ping_ping_0.Install(term_1.Get(0));
  apps_ping_0.Start (Seconds (3.2));
  apps_ping_0.Stop (Seconds (5.0));
	 */
	
	public List<String> bulid_pingapp_code(Application a,NetworkHardware n){
		String ping = a.getName();
		List<String> codelist = new ArrayList<String>();
		List<String> getConnectedNodes = n.getConnectedNodes();
		int i = 0;
		for(String name : getConnectedNodes){
			if(name.equals(a.getReceiver())){
				break;
			}else{
				i++;
			}
		}
		
		codelist.add(" InetSocketAddress dst_"+ping+" = InetSocketAddress (iface_ndc_"+n.getName()+".GetAddress("+String.valueOf(i)+"));\n");
		codelist.add(" OnOffHelper onoff_"+ping+" = OnOffHelper (\"ns3::Ipv4RawSocketFactory\", dst_"+ping+");\n");
		codelist.add(" onoff_"+ping+".SetAttribute (\"OnTime\", StringValue (\"ns3::ConstantRandomVariable[Constant=1]\"));\n");
		codelist.add(" onoff_"+ping+".SetAttribute (\"OffTime\", StringValue (\"ns3::ConstantRandomVariable[Constant=0]\"));\n");
		codelist.add(" ApplicationContainer apps_"+ping+" = onoff_"+ping+".Install("+a.getSender()+".Get(0));\n");
		codelist.add(" apps_"+ping+".Start (Seconds ("+String.valueOf(a.getStartTime()+0.1)+"));\n");
		codelist.add(" apps_"+ping+".Stop (Seconds ("+String.valueOf(a.getEndTime()+0.1)+"));\n");
		codelist.add(" PacketSinkHelper sink_"+ping+" = PacketSinkHelper (\"ns3::Ipv4RawSocketFactory\", dst_"+ping+");\n");
		codelist.add(" apps_"+ping+" = sink_"+ping+".Install ("+a.getReceiver()+".Get(0));\n");
		codelist.add(" apps_"+ping+".Start (Seconds ("+String.valueOf(a.getStartTime())+"));\n");
		codelist.add(" apps_"+ping+".Stop (Seconds ("+String.valueOf(a.getEndTime()+0.2)+"));\n");
		codelist.add(" V4PingHelper ping_"+ping+" = V4PingHelper(iface_ndc_"+n.getName()+".GetAddress("+String.valueOf(i)+"));\n");
		codelist.add(" apps_"+ping+" = ping_"+ping+".Install("+a.getSender()+".Get(0));\n");
		codelist.add(" apps_"+ping+".Start (Seconds ("+String.valueOf(a.getStartTime()+0.2)+"));\n");
		codelist.add(" apps_"+ping+".Stop (Seconds ("+String.valueOf(a.getEndTime())+"));\n");
		  
		return codelist;
	}
	
	/**
	 *   
  uint16_t port_tcp_0 = 4;
  Address sinkLocalAddress_tcp_0 (InetSocketAddress (Ipv4Address::GetAny (), port_tcp_0));
  PacketSinkHelper sinkHelper_tcp_0 ("ns3::TcpSocketFactory", sinkLocalAddress_tcp_0);
  ApplicationContainer sinkApp_tcp_0 = sinkHelper_tcp_0.Install (term_2);
  sinkApp_tcp_0.Start (Seconds (2.0));
  sinkApp_tcp_0.Stop (Seconds (3.0));
  OnOffHelper clientHelper_tcp_0 ("ns3::TcpSocketFactory", Address ());
  clientHelper_tcp_0.SetAttribute ("OnTime", StringValue ("ns3::ConstantRandomVariable[Constant=1]"));
  clientHelper_tcp_0.SetAttribute ("OffTime",StringValue ("ns3::ConstantRandomVariable[Constant=0]"));
  ApplicationContainer clientApps_tcp_0;
  AddressValue remoteAddress_tcp_0 (InetSocketAddress (iface_ndc_hub_0.GetAddress (1), port_tcp_0));
  clientHelper_tcp_0.SetAttribute ("Remote", remoteAddress_tcp_0);
  clientApps_tcp_0.Add (clientHelper_tcp_0.Install (term_1));
  clientApps_tcp_0.Start (Seconds (2.0));
  clientApps_tcp_0.Stop (Seconds (3.0));
  
  
  
  UdpEchoServerHelper echoServer (9);

  ApplicationContainer serverApps = echoServer.Install (nodes.Get (1));
  serverApps.Start (Seconds (1.0));
  serverApps.Stop (Seconds (10.0));

  UdpEchoClientHelper echoClient (interfaces.GetAddress (1), 9);
  echoClient.SetAttribute ("MaxPackets", UintegerValue (1));
  echoClient.SetAttribute ("Interval", TimeValue (Seconds (1.)));
  echoClient.SetAttribute ("PacketSize", UintegerValue (1024));

  ApplicationContainer clientApps = echoClient.Install (nodes.Get (0));
  clientApps.Start (Seconds (2.0));
  clientApps.Stop (Seconds (10.0));
  
	 */
	public List<String> bulid_tcpapp_code(Application a,NetworkHardware n){
		List<String> codelist = new ArrayList<String>();
		List<String> getConnectedNodes = n.getConnectedNodes();
		int i = 0;
		for(String name : getConnectedNodes){
			if(name.equals(a.getReceiver())){
				break;
			}else{
				i++;
			}
		}
		
		codelist.add(" uint16_t port_"+a.getName()+" = "+a.getPort()+";\n");
		codelist.add(" Address sinkLocalAddress_"+a.getName()+" (InetSocketAddress (Ipv4Address::GetAny (), port_"+a.getName()+"));\n");
		codelist.add(" PacketSinkHelper sinkHelper_"+a.getName()+" (\"ns3::TcpSocketFactory\", sinkLocalAddress_"+a.getName()+");\n");
		codelist.add(" ApplicationContainer sinkApp_"+a.getName()+" = sinkHelper_"+a.getName()+".Install ("+a.getReceiver()+");\n");
		codelist.add(" sinkApp_"+a.getName()+".Start (Seconds ("+a.getStartTime()+"));\n");
		codelist.add(" sinkApp_"+a.getName()+".Stop (Seconds ("+a.getEndTime()+"));\n");
		codelist.add(" OnOffHelper clientHelper_"+a.getName()+" (\"ns3::TcpSocketFactory\", Address ());\n");
		codelist.add(" clientHelper_"+a.getName()+".SetAttribute (\"OnTime\", StringValue (\"ns3::ConstantRandomVariable[Constant=1]\"));\n");
		codelist.add(" clientHelper_"+a.getName()+".SetAttribute (\"OffTime\", StringValue (\"ns3::ConstantRandomVariable[Constant=0]\"));\n");
		codelist.add(" ApplicationContainer clientApps_"+a.getName()+";\n");
		codelist.add(" AddressValue remoteAddress_"+a.getName()+" (InetSocketAddress (iface_ndc_"+n.getName()+".GetAddress ("+String.valueOf(i)+"), port_"+a.getName()+"));\n");
		codelist.add(" clientHelper_"+a.getName()+".SetAttribute (\"Remote\", remoteAddress_"+a.getName()+");\n");
		codelist.add(" clientApps_"+a.getName()+".Add (clientHelper_"+a.getName()+".Install ("+a.getSender()+"));\n");
		codelist.add(" clientApps_"+a.getName()+".Start (Seconds ("+a.getStartTime()+"));\n");
		codelist.add(" clientApps_"+a.getName()+".Stop (Seconds ("+a.getEndTime()+"));\n");
		
		
		codelist.add("\n\n");
		codelist.add("UdpEchoServerHelper echoServer ("+a.getPort()+");\n");
		codelist.add("ApplicationContainer serverApps = echoServer.Install ("+a.getReceiver()+");\n");
		codelist.add("serverApps.Start (Seconds ("+a.getStartTime()+"));\n");
		codelist.add("serverApps.Stop (Seconds ("+a.getEndTime()+"));\n");
		codelist.add("UdpEchoClientHelper echoClient (iface_ndc_"+n.getName()+".GetAddress ("+String.valueOf(i)+"), "+a.getPort()+");\n");
		codelist.add("echoClient.SetAttribute (\"MaxPackets\", UintegerValue (3));\n");
		codelist.add("echoClient.SetAttribute (\"Interval\", TimeValue (Seconds (2.)));\n");
		codelist.add("echoClient.SetAttribute (\"PacketSize\", UintegerValue (1024));\n");
		codelist.add("ApplicationContainer clientApps = echoClient.Install ("+a.getSender()+");\n");
		codelist.add("clientApps.Start (Seconds ("+a.getEndTime()+1+"));\n");
		codelist.add("clientApps.Stop (Seconds ("+a.getEndTime()+"));\n");
		  
		return codelist;
	}
	
	public List<String> bulid_udpapp_code(Application a,NetworkHardware n){
		List<String> codelist = new ArrayList<String>();
		List<String> getConnectedNodes = n.getConnectedNodes();
		int i = 0;
		for(String name : getConnectedNodes){
			if(name.equals(a.getReceiver())){
				break;
			}else{
				i++;
			}
		}
		
		codelist.add(" uint16_t port_"+a.getName()+" = "+a.getPort()+";\n");
		codelist.add(" Address sinkLocalAddress_"+a.getName()+" (InetSocketAddress (Ipv4Address::GetAny (), port_"+a.getName()+"));\n");
		codelist.add(" PacketSinkHelper sinkHelper_"+a.getName()+" (\"ns3::UdpSocketFactory\", sinkLocalAddress_"+a.getName()+");\n");
		codelist.add(" ApplicationContainer sinkApp_"+a.getName()+" = sinkHelper_"+a.getName()+".Install ("+a.getReceiver()+");\n");
		codelist.add(" sinkApp_"+a.getName()+".Start (Seconds ("+a.getStartTime()+"));\n");
		codelist.add(" sinkApp_"+a.getName()+".Stop (Seconds ("+a.getEndTime()+"));\n");
		codelist.add(" OnOffHelper clientHelper_"+a.getName()+" (\"ns3::UdpSocketFactory\", Address ());\n");
		codelist.add(" clientHelper_"+a.getName()+".SetAttribute (\"OnTime\", StringValue (\"ns3::ConstantRandomVariable[Constant=1]\"));\n");
		codelist.add(" clientHelper_"+a.getName()+".SetAttribute (\"OffTime\", StringValue (\"ns3::ConstantRandomVariable[Constant=0]\"));\n");
		codelist.add(" ApplicationContainer clientApps_"+a.getName()+";\n");
		codelist.add(" AddressValue remoteAddress_"+a.getName()+" (InetSocketAddress (iface_ndc_"+n.getName()+".GetAddress ("+String.valueOf(i)+"), port_"+a.getName()+"));\n");
		codelist.add(" clientHelper_"+a.getName()+".SetAttribute (\"Remote\", remoteAddress_"+a.getName()+");\n");
		codelist.add(" clientApps_"+a.getName()+".Add (clientHelper_"+a.getName()+".Install ("+a.getSender()+"));\n");
		codelist.add(" clientApps_"+a.getName()+".Start (Seconds ("+a.getStartTime()+"));\n");
		codelist.add(" clientApps_"+a.getName()+".Stop (Seconds ("+a.getEndTime()+"));\n");
		
		
		codelist.add("\n\n");
		codelist.add("UdpEchoServerHelper echoServer ("+a.getPort()+");\n");
		codelist.add("ApplicationContainer serverApps = echoServer.Install ("+a.getReceiver()+");\n");
		codelist.add("serverApps.Start (Seconds ("+a.getStartTime()+"));\n");
		codelist.add("serverApps.Stop (Seconds ("+a.getEndTime()+"));\n");
		codelist.add("UdpEchoClientHelper echoClient (iface_ndc_"+n.getName()+".GetAddress ("+String.valueOf(i)+"), "+a.getPort()+");\n");
		codelist.add("echoClient.SetAttribute (\"MaxPackets\", UintegerValue (3));\n");
		codelist.add("echoClient.SetAttribute (\"Interval\", TimeValue (Seconds (2.)));\n");
		codelist.add("echoClient.SetAttribute (\"PacketSize\", UintegerValue (1024));\n");
		codelist.add("ApplicationContainer clientApps = echoClient.Install ("+a.getSender()+");\n");
		codelist.add("clientApps.Start (Seconds ("+a.getEndTime()+1+"));\n");
		codelist.add("clientApps.Stop (Seconds ("+a.getEndTime()+"));\n");
		
		return codelist;
	}

}
