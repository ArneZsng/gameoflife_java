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
	private Button btnStart;
	private Label lblRound;
	private Label lblSpeed;
	private Label lbl0;
	private Label lbl1;
	private Slider sldSpeed;
	private Composite compControls;
	private Label lblCurrentSpeed;
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
		lblSpeed = new Label(compControls, SWT.NONE);
		lbl0 = new Label(compControls, SWT.NONE);
		lbl0.setText("0");
		sldSpeed = new Slider(compControls, SWT.HORIZONTAL);
		sldSpeed.setValues(500, 0, 1000, 1, 50, 50);
		lbl1 = new Label(compControls, SWT.NONE);
		lbl1.setText("1");
		lblSpeed.setText("Spielgeschwindigkeit:");
		lblCurrentSpeed = new Label(compControls, SWT.NONE);
		lblCurrentSpeed.setText(String.valueOf(getSpeed()));
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
		setRound(game.getRound());
		canvas.redraw();
		canvas.setBackground(canvas.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		canvas.drawBackground(gc, 0, 0, 500, 500);
		paintCells();
	}
	
	public void setNewSpeed() {
		String speed = String.valueOf(getSpeed());
		lblCurrentSpeed.setText(speed);
	}
	
	public void setRound(int round) {
		lblRound.setText("Generation: "+ String.valueOf(round));
	}
	
	public void addStartGameListener(SelectionAdapter listenForStartGameButton) {
		btnStart.addSelectionListener(listenForStartGameButton);
	}
	
	public int getSpeed() {
		int speed = sldSpeed.getSelection();
		return speed;
	}
	
	public void addSpeedSliderListener(SelectionAdapter listenForSpeedSlider) {
		sldSpeed.addSelectionListener(listenForSpeedSlider);
	}
	
	private class GameObserver implements Observer {

	    @Override
	    public void update(Observable o, Object arg) {
	    	Display.getDefault().syncExec(new Runnable() {
	    		public void run() {
	    			updateView();
	    		}
	    	});
	    }
	}
}
