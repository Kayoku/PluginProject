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

public class PluginUpdater extends PluginFinder implements ActionListener {

	protected Timer timer;
	protected Fenetre fenetre;
	
	public PluginUpdater(File dir, Fenetre fenetre){
		super(dir);
		this.fenetre = fenetre;
		this.timer = new Timer(200, this);
		this.timer.start();
	}
	
	public void actionPerformed(ActionEvent e) {
		List<JMenuItem> listPlug = new ArrayList<JMenuItem>();
		File[] files = getFiles();
		String nameWithoutClass;
		System.out.println(files.length);
		for(File file : files){
			
			System.out.print(this.filter.accept(file, file.getName()));
			// Si le fichier est accepté (Donc ".class")
//			if(this.filter.accept(file, file.getName())){
				nameWithoutClass = file.getName();
				nameWithoutClass = nameWithoutClass.substring(0, nameWithoutClass.length()-6);
				listPlug.add(new JMenuItem(nameWithoutClass));
//			}
			
		}
		
		this.fenetre.update(listPlug);
				
	}

}
