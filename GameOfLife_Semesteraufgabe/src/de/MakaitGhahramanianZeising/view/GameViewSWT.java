package de.MakaitGhahramanianZeising.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Text;

import de.MakaitGhahramanianZeising.model.Game;

public class GameViewSWT {
	
	private Shell shell;
	private static Display display = new Display();
	private Button btnStart;
	private Label lblGeneration;
	private Label lblSpeed;
	private Label lbl0;
	private Label lbl1;
	private Slider sldSpeed;
	private Composite compControls;
	private Text txtSpeed;
	private Canvas canvas;
	private Game game;
	private int cellSize;
	private GC gc;

	public GameViewSWT(Game game) {
		shell = new Shell();
		shell.setText("Game of Life");
		setSize(shell);
		this.game = game;
		init(shell);
	}
	
	private void setSize(Shell shell) {
        Rectangle bds = shell.getDisplay().getBounds();
        int width = bds.width;
        int height = bds.height;
        shell.setBounds(0,0,width,height);
	}
	
	private void init(Shell s) {
		shell.setLayout(new GridLayout());
		initControls();
		initCanvas();
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
		btnStart = new Button(compControls, SWT.NONE);
		btnStart.setText("Start");
	}
	
	private void initCanvas() {
		canvas = new Canvas(shell, SWT.NONE);
		gc = new GC(canvas);
		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		canvas.setLayoutData(gridData);
		canvas.setBackground(canvas.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		
		drawBoard(gc, canvas);
	}
	
	private void drawBoard(GC gc, Canvas canvas) {
		int height, width;
		if (game.getHeight() > game.getWidth()) {
			height = 500;
			width = 500*(game.getWidth()/game.getHeight());
			cellSize = 500/game.getHeight();
		} else {
			width = 500;
			height = 500*(game.getHeight()/game.getWidth());
			cellSize = 500/game.getHeight();
		}
		canvas.drawBackground(gc, 0, 0, width, height);
		paintCells();
	}
	
	private void paintCells() {
	    canvas.addPaintListener(new PaintListener() {
	    	public void paintControl(PaintEvent e) {
	    		e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_BLACK));
	    		for (int i = 0; i < game.getWidth(); i++) {
					for (int j = 0; j < game.getHeight(); j++) {
						if (game.cellAlive(i,j)) {
							e.gc.fillRectangle(i*cellSize, j*cellSize, cellSize, cellSize);
						}
					}
				}
	    	}
	    });
	}
	
	public void start() {
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	public void updateView() {
		canvas.redraw();
		canvas.setBackground(canvas.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		canvas.drawBackground(gc, 0, 0, 500, 500);
		paintCells();
	}
	
	public void addStartGameListener(SelectionAdapter listenForStartGameButton) {
		btnStart.addSelectionListener(listenForStartGameButton);
	}
	
}
