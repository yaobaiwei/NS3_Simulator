package component;
/*
 * <node>
 * 	<type>Pc</type>
 * 	<nodeNbr>1</nodeNbr>
 * 	<name>term_0</name>
 * 	<nsc/>
 * 	<x>193</x>
 * 	<y>178</y>
 * </node>
 */
import java.util.List;  

/** 
 * Xml节点对象 
 * @author Administrator 
 * 
 */  
public class Node {  
  
    private String type;  
    private int nodeNbr;  
    private String name;  
    private int X;
    private int Y;
    private List<Node> nodeList;  
    
    private String nodeStructName = new String("NodeContainer");
    private String createOneNode = new String(".Create(1)");
    private String br = new String(";\n");
      
    public String getType() {  
        return type;  
    }  
    public void setType(String type) {  
        this.type = type;  
    }  

    public int getNodeNbr() {  
        return nodeNbr;  
    }  
    public void setNodeNbr(int nodenbr) {  
        this.nodeNbr = nodenbr;  
    }
    
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    } 
    
    public int getX() {  
        return X;  
    }  
    public void setX(int X) {  
        this.X = X;  
    }
    
    public int getY() {  
        return Y;  
    }  
    public void setY(int Y) {  
        this.Y = Y;  
    }  
    
	/*
	 *  
	 *  NodeContainer term_0;
			term_0.Create (1);
	 */
    public String bulidCode(){
    	return   nodeStructName+" "+this.name+br
    			+ this.name+createOneNode+br;
    }
} 

