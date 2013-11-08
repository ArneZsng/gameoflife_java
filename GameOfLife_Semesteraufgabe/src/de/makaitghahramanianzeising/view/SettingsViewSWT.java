package de.makaitghahramanianzeising.view;

/**
 * @author Hendrik Makait
 *
 */

import java.util.EnumSet;

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

import de.makaitghahramanianzeising.enums.BoardTypeEnum;
import de.makaitghahramanianzeising.enums.ModeEnum;
import de.makaitghahramanianzeising.exceptions.GOLException;

public class SettingsViewSWT {
    private Shell shell;
    private Display display;
    private Button btnSelectFile;
    private Button btnCreateGame;
    private Combo comboMode;
    private Combo comboBoard;
    private Composite compBoardType;
    private Composite compGameMode;
    private Composite compInitialBoard;
    private Label lblChooseBoardType;
    private Label lblChooseGameMode;
    private Label lblInitialBoardFileName;
    private Label lblUpload;
    private FileDialog dlgFileSelector;
    private ModeEnum[] availableModes;
    private BoardTypeEnum[] availableBoardTypes;
    private String filePath;


    public SettingsViewSWT(Display display) {
        this.display = display;
        shell = new Shell();
        shell.setText("Game of Life");
        shell.setSize(400, 400);
        init(shell);
    }

    private void init(Shell s) {
        shell.setLayout(new GridLayout());
        initGameModeComposite();
        initBoardTypeComposite();
        initInitialBoardComposite();
        initBtnCreateGame();
    }

    private void initGameModeComposite() {
        compGameMode = new Composite(shell, SWT.NULL);
        compGameMode.setLayout(new GridLayout());

        lblChooseGameMode = new Label(compGameMode, SWT.NONE);
        lblChooseGameMode.setText("Bitte wählen Sie den Spielmodus:");

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

        lblChooseBoardType = new Label(compBoardType, SWT.NONE);
        lblChooseBoardType.setText("Bitte wählen Sie die Art des Spielbretts:");

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

        lblUpload = new Label(compInitialBoard, SWT.NONE);
        lblUpload.setText("Bitte laden Sie den Anfangszustand hoch:");

        GridData gdUploadLabel = new GridData();
        gdUploadLabel.horizontalSpan = 2;
        lblUpload.setLayoutData(gdUploadLabel);

        lblInitialBoardFileName = new Label(compInitialBoard, SWT.NONE);
        lblInitialBoardFileName.setText("Bitte Datei auswählen");

        initFileSelectorDialog();
        initBtnSelectFile();
    }

    private void initFileSelectorDialog() {
        dlgFileSelector = new FileDialog(shell, SWT.OPEN);
        dlgFileSelector.setText("Import initial board");
        dlgFileSelector.setFilterExtensions(new String[] { "*.gol", "*.*" });
        dlgFileSelector.setFilterNames(new String[] {"GameOfLife files (*.gol)", "all (*.*)" });
    }

    public void addSelectFileListener(SelectionAdapter listenForSelectFileButton) {
        btnSelectFile.addSelectionListener(listenForSelectFileButton);
    }	

    public void addCreateGameListener(SelectionAdapter listenForCreateGameButton) {
        btnCreateGame.addSelectionListener(listenForCreateGameButton);
    }

    public void selectFile() {
        filePath = dlgFileSelector.open();		
        if (filePath != null) {
            String name = dlgFileSelector.getFileName();
            lblInitialBoardFileName.setText(name);
        }
    }

    private void initBtnSelectFile() {
        btnSelectFile = new Button(compInitialBoard, SWT.NONE);
        btnSelectFile.setText("Datei auswählen");
        btnSelectFile.setLayoutData(new GridData(GridData.END, GridData.CENTER, true, false));
    }

    private void initBtnCreateGame() {
        btnCreateGame = new Button(shell, SWT.NONE);
        btnCreateGame.setText("Spiel erstellen");
        btnCreateGame.setLayoutData(new GridData(GridData.FILL, GridData.CENTER,
                true, false));
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

    public String getFilePath() {
        return filePath;
    }


    public Shell getShell() {
        return shell;
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
}