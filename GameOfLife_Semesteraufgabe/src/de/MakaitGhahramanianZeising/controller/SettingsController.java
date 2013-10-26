package de.MakaitGhahramanianZeising.controller;

/**
 * @author Hendrik Makait
 *
 */

import de.MakaitGhahramanianZeising.enums.BoardTypeEnum;
import de.MakaitGhahramanianZeising.enums.ModeEnum;
import de.MakaitGhahramanianZeising.model.Cell;
import de.MakaitGhahramanianZeising.utils.FileParser;
import de.MakaitGhahramanianZeising.utils.ValidBean;
import de.MakaitGhahramanianZeising.view.ErrorMessageBox;
import de.MakaitGhahramanianZeising.view.SettingsViewSWT;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class SettingsController {
	private SettingsViewSWT mySettingsView;
	private FileParser myFileParser;
	private ModeEnum myModeEnum;
	private BoardTypeEnum myBoardTypeEnum;
	private ValidBean validBean;
	
	public SettingsController() {
		mySettingsView = new SettingsViewSWT();
		mySettingsView.addSelectFileListener(new SelectFileListener());
		mySettingsView.addCreateGameListener(new CreateGameListener());
		mySettingsView.start();
	}
	
	public Cell[][] getBoard() {
		return myFileParser.getBoard();
	}
	
	public ModeEnum getMode() {
		return myModeEnum;
	}
	
	public BoardTypeEnum getBoardType() {
		return myBoardTypeEnum;
	}
	
	class SelectFileListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			mySettingsView.selectFile();
		}
	}
	
	class CreateGameListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			saveSettings();
			if (! validBean.isValid()) {
				throwErrorMessage(validBean);
			} else {
				mySettingsView.dispose();
			}
		}
	}
		
	private void throwErrorMessage(ValidBean validBean) {
		try {
			throw validBean.getExceptionOnInvalid();
		} catch (Exception e) {
			new ErrorMessageBox(mySettingsView.getShell(), e.getMessage());
		}
	}
	
	private void saveSettings() {
		try {
			myModeEnum = mySettingsView.getSelectedMode();
			myBoardTypeEnum = mySettingsView.getSelectedBoardType();
			myFileParser = new FileParser(mySettingsView.getFilePath());
			validBean = new ValidBean(true, null);
		} catch (Exception e) {
			validBean = new ValidBean(false, e);
		}
	}

}
