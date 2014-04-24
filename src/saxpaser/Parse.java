package saxpaser;
import java.io.BufferedOutputStream;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.util.ArrayList;  
import java.util.List;  
  
import javax.xml.parsers.ParserConfigurationException;  
import javax.xml.parsers.SAXParser;  
import javax.xml.parsers.SAXParserFactory;  
import javax.xml.transform.OutputKeys;  
import javax.xml.transform.Result;  
import javax.xml.transform.Transformer;  
import javax.xml.transform.TransformerConfigurationException;  
import javax.xml.transform.sax.SAXTransformerFactory;  
import javax.xml.transform.sax.TransformerHandler;  
import javax.xml.transform.stream.StreamResult;  
  
import org.xml.sax.Attributes;  
import org.xml.sax.InputSource;  
import org.xml.sax.SAXException;  
import org.xml.sax.helpers.AttributesImpl;  
import org.xml.sax.helpers.DefaultHandler;  

import component.*;

/**
 * 
 * @author root
 * 参数为解析的xml路径，返回node信息
 */
public class Parse {
    public static List<Node> nodeList = null;  
    public static List<NetworkHardware> networkHardwareList = null;
    public static List<Application> appList = null;
    public static Node node = null;  
    public static NetworkHardware  networkhardware = null;
    public static Application app = null;
    private static String path_dir = null;
    
    /*
     * path_dir为xml绝对文件路径
     */
    public Parse(String path_dir){
    	this.path_dir = path_dir;
    }
    
    public void parseXml(){  
        String xmlPath = path_dir;    
     try {  
         //获取SAX分析器的工厂实例，专门负责创建SAXParser分析器  
         SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();  
         //获取SAXParser分析器的实例  
         SAXParser saxParser = saxParserFactory.newSAXParser();  
            InputStream inputStream = new FileInputStream(new File(xmlPath));    
           
         //解析xml文档  
         saxParser.parse(inputStream, new XmlSAXHandler());  
           
         //迭代list  
         if(Parse.appList.size() > 0){  
             for (Application n : Parse.appList) {  
                 System.out.println("-----------------------------------------");  
                 System.out.println("【type】" +n.getType());  
                 System.out.println("【name】" + n.getName());  
             }  
         }  
     } catch (ParserConfigurationException e) {  
         e.printStackTrace();  
     } catch (SAXException e) {  
         e.printStackTrace();  
     } catch (FileNotFoundException e) {  
    	 System.out.print("file not found!\n");
         e.printStackTrace();  
     } catch (IOException e) {  
         e.printStackTrace();  
     }  
 }
    
    
    class XmlSAXHandler extends DefaultHandler {    
        
        private String currentQName;  //因为startElement()才能获取到标签名称，但是标签的内容在characters()获取，而且他们的执行顺序一直是顺序的，所以可以使用currentQName来过渡一下获取上一次的标签名  
        private int parsenow = 0; 
        private boolean connectedNodes = false;
        
        @Override  
        public void startDocument() throws SAXException {  
            Parse.nodeList = new ArrayList<Node>();  
            Parse.networkHardwareList = new ArrayList<NetworkHardware>();
            Parse.appList = new ArrayList<Application>();
        }  
          
        @Override  
        public void endDocument() throws SAXException {  
              
        }  
          
        @Override  
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {  
        	
        	if(qName.equals("node")){  
                Parse.node = new Node();  
                this.parsenow = 1;
            }
        	else if(qName.equals("networkHardware")){
        		Parse.networkhardware = new NetworkHardware();
        		this.connectedNodes = false;
        		this.parsenow = 2;
        	} 
        	else if(qName.equals("application")){
        		Parse.app = new Application();
        		this.parsenow = 3;
        	}
            this.currentQName = qName;  
        }  
      
