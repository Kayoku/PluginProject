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

public class Fenetre extends JFrame{
	
	protected JMenuBar menuBar;
	protected JMenu menuFile;
	protected JMenu menuTools;
	protected JMenu menuHelp;
	
	protected JTextArea textField;
	protected JScrollPane scroll;
	
	public static void main(String[] args){
		
	//	System.out.println("7454546.class".substring(0,"7454546.class".length()-6 ));
	//	System.out.println("7454546.class".substring("7454546.class".length() -6,"7454546.class".length() ));
		Fenetre pluginsProject = new Fenetre();
		File dir = new File("bin/plugins");
		PluginUpdater pluginUpdater = new PluginUpdater(dir, pluginsProject);
	}
	
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

	public void setText(String s){
		this.textField.setText(s);
	}

	public String getText(){
		return this.textField.getText();
	}
}
