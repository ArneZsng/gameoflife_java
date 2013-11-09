package de.makaitghahramanianzeising.controller;

import de.makaitghahramanianzeising.enums.BoardTypeEnum;

import de.makaitghahramanianzeising.enums.ModeEnum;
import de.makaitghahramanianzeising.model.Cell;
import de.makaitghahramanianzeising.model.AbstractGame;
import de.makaitghahramanianzeising.model.PacmanGame;
import de.makaitghahramanianzeising.model.WallOfDeathGame;
import de.makaitghahramanianzeising.view.GameSWT;
import de.makaitghahramanianzeising.view.components.GameControls;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/**
 * Creates a game according to the settings specified by the user.
 * Opens the settings controller first and asks for game settings.
 * Creates a new game accordingly. Returns to the settings when game
 * is reset.
 */

public class Game {

    private Settings mySettingsController;
    private Thread myGameThread;
    private AbstractGame myGame;
    private GameSWT myGameSWT;
    private GameControls myGameControls;
    private Display display = new Display();

    public Game() {
        init();
    }

    private void init() {
        mySettingsController = new Settings(display);
        if (mySettingsController.isValid()) {
            initGame();
        }
    }

    private void initGame() {
        Cell[][] board = mySettingsController.getBoard();
        ModeEnum mode = mySettingsController.getMode();
        BoardTypeEnum boardType = mySettingsController.getBoardType();
        myGame = createGame(board, mode, boardType);
        myGameThread = new Thread(myGame);
        myGameThread.start();
        initGameView();
    }

    private void initGameView() {
        myGameSWT = new GameSWT(display, myGame);
        myGameControls = myGameSWT.getControls();
        initGameViewListeners();
        myGameSWT.start();
    }

    private void initGameViewListeners() {
        myGameControls.addNewGameListener(new NewGameListener());
        myGameControls.addSpeedSliderListener(new SpeedSliderListener());
        myGameSWT.addCloseButtonListener(new CloseButtonListener());
    }

    private void reset() {
        mySettingsController.reset();
        myGame = null;
        myGameThread = null;
        myGameSWT = null;
    }

    private AbstractGame createGame(Cell[][] board, ModeEnum mode, BoardTypeEnum boardType) {
        Integer[] survives = mode.getSurvives();
        Integer[] revives = mode.getRevives();
        if (boardType == BoardTypeEnum.WALLOFDEATH) {
            return new WallOfDeathGame(board, survives, revives);
        } else {
            return new PacmanGame(board, survives, revives);
        }
    }

    class SpeedSliderListener extends SelectionAdapter {
        public void widgetSelected(SelectionEvent e) {
            myGame.setSpeed(myGameControls.getSpeed());
            myGameControls.setSpeed();
        }
    }

    class NewGameListener extends SelectionAdapter {
        public void widgetSelected(SelectionEvent e) {
            myGameThread.interrupt();
            myGameSWT.dispose();
            init();
        }
    }

    class CloseButtonListener implements Listener {
        public void handleEvent(Event e) {
            myGameThread.interrupt();
            myGameSWT.dispose();
            reset();
        }
    }

}
