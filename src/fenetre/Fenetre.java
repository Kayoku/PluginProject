package fenetre;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Fenetre extends JFrame{
	
	protected JMenuBar menuBar;
	protected JMenu menuFile;
	protected JMenu menuTools;
	protected JMenu menuHelp;
	
	protected JTextArea textField;
	protected JScrollPane scroll;
	
	public static void main(String[] args){
		Fenetre pluginsProject = new Fenetre();
	}
	
	public Fenetre(){
		
		// Création de base de la fenêtre
		this.setTitle("Super plugin programme !");
		this.setSize(400, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Ajout de la barre de menu
		this.menuBar = new JMenuBar();
		this.menuFile = new JMenu("File");
		this.menuTools = new JMenu("Tools");
		this.menuHelp = new JMenu("Help");
		
		this.menuBar.add(this.menuFile);
		this.menuBar.add(this.menuTools);
		this.menuBar.add(this.menuHelp);
		
		this.setJMenuBar(this.menuBar);
		
		// Création du champ de texte
		this.textField = new JTextArea();
		this.scroll = new JScrollPane(this.textField);
		this.scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.getContentPane().add(this.scroll, BorderLayout.CENTER);
		
		// Affiche la fenêtre
		this.setVisible(true);

	}
}
