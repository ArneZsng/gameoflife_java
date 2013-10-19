package de.MakaitGhahramanianZeising.controller;

import de.MakaitGhahramanianZeising.model.*;
import de.MakaitGhahramanianZeising.view.*;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class GameController {
	
	private GameModel myGame;
	private BoardModel myBoard;
	private GameViewSWT myGameView;

	public GameController() {
		myBoard = new WallOfDeathBoardModel();
		myGame = new GameOfLifeGameModel(myBoard);
		myGameView = new GameViewSWT(myBoard.getWidth());
		myGameView.addNextRoundListener(new NextRoundListener());
		myGameView.addResetGameListener(new ResetGameListener());
		myGameView.updateView(convertCellArray());
		myGameView.start();

	}

	class NextRoundListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			playRound();
		}
	}

	class ResetGameListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			myBoard = new WallOfDeathBoardModel();
			myGame = new GameOfLifeGameModel(myBoard);
			myGameView.updateView(convertCellArray());
			
		}
	}

	private void playRound() {
		if (!isGameOver()) {
			System.out.println("Round played!");
			nextRound();
			myGameView.updateView(convertCellArray());
		}
	};

	private boolean[][] convertCellArray() {
		boolean[][] convertedArray = new boolean[myBoard.getWidth()][myBoard.getHeight()];
		for (int i = 0; i < myBoard.getWidth(); i++) {
			for (int j = 0; j < myBoard.getHeight(); j++) {
				convertedArray[i][j] = myGame.getStatus(i,j);
			}
		}

		return convertedArray;
	}

	public void nextRound() {
		myGame.prepareNextRound();
		myGame.playNextRound();
	}

	private boolean isGameOver() {
		return 0 == myBoard.numberOfLivingCells();
	}


}
