package plugins;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fenetre.Fenetre;


/**
 * 
 * La classe PluginListener permet d'écouter chacun des boutons
 * correspondant à un plugin dans la barre de menu.
 * 
 * @author BOUCHOUCHA Jordan & MAITROT Guillaume
 * @version 1.0
 */
public class PluginListener implements ActionListener{

	private Plugin plugin;
	private Fenetre fenetre;
	
	/**
	 * 
	 * Constructeur de PluginListener.
	 * 
	 * @param plugin Correspond a la classe implémentant le plugin.
	 * @param fenetre Correspond à la fenêtre.
	 */
	public PluginListener(Plugin plugin,  Fenetre fenetre) {
		this.plugin = plugin;
		this.fenetre = fenetre;
	}
	
	/**
	 * 
	 * Méthode appelé lors du clique sur le bouton du plugin dans la barre de menu.
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		
		String s = this.fenetre.getText();
		s = this.plugin.transform(s);
		this.fenetre.setText(s);
	}

}
