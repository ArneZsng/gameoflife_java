package de.makaitghahramanianzeising.controller;

import de.makaitghahramanianzeising.enums.BoardTypeEnum;
import de.makaitghahramanianzeising.enums.ModeEnum;
import de.makaitghahramanianzeising.model.Cell;
import de.makaitghahramanianzeising.utils.FileParser;
import de.makaitghahramanianzeising.utils.ValidBean;
import de.makaitghahramanianzeising.view.ErrorMessageBox;
import de.makaitghahramanianzeising.view.SettingsSWT;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;

/**
 * Creates a settings view and asks the user for the game
 * settings. Validates the user's input. Throws a popup
 * with an error message if the input is not valid.
 */

public class Settings {
    
	private Display display;
	private SettingsSWT mySettingsSWT;
    private FileParser myFileParser;
    private ModeEnum myMode;
    private BoardTypeEnum myBoardType;
    private String myBoardFile;
    private ValidBean validBean;

    public Settings(Display display) {
    	this.display = display;
        validBean = new ValidBean(false, null);
        mySettingsSWT = new SettingsSWT(display);
        mySettingsSWT.addSelectFileListener(new SelectFileListener());
        mySettingsSWT.addCreateGameListener(new CreateGameListener());
        mySettingsSWT.start();
    }

    public ModeEnum getMode() {
        return myMode;
    }

    public BoardTypeEnum getBoardType() {
        return myBoardType;
    }

    public Cell[][] getBoard() {
        return myFileParser.getBoard();
    }

    public void reloadSettings() {
        validBean = new ValidBean(false, null);
        mySettingsSWT = new SettingsSWT(display);
        mySettingsSWT.reloadSettings(myBoardFile, myMode, myBoardType);
        mySettingsSWT.addSelectFileListener(new SelectFileListener());
        mySettingsSWT.addCreateGameListener(new CreateGameListener());
        mySettingsSWT.start();
    }
    
    public boolean isValid() {
        return validBean.isValid();
    }

    private void throwErrorMessage(ValidBean validBean) {
        try {
            throw validBean.getExceptionOnInvalid();
        } catch (Exception e) {
            new ErrorMessageBox(mySettingsSWT.getShell(), e.getMessage());
        }
    }

    private void saveSettings() {
        try {
            myMode = mySettingsSWT.getSelectedMode();
            myBoardType = mySettingsSWT.getSelectedBoardType();
            myBoardFile = mySettingsSWT.getFile();
            myFileParser = new FileParser(myBoardFile);
            myFileParser.parse();
            validBean = new ValidBean(true, null);
        } catch (Exception e) {
            validBean = new ValidBean(false, e);
        }
    }

    class SelectFileListener extends SelectionAdapter {
        public void widgetSelected(SelectionEvent e) {
            mySettingsSWT.selectFile();
        }
    }

    class CreateGameListener extends SelectionAdapter {
        public void widgetSelected(SelectionEvent e) {
            saveSettings();
            if (!isValid()) {
                throwErrorMessage(validBean);
            } else {
                mySettingsSWT.dispose();
            }
        }
    }
}
