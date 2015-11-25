package plugin;

import java.io.File;
import java.io.FilenameFilter;

public class PluginFilter implements FilenameFilter {

	public PluginFilter(){}
	
	public boolean accept(File f, String name) {
		return endWithClass(name);
	}
	
	private boolean endWithClass(String name) {
		if(name.contains(".class")){
			return true;
		} else {
			return false;
		}
	}
}
