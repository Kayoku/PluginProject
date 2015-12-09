package fenetre;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import plugins.Plugin;
import plugins.PluginListener;
import plugins.PluginUpdater;

/**
 * 
 * La classe "Fenetre" est une classe qui étend la classe JFrame permettant la création
 * de la fenêtre, ainsi que son affichage. C'est aussi elle qui s'occupe de mettre à 
 * jour les listes de plugins, ainsi que le texte affiché lors de l'activation d'un plugin.
 * 
 * @author BOUCHOUCHA Jordan & MAITROT Guillaume
 * @version 1.0
 */

public class Fenetre extends JFrame{
	
	protected JMenuBar menuBar;
	protected JMenu menuFile;
	protected JMenu menuTools;
	protected JMenu menuHelp;
	
	protected JTextArea textField;
	protected JScrollPane scroll;
	
	/**
	 * 
	 * Fonction principale. On instancie simplement une fenêtre et on lui 
	 * indique ou sont les plugins.
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		
	//	System.out.println("7454546.class".substring(0,"7454546.class".length()-6 ));
	//	System.out.println("7454546.class".substring("7454546.class".length() -6,"7454546.class".length() ));
		Fenetre pluginsProject = new Fenetre();
		File dir = new File("bin/plugins");
		PluginUpdater pluginUpdater = new PluginUpdater(dir, pluginsProject);
	}
	
	/**
	 * Constructeur de fenetre.
	 */
	public Fenetre(){
		
		// Cr�ation de base de la fen�tre
		this.setTitle("Super plugin programme !");
		this.setSize(400, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Ajout de la barre de menu
		this.menuBar = new JMenuBar();
		this.menuTools = new JMenu("Tools");
		this.menuBar.add(this.menuTools);
		
		this.setJMenuBar(this.menuBar);
		
		// Cr�ation du champ de texte
		this.textField = new JTextArea();
		this.scroll = new JScrollPane(this.textField);
		this.scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.getContentPane().add(this.scroll, BorderLayout.CENTER);
		
		// Affiche la fen�tre
		this.setVisible(true);

	}
	
	
	/**
	 * 
	 * Permet de mettre à jour la barre des plugins dans le menu. Il est appelé à la création de la fenêtre
	 * et dès qu'il y a un changement (soit un ajout de plugin, soit une suppression) grâce au timer. (voir PluginUpdater)
	 * 
	 * @param list Correspond à la liste des plugins à mettre dans la barre de menu.
	 * @throws ClassNotFoundException Si la classe n'est pas trouvé. (dans les plugins)
	 * @throws InstantiationException Si la classe est mal instancié.
	 * @throws IllegalAccessException Si l'accès à la classe n'est pas autorisé.
	 */
	public void update(List<JMenuItem> list) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		
		this.menuTools.removeAll();
		
		for(JMenuItem item : list){
			Class c;
			c =  Class.forName("plugins."+item.getText());
			item.addActionListener(new PluginListener((Plugin) c.newInstance(),this));
			item.setEnabled(true);
			this.menuTools.add(item);
		}
		this.menuTools.setEnabled(true);
		this.menuBar.add(this.menuTools);
	}

	/**
	 * 
	 * Met à jour la texte contenu dans le champ de texte.
	 * 
	 * @param s
	 */
	public void setText(String s){
		this.textField.setText(s);
	}

	/**
	 * 
	 * Permet de récupérer le contenu du champ de texte.
	 * 
	 * @return Le texte contenu dans le champ de texte.
	 */
	public String getText(){
		return this.textField.getText();
	}
}
