package plugins;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Constructor;

/**
 * 
 * Classe permettant de filtrer les .class contenus dans "plugins" pour détecter 
 * lesquels sont valides, et lesquels ne le sont pas. 
 * En autre pour être valide, il faut :
 *  - Avoir un nom de fichier terminant par ".class"
 *  - Implémenter l'interface 'Plugin"
 *  - Faire parti du fichier "plugins".
 * 
 * @author BOUCHOUCHA Jordan & MAITROT Guillaume
 *
 */
public class PluginFilter implements FilenameFilter {

	/**
	 * Constructeur de PluginFilter.
	 */
	public PluginFilter(){}
	
	/**
	 * 
	 * Méthode de test de la classe.
	 * 
	 * @param f représente le fichier à tester
	 * @param name représente le nom du fichier
	 * @return True si le fichier est accepté comme un plugin, False si il ne l'est pas.
	 */
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
	
	/**
	 * 
	 * Méthode privé permettant de tester le nom d'un fichier.
	 * 
	 * @param name Correspond au nom d'un fichier
	 * @return True si la chaine termine par ".class"
	 */
	
	private boolean endWithClass(String name) {
		if(name.contains(".class")){
			String res = name.substring(name.length()-6, name.length());
			return res.equals(".class");
		} else {
			return false;
		}
	}
}
