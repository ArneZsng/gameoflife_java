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

    private SettingsSWT mySettingsSWT;
    private FileParser myFileParser;
    private ModeEnum myModeEnum;
    private BoardTypeEnum myBoardTypeEnum;
    private ValidBean validBean;

    public Settings(Display display) {
        validBean = new ValidBean(false, null);
        mySettingsSWT = new SettingsSWT(display);
        mySettingsSWT.addSelectFileListener(new SelectFileListener());
        mySettingsSWT.addCreateGameListener(new CreateGameListener());
        mySettingsSWT.start();
    }

    public void reset() {
        validBean = new ValidBean(false, null);
        mySettingsSWT.start();
    }

    public ModeEnum getMode() {
        return myModeEnum;
    }

    public BoardTypeEnum getBoardType() {
        return myBoardTypeEnum;
    }

    public Cell[][] getBoard() {
        return myFileParser.getBoard();
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
            myModeEnum = mySettingsSWT.getSelectedMode();
            myBoardTypeEnum = mySettingsSWT.getSelectedBoardType();
            myFileParser = new FileParser(mySettingsSWT.getFilePath());
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
