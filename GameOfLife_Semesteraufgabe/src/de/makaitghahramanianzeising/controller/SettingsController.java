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

/**
 * Creates a settings view and asks the user for the game
 * settings. Validates the user's input. Throws a popup
 * with an error message if the input is not valid.
 */

public class SettingsController {
    private SettingsViewSWT mySettingsView;
    private FileParser myFileParser;
    private ModeEnum myModeEnum;
    private BoardTypeEnum myBoardTypeEnum;
    private ValidBean validBean;

    public SettingsController(Display display) {
        validBean = new ValidBean(false, null);
        mySettingsView = new SettingsViewSWT(display);
        mySettingsView.addSelectFileListener(new SelectFileListener());
        mySettingsView.addCreateGameListener(new CreateGameListener());
        mySettingsView.start();
    }
    
    public void reset() {
        validBean = new ValidBean(false, null);
        mySettingsView.start();
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

    class SelectFileListener extends SelectionAdapter {
        public void widgetSelected(SelectionEvent e) {
            mySettingsView.selectFile();
        }
    }

    class CreateGameListener extends SelectionAdapter {
        public void widgetSelected(SelectionEvent e) {
            saveSettings();
            if (!isValid()) {
                throwErrorMessage(validBean);
            } else {
               mySettingsView.dispose();
            }
        }
    }
}
