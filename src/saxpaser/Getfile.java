package saxpaser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import component.Application;
import component.Body;
import component.GenerateRoute;
import component.InstallIpStack;
import component.IpAssign;
import component.Link;
import component.NetworkHardware;
import component.Node;
import component.Stop;
import component.TapBridge;

import Client.Parameter;

public class Getfile {
	private Parse parse;
	private String path;     //要生成的cc文件制定的根目录
	private String ccTargetFileName; //要生成的cc文件名
	private String directory;     //模板文件的根目录
	private String xmlpath; //要解析的XML文件的根目录
	private String xmlTargetFileName ; //要解析的XML文件名

	private List<Node> nodelist = new ArrayList<Node>();
	
	private String[] EmuName = {"eth0"};  //界面中设置的所有Pc with Emu元件的名称
	private String[] TapName = {"tap0"};  //界面中设置的所有Pc With Tap元件的名称
	
	public Getfile(Parameter P){
		this.path = P.PATH;
		this.directory = P.DIRECTORY;
		this.xmlpath = P.XMLPATH;
		this.ccTargetFileName = P.CCTargetFileName;
		this.xmlTargetFileName = P.XMLTargetFileName;
		this.parse = new Parse(this.xmlpath+this.xmlTargetFileName);
		this.nodelist = this.parse.getNode();
	}
	
	/**
	 * 
	 * @param file_path  //文件路径+文件名
	 * @param stream //要写入的字符串
	 * @oaram add //true表示追加新内容，false表示更新
	 * function 输入想要生成的文件路径，生成最终的文件
	 */
	public void Inputfile(String file_path, String stream, boolean add) throws IOException{
		File file = new File(file_path);
		if(!file.exists())
			file.createNewFile();
		
        FileWriter fileWritter = new FileWriter(file,add);
        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
        StringBuffer sb=new StringBuffer();
        sb.append(stream);
        bufferWritter.write(sb.toString());
        bufferWritter.close();
	}
	
	/**
	 * 
	 * @param path   //文件路径
	 * @return   文件中的内容 
	 * @throws IOException
	 * function 输入文件的绝对路径，输出String型的文件内容
	 */
    public String FileRead(String path) throws IOException{

        File file=new File(path);
        
        if(!file.exists()||file.isDirectory())
            throw new FileNotFoundException();

        BufferedReader reader = null; 
        reader = new BufferedReader(new FileReader(file)); 
        
        StringBuffer sb=new StringBuffer();
        String tempString = null;
        
        while ((tempString = reader.readLine()) != null){ 
        	sb.append(tempString+"\n");   
        } 
        reader.close(); 
        
        return sb.toString();
    }
    
    
    public String path_dir(String filename){
    	return directory+filename;
    }
    
    
    public void formfile(){
    	try{
    		create_body_file(path_dir("/body"));
    		create_node_file(path_dir("/node"));
    		create_networkHardwares_file(path_dir("/network"));
    		create_link_file(path_dir("/link"));
    		create_ipstack_file(path_dir("/ipstack"));
    		create_ipAssign_file(path_dir("/ipassign"));
    		create_tapbridge_file(path_dir("/tapbridge"));
    		create_generateRoute_file(path_dir("/generateRoute"));
    		create_app_file(path_dir("/generateApp"));
    		create_trace_code(path_dir("/trace"));
    		create_stopSim_file(path_dir("/stopSim"));
    		
    		Inputfile(path+ccTargetFileName,FileRead(path_dir("/head19")),false);
    		Inputfile(path+ccTargetFileName,FileRead(path_dir("/body")),true);
    		Inputfile(path+ccTargetFileName,FileRead(path_dir("/node")),true); 
    		Inputfile(path+ccTargetFileName,FileRead(path_dir("/network")),true); 
    		Inputfile(path+ccTargetFileName,FileRead(path_dir("/link")),true); 
    		Inputfile(path+ccTargetFileName,FileRead(path_dir("/ipstack")),true); 
    		Inputfile(path+ccTargetFileName,FileRead(path_dir("/ipassign")),true); 
      		Inputfile(path+ccTargetFileName,FileRead(path_dir("/tapbridge")),true); 
      		Inputfile(path+ccTargetFileName,FileRead(path_dir("/generateRoute")),true); 
      		Inputfile(path+ccTargetFileName,FileRead(path_dir("/generateApp")),true); 
      		Inputfile(path+ccTargetFileName,FileRead(path_dir("/trace")),true); 
      		Inputfile(path+ccTargetFileName,FileRead(path_dir("/stopSim")),true); 
    		Inputfile(path+ccTargetFileName,FileRead(path_dir("/footer")),true);
 	
    	} catch (IOException e) {  
            e.printStackTrace();  
        }
    }
       
