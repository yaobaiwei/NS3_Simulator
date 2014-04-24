package component;

import java.util.ArrayList;
import java.util.List;

public class GenerateRoute {
	public List<String> bulid_GenerateRoute_code(){
		
		List<String> codelist = new ArrayList<String>();
		
		codelist.add("Ipv4GlobalRoutingHelper::PopulateRoutingTables ();\n");
		
		return codelist;
	}
}