        @Override  
        public void endElement(String uri, String localName, String qName) throws SAXException {  

            if(qName.equals("node")){    
            	Parse.nodeList.add(Parse.node);  
            	Parse.node = null;  
            	this.parsenow = 0;
            }  
            else if(qName.equals("networkHardware")){    
            	Parse.networkHardwareList.add(Parse.networkhardware);  
            	Parse.networkhardware = null;  
            	this.connectedNodes = false;
            	this.parsenow = 0;
            } 
            else if(qName.equals("application")){    
            	Parse.appList.add(Parse.app);  
            	Parse.app = null;  
            	this.parsenow = 0;
            }
            this.currentQName = null;  
        }  
          
        @Override  
        public void characters(char[] ch, int start, int length) throws SAXException {  
            String content = new String(ch, start, length);  

            if(this.parsenow != 0 && currentQName != null){  
            	switch(parsenow){
            		case 1:
                        if(currentQName.equals("type")){  
                        	Parse.node.setType(content);  
                        }else if(currentQName.equals("nodeNbr")){  
                        	Parse.node.setNodeNbr(Integer.parseInt(content));  
                        }else if(currentQName.equals("name")){  
                        	Parse.node.setName(content);  
                        }else if(currentQName.equals("x")){  
                        	Parse.node.setX(Integer.parseInt(content));  
                        }else if(currentQName.equals("y")){  
                        	Parse.node.setY(Integer.parseInt(content));  
                        }
                        break;	
            		 case 2:
                         if(currentQName.equals("hidden")){  
                        	if(content.equals("false"))
                        		Parse.networkhardware.setHidden(false);
                        	else
                        		Parse.networkhardware.setHidden(true);
                         }else if(currentQName.equals("type")){  
                         	Parse.networkhardware.setType(content);  
                         }else if(currentQName.equals("name")){ 
                        	if(this.connectedNodes == false)
                        		Parse.networkhardware.setName(content); 
                        	else
                        		Parse.networkhardware.addConnectedNodes(content); 
                         }else if(currentQName.equals("dataRate")){  
                         	Parse.networkhardware.setDataRate(Long.parseLong(content));  
                         }else if(currentQName.equals("linkDelay")){  
                         	Parse.networkhardware.setLinkDelay(Integer.parseInt(content));  
                         }else if(currentQName.equals("enableTrace")){  
                         	if(content.equals("false"))
                        		Parse.networkhardware.setEnableTrace(false);
                        	else
                        		Parse.networkhardware.setEnableTrace(true);
                         }else if(currentQName.equals("tracePromisc")){  
                          	if(content.equals("false"))
                         		Parse.networkhardware.setTracePromisc(false);
                         	else
                         		Parse.networkhardware.setTracePromisc(true); 
                         }else if(currentQName.equals("iface")){  
                           	Parse.networkhardware.setIface(content);  
                         }else if(currentQName.equals("connectedNodes")){  
                           	this.connectedNodes = true;
                         }else if(currentQName.equals("mobility")){
                          	if(content.equals("false"))
                          		Parse.networkhardware.setMobility(false);
                         	else
                         		Parse.networkhardware.setMobility(true);             	 
                         }else if(currentQName.equals("hubPos")){  
                        	 Parse.networkhardware.setHubPos(content);      
                         }
                         break;
            		 case 3:
                         if(currentQName.equals("type")){  
                         	Parse.app.setType(content);  
                         }else if(currentQName.equals("name")){  
                         	Parse.app.setName(content);  
                         }else if(currentQName.equals("sender")){  
                         	Parse.app.setSender(content);  
                         }else if(currentQName.equals("receiver")){  
                          	Parse.app.setReceiver(content);  
                         }else if(currentQName.equals("startTime")){  
                          	Parse.app.setStartTime(Double.parseDouble(content));  
                         }else if(currentQName.equals("endTime")){  
                         	Parse.app.setEndTime(Double.parseDouble(content));  
                         }else if(currentQName.equals("port")){  
                         	Parse.app.setPort(Integer.parseInt(content));  
                         }      			 
            			 break;
            		 default:break;
                 
            			 
            	}
  
            }  
        }  
    }  

    public List<Node> getNode(){
    	parseXml();
    	return this.nodeList;
    }
    
    public List<NetworkHardware> getNetworkHardware(){	
    	return this.networkHardwareList;
    }
    
    public List<Application> getapp(){	
    	return this.appList;
    }
    
}
