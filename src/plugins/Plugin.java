package plugins;


/**
 * 
 * Interface permettant de se servir des plugins dans le fichier "plugins".
 * 
 * @author BOUCHOUCHA Jordan & MAITROT Guillaume
 *
 */
public interface Plugin {
	 public String transform(String s) ;
	 public String getLabel() ; 
}
