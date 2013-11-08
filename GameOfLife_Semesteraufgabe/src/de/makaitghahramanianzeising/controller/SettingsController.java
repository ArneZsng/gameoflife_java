package de.makaitghahramanianzeising.controller;

/**
 * @author Hendrik Makait
 *
 */

import de.makaitghahramanianzeising.enums.BoardTypeEnum;
import de.makaitghahramanianzeising.enums.ModeEnum;
import de.makaitghahramanianzeising.model.Cell;
import de.makaitghahramanianzeising.utils.FileParser;
import de.makaitghahramanianzeising.utils.ValidBean;
import de.makaitghahramanianzeising.view.ErrorMessageBox;
import de.makaitghahramanianzeising.view.SettingsViewSWT;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;

public class SettingsController {
	private SettingsViewSWT mySettingsView;
	private FileParser myFileParser;
	private ModeEnum myModeEnum;
	private BoardTypeEnum myBoardTypeEnum;
	private ValidBean validBean;
	
	public SettingsController(Display display) {
		mySettingsView = new SettingsViewSWT(display);
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
			myFileParser.parse();
			validBean = new ValidBean(true, null);
		} catch (Exception e) {
			validBean = new ValidBean(false, e);
		}
	}

}
