package de.makaitghahramanianzeising.view;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import de.makaitghahramanianzeising.model.Game;
import de.makaitghahramanianzeising.view.components.BoardCanvas;
import de.makaitghahramanianzeising.view.components.GameControls;

//TODO: BUG CLOSING GAMEVIEW
public final class GameViewSWT {

    private final Shell shell;
    private final Display display;
    private GameControls controls;
    private BoardCanvas canvas;
    private final Game game;


    public GameViewSWT(Display display, Game game) {
        this.display = display;
        shell = new Shell();
        shell.setText("Game of Life");
        this.game = game;
        game.addObserver(new GameObserver());
        init();
        shell.pack();
    }

    private void init() {
        shell.setLayout(new GridLayout());
        controls = new GameControls(shell, game);
        canvas = new BoardCanvas(shell, game);
    }

    public GameControls getControls() {
        return controls;
    }

    public void addCloseButtonListener(Listener listenForCloseButton) {		
        shell.addListener(SWT.Close, listenForCloseButton);
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
        controls.setRoundLabel(game.getRoundAsString());
        canvas.redraw();
    }

    public void dispose() {
        shell.dispose();
    }

    public Shell getShell() {
        return shell;
    }

    private class GameObserver implements Observer {

        @Override
        public void update(Observable obs, Object arg) {
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