    public void create_node_file(String path) throws IOException{

    	Inputfile(path, "\n/* Build nodes. */\n",false);
        for (Node node : this.nodelist){  
        	//  NodeContainer term_0;
        	//  term_0.Create (1);
        	Inputfile(path,node.bulidCode(),true);
    	}
    }
    
    public void create_ipstack_file(String path) throws IOException{
    	InstallIpStack ipstack = new InstallIpStack();
    	List<String> ipstackList = ipstack.bulidIpStackCode(this.nodelist);
    	
    	Inputfile(path, "\n  /* Install the IP stack. */\n",false);

    	for(String s : ipstackList){
    		Inputfile(path,s,true);
    	}
    }
    
    public void create_networkHardwares_file(String path) throws IOException{
    	List<NetworkHardware> listNetworkHardware = this.parse.getNetworkHardware();

    	Inputfile(path, "\n  /* Build link. */\n",false);
        for (NetworkHardware networkHardware : listNetworkHardware){  
        	Inputfile(path,networkHardware.bulidCode(),true);
    	}
    }
    
    public void create_ipAssign_file(String path) throws IOException{
    	List<NetworkHardware> listNetworkHardware = this.parse.getNetworkHardware();
    	IpAssign ipassign = new IpAssign();
    	List<String> ipsetList = ipassign.bulidIpAssignCode(listNetworkHardware);
    	
    	Inputfile(path, "\n    /* IP assign. */\n",false);

    	for(String s : ipsetList){
    		Inputfile(path,s,true);
    	}
    }
    
    public void create_link_file(String path) throws IOException{
    	List<NetworkHardware> listNetworkHardware = this.parse.getNetworkHardware();
    	List<String> codelist = new ArrayList<String>();
    	Link link = new Link();
    	
    	Inputfile(path, "\n    /* Build link net device container. */\n",false);

    	for(NetworkHardware n : listNetworkHardware){
    		String name = n.getName();
    		if(name.indexOf("emu")==0)
    		{
    			codelist = link.bulid_emu_code(n);
    			for(String code : codelist){
    				Inputfile(path, code,true);
    			}
    		}
    		else if(name.indexOf("tap")==0)
    		{
    			codelist = link.bulid_tap_code(n);
    			for(String code : codelist){
    				Inputfile(path, code,true);
    			}
    		}
    		else if(name.indexOf("ap")==0)
    		{
    			codelist = link.bulid_Ap_code(n);
    			for(String code : codelist){
    				Inputfile(path, code,true);
    			}
    		}
    		else if(name.indexOf("p2p")==0)
    		{
    			codelist = link.bulid_p2p_code(n);
    			for(String code : codelist){
    				Inputfile(path, code,true);
    			}
    		}
    		else if(name.indexOf("bridge")==0)
    		{
    			codelist = link.bulid_bridge_code(n);
    			for(String code : codelist){
    				Inputfile(path, code,true);
    			}
    		}
    		else if(name.indexOf("hub")==0)
    		{
    			codelist = link.bulid_hub_code(n);
    			for(String code : codelist){
    				Inputfile(path, code,true);
    			}
    		}
    	}
    }
  
