package component;

import java.util.ArrayList;
import java.util.List;

public class Stop {
	public List<String> built_stopSIM_code(double time){
		  
		  List<String> codelist = new ArrayList<String>();
		  codelist.add("uint32_t stopTime = "+(int)time+";\n");
		  codelist.add("Simulator::Stop (Seconds (stopTime));\n");
		  return codelist;
	} 


}
