package plugins;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javafx.beans.InvalidationListener;

import javax.swing.JMenuItem;
import javax.swing.Timer;

import fenetre.Fenetre;

/**
 * 
 * Classe permetant de mettre à jour la fenêtre à chaque tic de timer.
 * On test périodiquement si il n'y a pas de nouveau plugin mis dans le 
 * fichier où sont présent les plugins (ou s'il n'y en a pas de supprimé) grâce
 * au timer lancé dans le constructeur.
 * 
 * @author BOUCHOUCHA Jordan & MAITROT Guillaume
 * @version 1.0
 */
public class PluginUpdater extends PluginFinder implements ActionListener {

	protected Timer timer;
	protected Fenetre fenetre;
	protected List<JMenuItem> OldList;
	
	/**
	 * 
	 * Constructeur de PluginUpdater
	 * 
	 * @param dir Répertoire où sont présent les plugins.
	 * @param fenetre Correspond à la fenêtre.
	 */
	public PluginUpdater(File dir, Fenetre fenetre){
		super(dir);
		this.fenetre = fenetre;
		this.timer = new Timer(5000, this);
		this.timer.start();
		this.OldList = getNewMenu();
		
		try {
			this.fenetre.update(getNewMenu());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * Méthode s'activant à chaque tic du timer.
	 * Elle teste si la liste des plugins n'a pas changé,
	 * si elle a changé, elle le fait remarqué à la fenêtre 
	 * qui procède également à la mise à jour.
	 */
	public void actionPerformed(ActionEvent e) {

		List<JMenuItem> listPlug = getNewMenu();
		
		if(!listPlug.equals(this.OldList)) {
			
			try {
				this.fenetre.update(listPlug);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			}
			
			this.OldList = listPlug;
		}
	}
	
	/**
	 * 
	 * Méthode privée permettant de retourner la liste des plugins accepté dans le répertoire.
	 * 
	 * @return Liste des plugins actuelles.
	 */
	private List<JMenuItem> getNewMenu(){
		
		List<JMenuItem> listPlug = new ArrayList<JMenuItem>();
		File[] files = getFiles();
		String nameWithoutClass;
		for(File file : files){

			// Si le fichier est accepté (Donc ".class")
			if(this.filter.accept(file, file.getName())){
				nameWithoutClass = file.getName();
				nameWithoutClass = nameWithoutClass.substring(0, nameWithoutClass.length()-6);
				listPlug.add(new JMenuItem(nameWithoutClass));
			}
			
		}
		
		return listPlug;
	}

}