    public void create_tapbridge_file(String path) throws IOException{
    	List<NetworkHardware> listNetworkHardware = this.parse.getNetworkHardware();
    	List<String> codelist = new ArrayList<String>();
    	TapBridge tap = new TapBridge();
    	
    	Inputfile(path, "\n    /* Tap Bridge. */\n",false);

    	for(NetworkHardware n : listNetworkHardware){
    		String name = n.getName();
    		if(name.indexOf("tap")==0)
    		{
        		codelist =  tap.bulid_tapBridge_code(n);
        		for(String code : codelist){
        			Inputfile(path,code,true);
        		}
        		
    		}
    	}
    }
    
    public void create_generateRoute_file(String path) throws IOException{
    	GenerateRoute gr = new GenerateRoute();
    	List<String> codelist = new ArrayList<String>();
    	codelist = gr.bulid_GenerateRoute_code();
    	Inputfile(path, "\n    /* Generate Route. */\n",false);
    	for(String code : codelist){
    		Inputfile(path, code,true);
    	}
    }
    
    public void create_app_file(String path) throws IOException{
    	Application application = new Application();
    	List<Application> listapp = this.parse.getapp();

    	
    	List<NetworkHardware> listnetwork = this.parse.getNetworkHardware();
    	NetworkHardware ware = new NetworkHardware();
    	Inputfile(path, "\n    /* Generate Application. */\n",false);
    	for( Application app : listapp){
    		boolean out = false;
    		String receiver = app.getReceiver();
    		for(NetworkHardware net : listnetwork){
    			List<String> name = net.getConnectedNodes();
    			for(String node : name){
    				if(node.equals(receiver)){
    					ware = net;
    					out = true;
    					break;
    				}
    			}
    			if(out){
    				break;
    			}
    		}
    		if(app.getType().equals("TcpLargeTransfer")){
    			List<String> codelist = application.bulid_tcpapp_code(app,ware);
    			for(String code : codelist){
    				Inputfile(path, code,true); 			
    			}	
    			Inputfile(path, "\n\n",true);
    		}
    		else if(app.getType().equals("Ping")){
    			List<String> codelist = application.bulid_pingapp_code(app,ware);
    			for(String code : codelist){
    				Inputfile(path, code,true);			
    			}
    			Inputfile(path, "\n\n",true);
    		}
    		else if(app.getType().equals("UdpEcho")){
    			List<String> codelist = application.bulid_udpapp_code(app,ware);
    			for(String code : codelist){
    				Inputfile(path, code,true);
    			}	
    			Inputfile(path, "\n\n",true);
    		}
    	}
    	
    }
    
    public void create_stopSim_file(String path) throws IOException{
    	Stop stop = new Stop();
    	List<Application> applist = this.parse.getapp();
    	double time = 0 ;
    	for(Application app : applist){
    		if(time < app.getEndTime()){
    			time =  app.getEndTime();
    		}
    	}
    	time += 1;
    	
    	List<String> codelist = new ArrayList<String>();
    	codelist = stop.built_stopSIM_code(time);
    	
    	Inputfile(path, "\n    /* Stop the simulation after x seconds. */\n",false);
    	for(String code : codelist){
    		Inputfile(path, code,true);
    	}
    }
    
    public void create_body_file(String path) throws IOException{
    	Body body = new Body();
    	Inputfile(path, "    /*body*/\n",false);
    	List<String> codelist = new ArrayList<String>();
    	codelist = body.build_body_code(this.nodelist,EmuName,TapName);
    	for(String code : codelist){
    		Inputfile(path, code,true);
    	}
    	
    }
    
    public void create_trace_code(String path) throws IOException{
    	List<NetworkHardware> listNetworkHardware = this.parse.getNetworkHardware();
    	Inputfile(path, "    /*trace*/\n",false);
    	Inputfile(path, "    AsciiTraceHelper ascii;\n",true);
    	for(NetworkHardware n : listNetworkHardware){
    		String code = n.traceCode();
    		Inputfile(path, code,true);
    	}
    }
    
    
    
	public String getfile() throws IOException{
		formfile();
		return FileRead(path+ccTargetFileName);
	}
}
