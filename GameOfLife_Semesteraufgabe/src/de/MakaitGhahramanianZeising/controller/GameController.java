package de.MakaitGhahramanianZeising.controller;

import de.MakaitGhahramanianZeising.model.*;
import de.MakaitGhahramanianZeising.view.*;
import de.MakaitGhahramanianZeising.controller.SettingsController;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class GameController {
	
	private SettingsController mySettingsController;
	private Game myGame;
	private Mode myMode;
	private Cell[][] myBoard;
	private GameViewSWT myGameView;

	public GameController() {
		mySettingsController = new SettingsController();
		myBoard = mySettingsController.getBoard();
		myMode = new GameOfLifeMode();
		myGame = new WallOfDeathGame(myMode, myBoard);
		myGameView = new GameViewSWT(myGame.getWidth());
		myGameView.updateView(convertCellArray());
		myGameView.start();

	}

	class StartStopListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			
		}
	}
	
	private void playRound() {
		if (!myGame.isGameOver()) {
			System.out.println("Round played!");
			nextRound();
			myGameView.updateView(convertCellArray());
		}
	};

	private boolean[][] convertCellArray() {
		boolean[][] convertedArray = new boolean[myGame.getWidth()][myGame.getHeight()];
		for (int i = 0; i < myGame.getWidth(); i++) {
			for (int j = 0; j < myGame.getHeight(); j++) {
				convertedArray[i][j] = myGame.cellAlive(i,j);
			}
		}

		return convertedArray;
	}

	public void nextRound() {
		myGame.prepareNextRound();
		myGame.playNextRound();
	}

}
