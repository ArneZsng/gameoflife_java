package de.makaitghahramanianzeising.controller;

import de.makaitghahramanianzeising.enums.BoardTypeEnum;

import de.makaitghahramanianzeising.enums.ModeEnum;
import de.makaitghahramanianzeising.model.*;
import de.makaitghahramanianzeising.view.GameViewSWT;
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
 * is resetted.
 */

public class GameController {

    private SettingsController mySettingsController;
    private Thread myGameThread;
    private Game myGame;
    private GameViewSWT myGameView;
    private GameControls myGameControls;
    private Display display = new Display();

    public GameController() {
        initialize();
    }

    private void initialize() {
        mySettingsController = new SettingsController(display);
        if (mySettingsController.isValid()) {
            initGame();
        }
    }

    private void initGame() {
        myGame = createGame(mySettingsController.getBoard(), mySettingsController.getMode(), mySettingsController.getBoardType());
        myGameThread = new Thread(myGame);
        myGameThread.start();
        initGameView();
    }

    private void initGameView() {
        myGameView = new GameViewSWT(display, myGame);
        myGameControls = myGameView.getControls();
        initGameViewListeners();
        myGameView.start();
    }

    private void initGameViewListeners() {
        myGameControls.addNewGameListener(new NewGameListener());
        myGameControls.addSpeedSliderListener(new SpeedSliderListener());
        myGameView.addCloseButtonListener(new CloseButtonListener());
    }

    private void reset() {
        mySettingsController.reset();
        myGame = null;
        myGameThread = null;
        myGameView = null;
    }

    private Game createGame(Cell[][] board, ModeEnum modeEnum, BoardTypeEnum boardTypeEnum) {
        if (boardTypeEnum == BoardTypeEnum.WALLOFDEATH) {
            return new WallOfDeathGame(board, modeEnum.getSurvives(), modeEnum.getRevives());
        } else {
            return new PacmanGame(board, modeEnum.getSurvives(), modeEnum.getRevives());
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
            myGameView.dispose();
            initialize();
        }
    }

    class CloseButtonListener implements Listener {
        public void handleEvent(Event e) {
            myGameThread.interrupt();
            myGameView.dispose();
            reset();
        }
    }

}
