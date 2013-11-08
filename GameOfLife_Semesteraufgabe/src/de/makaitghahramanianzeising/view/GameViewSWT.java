package de.makaitghahramanianzeising.view;

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
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Slider;

import de.makaitghahramanianzeising.model.Game;

public class GameViewSWT {

    private Shell shell;
    private Display display;
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

    public GameViewSWT(Display display, Game game) {
        this.display = display;
        shell = new Shell();
        shell.setText("Game of Life");
        this.game = game;
        game.addObserver(new GameObserver());
        initializeShell(shell);
        shell.pack();
    }

    private Rectangle getShellFrameSize() {
        Rectangle shellArea = shell.getBounds();
        Rectangle shellClientArea = shell.getClientArea();
        int shellFrameHeight = shellArea.height - shellClientArea.height;
        int shellFrameWidth = shellArea.height - shellClientArea.height;
        return new Rectangle(0, 0, shellFrameWidth, shellFrameHeight);
    }
    private Rectangle getMaxCanvas() {
        int defaultMargin = 5;
        int controlsHeight = 80;
        Rectangle clientArea = shell.getDisplay().getClientArea();
        Rectangle shellFrameSize = getShellFrameSize();
        int maxCanvasHeight = clientArea.height - shellFrameSize.height - controlsHeight - 3 * defaultMargin;
        int maxCanvasWidth = clientArea.width - shellFrameSize.width - 2 * defaultMargin;
        return new Rectangle(0,0, maxCanvasWidth, maxCanvasHeight);

    }

    private int getCellPixelSize(int boardWidth, int boardHeight) {
        Rectangle maxCanvas = getMaxCanvas();
        int pixelWidth = maxCanvas.width / boardWidth;
        int pixelHeight = maxCanvas.height / boardHeight;
        if (pixelWidth <= pixelHeight) {
            return pixelWidth;
        } else {
            return pixelHeight;
        }
    }

    private void initializeShell(Shell s) {
        shell.setLayout(new GridLayout());
        initControlsComposite();
        initCanvas();
    }

    private void initControlsComposite() {
        compControls = new Composite(shell, SWT.NULL);
        compControls.setLayout(new GridLayout(10,false));

        GridData gdControlsComposite = new GridData();
        gdControlsComposite.heightHint = 50;
        compControls.setLayoutData(gdControlsComposite);

        initRoundLabel();
        initSpeedAdjustment();
        initNewGameButton();
    }

    private void initRoundLabel() {
        lblRound = new Label(compControls, SWT.NONE);
        setRoundLabel(game.getRoundAsString());
        GridData gdRoundLabel = new GridData();
        gdRoundLabel.widthHint = 200;
        lblRound.setLayoutData(gdRoundLabel);
    }

    private void initSpeedAdjustment() {
        lblSpeedDescription = new Label(compControls, SWT.NONE);

        lbl0 = new Label(compControls, SWT.NONE);
        lbl0.setText("0");

        sldSpeed = new Slider(compControls, SWT.HORIZONTAL);
        sldSpeed.setValues(game.getSpeed(), 0, 1001, 1, 50, 50);

        lbl1 = new Label(compControls, SWT.NONE);
        lbl1.setText("1");

        lblSpeedDescription.setText("Spielgeschwindigkeit:");
        lblSpeed = new Label(compControls, SWT.NONE);
        lblSpeed.setText(String.valueOf(((double)getSpeed())/1000));

        GridData gdSpeedLabel = new GridData();
        gdSpeedLabel.widthHint = 100;
        lblSpeed.setLayoutData(gdSpeedLabel);
    }

    private void initNewGameButton() {
        btnNewGame = new Button(compControls, SWT.NONE);
        btnNewGame.setText("Neues Spiel");
    }

    private void initCanvas() {
        canvas = new Canvas(shell, SWT.NONE);
        gc = new GC(canvas);

        int boardWidth = game.getWidth();
        int boardHeight = game.getHeight();
        cellSize = getCellPixelSize(boardWidth, boardHeight);

        canvas.setLayoutData(initCanvasGridData(boardWidth, boardHeight));
        canvas.setBackground(canvas.getDisplay().getSystemColor(SWT.COLOR_WHITE));
        canvas.drawBackground(gc, 0, 0, boardWidth * cellSize, boardHeight * cellSize);
        paintCells();
    }

    private GridData initCanvasGridData(int boardWidth, int boardHeight) {
        GridData gridData = new GridData();
        gridData.widthHint = boardWidth * cellSize;
        gridData.heightHint = boardHeight * cellSize;
        gridData.horizontalAlignment = GridData.CENTER;
        gridData.verticalAlignment = GridData.CENTER;
        gridData.grabExcessHorizontalSpace = false;
        gridData.grabExcessVerticalSpace = false;
        return gridData;
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
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        shell.dispose();
    }

    public void updateView() {
        setRoundLabel(game.getRoundAsString());
        canvas.redraw();
    }

    public void setSpeed() {
        String speed = String.valueOf((double) (getSpeed())/1000);
        lblSpeed.setText(speed);
    }

    public void setRoundLabel(String round) {
        lblRound.setText("Generation: "+ round);
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


    public void addCloseButtonListener(Listener listenForCloseButton) {		
        shell.addListener(SWT.Close, listenForCloseButton);
    }

    public void dispose() {
        shell.dispose();
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
