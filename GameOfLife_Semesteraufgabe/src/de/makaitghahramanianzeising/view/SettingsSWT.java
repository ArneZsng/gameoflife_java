package de.makaitghahramanianzeising.view;

/**
 * @author Hendrik Makait
 *
 */

import java.util.EnumSet;

import de.makaitghahramanianzeising.enums.BoardTypeEnum;
import de.makaitghahramanianzeising.enums.ModeEnum;
import de.makaitghahramanianzeising.exceptions.GOLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * Renders the settings view to allow the user
 * configure the game prior to start. Allows
 * selecting the game mode, the board type
 * and the initial configuration of the board.
 */

public class SettingsSWT {

    private final Shell shell;
    private final Display display;
    private Button btnSelectFile;
    private Button btnCreateGame;
    private Combo comboMode;
    private Combo comboBoard;
    private Composite compBoardType;
    private Composite compGameMode;
    private Composite compInitialBoard;
    private Label lblBoardFileName;
    private FileDialog dlgFileSelector;
    private ModeEnum[] availableModes;
    private BoardTypeEnum[] availableBoardTypes;
    private String filePath;


    public SettingsSWT(Display display) {
        this.display = display;
        shell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.MIN);
        shell.setText("Game of Life");
        shell.setSize(400, 400);
        init();
    }

    private void init() {
        shell.setLayout(new GridLayout());
        initGameModeComposite();
        initBoardTypeComposite();
        initInitialBoardComposite();
        initBtnCreateGame();
    }

    private void initGameModeComposite() {
        compGameMode = new Composite(shell, SWT.NULL);
        compGameMode.setLayout(new GridLayout());

        Label lblGameMode = new Label(compGameMode, SWT.NONE);
        lblGameMode.setText("Bitte wählen Sie den Spielmodus:");

        initComboForGameMode();
    }

    private void initComboForGameMode() {
        EnumSet<ModeEnum> modes =  EnumSet.allOf(ModeEnum.class);
        comboMode = new Combo(compGameMode, SWT.READ_ONLY);
        availableModes = modes.toArray(new ModeEnum[modes.size()]);
        String[] modesString = new String[modes.size()];
        int i = 0;
        for (ModeEnum mode : modes) {
            modesString[i] = mode.getName();
            i++;
        }
        comboMode.setItems(modesString);
        comboMode.select(0);
    }

    private void initBoardTypeComposite() {
        compBoardType = new Composite(shell, SWT.NULL);
        compBoardType.setLayout(new GridLayout());

        Label lblBoardType = new Label(compBoardType, SWT.NONE);
        lblBoardType.setText("Bitte wählen Sie die Art des Spielbretts:");

        initBtnsForBoardType();
    }

    private void initBtnsForBoardType() {
        EnumSet<BoardTypeEnum> boardTypes =  EnumSet.allOf(BoardTypeEnum.class);
        comboBoard = new Combo(compBoardType, SWT.READ_ONLY);
        availableBoardTypes = boardTypes.toArray(new BoardTypeEnum[boardTypes.size()]);
        String[] boardsString = new String[boardTypes.size()];
        int i = 0;
        for (BoardTypeEnum board : boardTypes) {
            boardsString[i] = board.getName();
            i++;
        }
        comboBoard.setItems(boardsString);
        comboBoard.select(0);
    }

    private void initInitialBoardComposite() {
        compInitialBoard = new Composite(shell, SWT.NULL);
        compInitialBoard.setLayout(new GridLayout(2, false));
        compInitialBoard.setLayoutData(new GridData(GridData.FILL_BOTH));
        
        GridData gdUploadLabel = new GridData();
        gdUploadLabel.horizontalSpan = 2;
        Label lblUpload = new Label(compInitialBoard, SWT.NONE);
        lblUpload.setText("Bitte laden Sie den Anfangszustand hoch:");
        lblUpload.setLayoutData(gdUploadLabel);

        GridData gdBoardFileLabel = new GridData(SWT.FILL, SWT.CENTER, true, false);
        gdUploadLabel.horizontalSpan = 2;
        lblBoardFileName = new Label(compInitialBoard, SWT.NONE);
        lblBoardFileName.setText("Bitte Datei auswählen");
        lblBoardFileName.setLayoutData(gdBoardFileLabel);

        initFileSelectorDialog();
        initBtnSelectFile();
    }

    private void initFileSelectorDialog() {
        dlgFileSelector = new FileDialog(shell, SWT.OPEN);
        dlgFileSelector.setText("Import initial board");
        dlgFileSelector.setFilterExtensions(new String[] {"*.gol"});
        dlgFileSelector.setFilterNames(new String[] {"GameOfLife files (*.gol)"});
    }

    private void initBtnSelectFile() {
        GridData gdSelectFileButton = new GridData(GridData.END, GridData.CENTER, true, false);
        gdSelectFileButton.horizontalSpan = 2;
        btnSelectFile = new Button(compInitialBoard, SWT.NONE);
        btnSelectFile.setText("Datei auswählen");
        btnSelectFile.setLayoutData(gdSelectFileButton);
    }

    private void initBtnCreateGame() {
        btnCreateGame = new Button(shell, SWT.NONE);
        btnCreateGame.setText("Spiel erstellen");
        btnCreateGame.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
    }


    public String getFilePath() {
        return filePath;
    }

    public Shell getShell() {
        return shell;
    }

    public ModeEnum getSelectedMode() throws GOLException {
        int i = comboMode.getSelectionIndex();
        if (i == -1) {
            throw new GOLException("Bitte Spielmodus auswählen.");
        } else {
            return availableModes[i];
        }
    }

    public BoardTypeEnum getSelectedBoardType() throws GOLException {
        int i = comboBoard.getSelectionIndex();
        if (i == -1) {
            throw new GOLException("Bitte Art des Spielbretts auswählen.");
        } else {
            return availableBoardTypes[i];
        }
    }

    public void start() {
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        shell.dispose();
    }

    public void dispose() {
        shell.dispose();
    }

    public void selectFile() {
        filePath = dlgFileSelector.open();
        if (filePath != null) {
            String name = dlgFileSelector.getFileName();
            if (name.length() > 50) {
                name = name.substring(0, 47) + "...";
            }
            lblBoardFileName.setText(name);
        }
    }

    public void addSelectFileListener(SelectionAdapter listenForSelectFileButton) {
        btnSelectFile.addSelectionListener(listenForSelectFileButton);
    }

    public void addCreateGameListener(SelectionAdapter listenForCreateGameButton) {
        btnCreateGame.addSelectionListener(listenForCreateGameButton);
    }
}
