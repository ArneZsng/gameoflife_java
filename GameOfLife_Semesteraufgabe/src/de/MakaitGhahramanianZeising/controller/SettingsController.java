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
