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
	protected List<JMenuItem> OldList;
	
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
