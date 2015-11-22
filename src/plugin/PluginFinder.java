package plugin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class PluginFinder implements ActionListener {

	
	protected File dir;
	protected PluginFilter filter;
	
	public PluginFinder(File dir) {
		this.dir = dir;
		this.filter = new PluginFilter();
	}
	
	public File[] getFiles() {
		return dir.listFiles(filter);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
