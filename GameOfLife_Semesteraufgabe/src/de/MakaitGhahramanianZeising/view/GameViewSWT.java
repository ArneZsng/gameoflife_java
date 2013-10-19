package de.MakaitGhahramanianZeising.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class GameViewSWT implements GameView {
	
	private Shell shell;
	private static Display display = new Display();
	private Table table;
	private Button btnNextRound;
	private Button btnResetGame;
	private int boardSize;

	public GameViewSWT() {
		new GameViewSWT(10);
	}

	public GameViewSWT(int boardSize) {
		this.boardSize = boardSize;
		shell = new Shell();
		shell.setText("Game of Life");
		shell.setSize(600, 600);
		init(shell);
	}

	private void init(Shell s) {
		shell.setLayout(new GridLayout());
		initTable();
		initBtnNextRound();
		initBtnResetGame();
	}

	private void initBtnNextRound() {
		btnNextRound = new Button(shell, SWT.NONE);
		btnNextRound.setText("Next Round");
		btnNextRound.setLayoutData(new GridData(GridData.FILL, GridData.CENTER,
				true, false));
	}

	private void initBtnResetGame() {
		btnResetGame = new Button(shell, SWT.NONE);
		btnResetGame.setText("Reset Game");
		btnResetGame.setLayoutData(new GridData(GridData.FILL, GridData.CENTER,
				true, false));
	}
	
	private void initTable() {
		table = new Table(shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(false);
		table.setHeaderVisible(false);
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.heightHint = 600;
		table.setLayoutData(data);
		for (int i = 0; i < boardSize; i++) {
			TableColumn column = new TableColumn(table, SWT.CENTER);
			column.setWidth(580 / boardSize);
		}
	}

	public void addNextRoundListener(SelectionAdapter listenForNextButton) {
		btnNextRound.addSelectionListener(listenForNextButton);
	}

	public void addResetGameListener(SelectionAdapter listenForResetButton) {
		btnResetGame.addSelectionListener(listenForResetButton);
	}
	
	public void start() {
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	@Override
	public void updateView(boolean[][] cells) {
		table.removeAll();
		for (int i = 0; i < cells.length; i++) {
			TableItem item = new TableItem(table, SWT.NONE);
			String[] values = new String[cells.length];
			for (int j = 0; j < cells[i].length; j++) {
				if (cells[i][j]) {
					values[j] = "x";
				} else {
					values[j] = " ";
				}
			}
			item.setText(values);
		}
	}
	
}
