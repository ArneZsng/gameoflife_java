package de.MakaitGhahramanianZeising.controller;

import de.MakaitGhahramanianZeising.model.*;
import de.MakaitGhahramanianZeising.view.*;
import de.MakaitGhahramanianZeising.controller.SettingsController;
import de.MakaitGhahramanianZeising.enums.BoardTypeEnum;
import de.MakaitGhahramanianZeising.enums.ModeEnum;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

public class GameController {
	
	private SettingsController mySettingsController;
	private Thread myGameThread;
	private Game myGame;
	private GameViewSWT myGameView;
	private Display display = new Display();

	public GameController() {
		reset();
		initialize();
	}
	
	private void reset() {
		mySettingsController = null;
		myGame = null;
		myGameThread = null;
		myGameView = null;
	}
	
	private void initialize() {
		mySettingsController = new SettingsController(display);
		myGame = createGame(mySettingsController.getBoard(), mySettingsController.getMode(), mySettingsController.getBoardType());
		myGameThread = new Thread(myGame);
		myGameThread.start();
		myGameView = new GameViewSWT(display, myGame);
		myGameView.addNewGameListener(new NewGameListener());
		myGameView.addSpeedSliderListener(new SpeedSliderListener());
		
		// TODO FIX CLOSE-BUTTON-ERROR
		myGameView.addCloseButtonListener(new CloseButtonListener());
		myGameView.start();
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
			myGame.setSpeed(myGameView.getSpeed());
			myGameView.setSpeed();
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
			initialize();
		}
	}

}
