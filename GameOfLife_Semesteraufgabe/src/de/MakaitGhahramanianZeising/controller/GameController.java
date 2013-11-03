package de.MakaitGhahramanianZeising.controller;

import de.MakaitGhahramanianZeising.model.*;
import de.MakaitGhahramanianZeising.view.*;
import de.MakaitGhahramanianZeising.controller.SettingsController;
import de.MakaitGhahramanianZeising.enums.BoardTypeEnum;
import de.MakaitGhahramanianZeising.enums.ModeEnum;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class GameController {
	
	private SettingsController mySettingsController;
	private Game myGame;
	private GameViewSWT myGameView;

	public GameController() {
		mySettingsController = new SettingsController();
		myGame = createGame(mySettingsController.getBoard(), mySettingsController.getMode(), mySettingsController.getBoardType());
		Thread t1 = new Thread(myGame);
		t1.start();
		myGameView = new GameViewSWT(myGame);
		myGameView.addStartGameListener(new StartGameListener());
		myGameView.addSpeedSliderListener(new SpeedSliderListener());
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
			myGameView.setNewSpeed();
		}
	}
	
	class StartGameListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			myGame.prepareNextRound();
			if (!myGame.isGameOver()) {
				myGame.playNextRound();
			}
		}
	}

}
