package plugins;

import java.io.File;

import javafx.beans.Observable;

/**
 * 
 * La classe PluginFinder permet de trouver les plugins et de les récupérer dans 
 * le fichier "plugins".
 * 
 * @author BOUCHOUCHA Jordan & MAITROT Guillaume
 * @version 1.0
 */
public abstract class PluginFinder {

	protected File dir;
	protected PluginFilter filter;
	
	/**
	 * 
	 * Constructeur de PluginFinder.
	 * 
	 * @param dir représente le répertoire où sont situés les plugins.
	 */
	public PluginFinder(File dir) {
		this.dir = dir;
		this.filter = new PluginFilter();
	}
	
	/**
	 * 
	 * Permet de récupérer la liste des fichiers valides.
	 * 
	 * @return La liste des fichiers valides.
	 */
	public File[] getFiles() {
		return dir.listFiles(filter);
	}
	
	
}
