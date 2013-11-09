package de.makaitghahramanianzeising.view;

import de.makaitghahramanianzeising.model.AbstractGame;
import de.makaitghahramanianzeising.view.components.BoardCanvas;
import de.makaitghahramanianzeising.view.components.GameControls;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

/**
 * Renders the game view to display the current
 * status of the game and allowing user interaction.
 */

public final class GameSWT {

    private final Shell shell;
    private final Display display;
    private final AbstractGame game;
    private GameControls controls;
    private BoardCanvas canvas;

    public GameSWT(Display display, AbstractGame game) {
        this.display = display;
        this.game = game;
        shell = new Shell();
        shell.setText("Game of Life");
        init();
        game.addObserver(new GameObserver());
        shell.pack();
    }

    private void init() {
        shell.setLayout(new GridLayout());
        controls = new GameControls(shell, game);
        controls.initControls();
        canvas = new BoardCanvas(shell, game);
    }

    public GameControls getControls() {
        return controls;
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

    public void updateView() {
        controls.setRoundLabel(game.getRoundAsString());
        canvas.redraw();
    }

    public void addCloseButtonListener(Listener listenForCloseButton) {
        shell.addListener(SWT.Close, listenForCloseButton);
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
