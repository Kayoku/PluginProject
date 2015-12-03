package plugins;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Constructor;

public class PluginFilter implements FilenameFilter {

	public PluginFilter(){}
	
	public boolean accept(File f, String name) {

		if (endWithClass(name)) {

			String nameWithoutClass = name.substring(0,name.length()-6);
			Class<?> c = null ;
			try {
				c = Class.forName("plugins."+nameWithoutClass);
			} catch (ClassNotFoundException e) {				
				e.printStackTrace();
				return false ;
			}
			
			Class<?> plugin = null;
			try {
				plugin = Class.forName("plugins.Plugin");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return false ;
			}
			
			if (plugin.isAssignableFrom(c)){

				if (c.getPackage().equals(plugin.getPackage())) { 

					@SuppressWarnings("rawtypes")
					Constructor[] t = c.getConstructors() ;
					if(t.length > 0 && t[0].getParameterCount() == 0 ){
						return true ;
					}
				}
			}
			
		}
		return false ;
	}
	
	private boolean endWithClass(String name) {
		if(name.contains(".class")){
			String res = name.substring(name.length()-6, name.length());
			return res.equals(".class");
		} else {
			return false;
		}
	}
}
