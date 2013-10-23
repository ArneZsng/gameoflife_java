package de.MakaitGhahramanianZeising.controller;

/**
 * @author Hendrik Makait
 *
 */

import de.MakaitGhahramanianZeising.utils.FileParser;
import de.MakaitGhahramanianZeising.view.SettingsViewSWT;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class SettingsController {
	private SettingsViewSWT mySettingsView;
	private FileParser myFileParser;
	
	public SettingsController() {
		mySettingsView = new SettingsViewSWT();
		mySettingsView.addSelectFileListener(new SelectFileListener());
		mySettingsView.addCreateGameListener(new CreateGameListener());
		mySettingsView.start();
		myFileParser = new FileParser("/Users/Arne/Dropbox/Nordakademie/Module/I143 - Praxis der Softwareentwicklung/Semesteraufgabe/gols/5by5.gol");
	}
	
	class SelectFileListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			mySettingsView.selectFile();
		}
	}
	
	class CreateGameListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			mySettingsView.dispose();
		}
	}
}
