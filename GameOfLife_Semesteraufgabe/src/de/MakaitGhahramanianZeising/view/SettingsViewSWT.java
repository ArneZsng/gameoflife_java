package de.MakaitGhahramanianZeising.view;

/**
 * @author Hendrik Makait
 *
 */

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class SettingsViewSWT {
	private Shell shell;
	private static Display display = new Display();
	private Button btnSelectFile;
	private Button btnCreateGame;
	private Button btnGameOfLife;
	private Button btnLifeWithoutDeath;
	private Button btnHighLife;
	private Button btn34Life;
	private Button btnPacManStyle;
	private Button btnWallOfDeath;
	private Composite compBoardType;
	private Composite compGameMode;
	private Composite compInitialBoard;
	private Label lblChooseBoardType;
	private Label lblChooseGameMode;
	private Label lblInitialBoardFileName;
	private Label lblUpload;
	private FileDialog dlgFileSelector;
	public String filePath;

	
	public SettingsViewSWT() {
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
		initBtnsForGameMode();
	}
	
	private void initBtnsForGameMode() {
		btnGameOfLife = createRadioButton("Game of Life", compGameMode);
		btnLifeWithoutDeath = createRadioButton("Life without Death", compGameMode);
		btnHighLife = createRadioButton("HighLife", compGameMode);
		btn34Life = createRadioButton("34 Life", compGameMode);
	}
	
	private void initBoardTypeComposite() {
	    compBoardType = new Composite(shell, SWT.NULL);
		compBoardType.setLayout(new GridLayout());
		lblChooseBoardType = new Label(compBoardType, SWT.NONE);
		lblChooseBoardType.setText("Bitte wählen Sie die Art des Spielbretts:");
		initBtnsForBoardType();
	}
	
	private void initBtnsForBoardType() {
		btnWallOfDeath = createRadioButton("Wall of Death", compBoardType);
		btnPacManStyle = createRadioButton("PacMan Style", compBoardType);
	}
	
	private Button createRadioButton(String buttonText, Composite composite) {
		Button radioButton = new Button(composite, SWT.RADIO);
		radioButton.setText(buttonText);
		return radioButton;
	}
	
	private void initInitialBoardComposite() {
	    compInitialBoard = new Composite(shell, SWT.NULL);
	    compInitialBoard.setLayout(new GridLayout(2, false));
	    compInitialBoard.setLayoutData(new GridData(GridData.FILL_BOTH));
		lblUpload = new Label(compInitialBoard, SWT.NONE);
		lblUpload.setText("Bitte laden Sie den Anfangszustand hoch:");
		GridData data = new GridData();
		data.horizontalSpan = 2;
		lblUpload.setLayoutData(data);
		lblInitialBoardFileName = new Label(compInitialBoard, SWT.NONE);
		lblInitialBoardFileName.setText("Bitte Datei auswählen");
		dlgFileSelector = new FileDialog(shell, SWT.OPEN);
		dlgFileSelector.setText("Import initial board");
		dlgFileSelector.setFilterExtensions(new String[] { "*.gol", "*.*" });
		dlgFileSelector.setFilterNames(new String[] {"GameOfLife files (*.gol)", "all (*.*)" });
		initBtnSelectFile();
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
	
	public String getFilePath() {
		return filePath;
	}
	
	public Shell getShell() {
		return shell;
	}
	
	public void start() {
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
	
	public void dispose() {
		shell.dispose();
	}
}