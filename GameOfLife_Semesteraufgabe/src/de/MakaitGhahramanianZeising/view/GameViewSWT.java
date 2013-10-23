package de.MakaitGhahramanianZeising.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class GameViewSWT implements GameView {
	
	private Shell shell;
	private static Display display = new Display();
	private Button btnStartStop;
	private Label lblGeneration;
	private Label lblSpeed;
	private Label lbl0;
	private Label lbl1;
	private Slider sldSpeed;
	private Composite compControls;
	private Text txtSpeed;

	private Table table;
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
		initControls();
		initTable();
	}
	
	private void initControls() {
		compControls = new Composite(shell, SWT.NULL);
		compControls.setLayout(new GridLayout(10,false));
		lblGeneration = new Label(compControls, SWT.NONE);
		lblGeneration.setText("Generation: 1");
		lblSpeed = new Label(compControls, SWT.NONE);
		lbl0 = new Label(compControls, SWT.NONE);
		lbl0.setText("0");
		sldSpeed = new Slider(compControls, SWT.HORIZONTAL);
		sldSpeed.setValues(500, 0, 1000, 1, 50, 50);
		lbl1 = new Label(compControls, SWT.NONE);
		lbl1.setText("1");
		lblSpeed.setText("Spielgeschwindigkeit:" + (sldSpeed.getSelection()/1000));
		txtSpeed = new Text(compControls, SWT.NONE);
		txtSpeed.setText("0.5");
		btnStartStop = new Button(compControls, SWT.NONE);
		btnStartStop.setText("Start");
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
