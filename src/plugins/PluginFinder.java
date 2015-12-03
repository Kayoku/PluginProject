package plugins;

import java.io.File;

import javafx.beans.Observable;


public abstract class PluginFinder {

	protected File dir;
	protected PluginFilter filter;
	
	public PluginFinder(File dir) {
		this.dir = dir;
		this.filter = new PluginFilter();
	}
	
	public File[] getFiles() {
		return dir.listFiles(filter);
	}
	
	
}
