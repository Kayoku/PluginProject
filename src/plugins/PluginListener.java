package plugins;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fenetre.Fenetre;

public class PluginListener implements ActionListener{

	private Plugin plugin;
	private Fenetre fenetre;
	
	public PluginListener(Plugin plugin,  Fenetre fenetre) {
		this.plugin = plugin;
		this.fenetre = fenetre;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		String s = this.fenetre.getText();
		s = this.plugin.transform(s);
		this.fenetre.setText(s);
	}

}
