package de.MakaitGhahramanianZeising.view;

import java.util.Observable;
import java.util.Observer;

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

import de.MakaitGhahramanianZeising.model.Game;

public class GameViewSWT {
	
	private Shell shell;
	private static Display display = new Display();
	private Button btnNewGame;
	private Label lblRound;
	private Label lblSpeedDescription;
	private Label lbl0;
	private Label lbl1;
	private Slider sldSpeed;
	private Composite compControls;
	private Label lblSpeed;
	private Canvas canvas;
	private Game game;
	private int cellSize;
	private GC gc;

	public GameViewSWT(Game game) {
		shell = new Shell();
		shell.setText("Game of Life");
		setSize(shell);
		this.game = game;
		game.addObserver(new GameObserver());
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
		lblRound = new Label(compControls, SWT.NONE);
		setRound(game.getRound());
		GridData roundGridData = new GridData();
		roundGridData.widthHint = 200;
		lblRound.setLayoutData(roundGridData);
		lblSpeedDescription = new Label(compControls, SWT.NONE);
		lbl0 = new Label(compControls, SWT.NONE);
		lbl0.setText("0");
		sldSpeed = new Slider(compControls, SWT.HORIZONTAL);
		sldSpeed.setValues(game.getSpeed(), 0, 1001, 1, 50, 50);
		lbl1 = new Label(compControls, SWT.NONE);
		lbl1.setText("1");
		lblSpeedDescription.setText("Spielgeschwindigkeit:");
		lblSpeed = new Label(compControls, SWT.NONE);
		GridData speedGridData = new GridData();
		speedGridData.widthHint = 100;
		lblSpeed.setLayoutData(speedGridData);
		lblSpeed.setText(String.valueOf(((double)getSpeed())/1000));
		btnNewGame = new Button(compControls, SWT.NONE);
		btnNewGame.setText("Neues Spiel");
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
		setRound(game.getRound());
		canvas.redraw();
	}
	
	public void setSpeed() {
		String speed = String.valueOf((double) (getSpeed())/1000);
		lblSpeed.setText(speed);
	}
	
	public void setRound(int round) {
		String newRound = "";
		if (round <= 999999999) {
			newRound = String.valueOf(round);
		} else {
			newRound = "Unzählbar!";
		}
		lblRound.setText("Generation: "+ newRound);
	}
	
	public void addNewGameListener(SelectionAdapter listenForNewGameButton) {
		btnNewGame.addSelectionListener(listenForNewGameButton);
	}
	
	public int getSpeed() {
		int speed = sldSpeed.getSelection();
		return speed;
	}
	
	public void addSpeedSliderListener(SelectionAdapter listenForSpeedSlider) {
		sldSpeed.addSelectionListener(listenForSpeedSlider);
	}
	
	public void dispose() {
		display.dispose();
	}
	
	private class GameObserver implements Observer {

	    @Override
	    public void update(Observable o, Object arg) {
	    	if (!game.isInterrupted()) {
	    		Display.getDefault().syncExec(new Runnable() {
	    			public void run() {
	    				updateView();
	    			}
	    		}); 
	    	}
	    }
	}
}
