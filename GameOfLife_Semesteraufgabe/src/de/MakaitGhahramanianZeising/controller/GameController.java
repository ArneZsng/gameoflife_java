package de.MakaitGhahramanianZeising.controller;

import de.MakaitGhahramanianZeising.model.*;
import de.MakaitGhahramanianZeising.view.*;
import de.MakaitGhahramanianZeising.controller.SettingsController;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class GameController {
	
	private SettingsController mySettingsController;
	private GameModel myGame;
	private ModeModel myMode;
	private CellModel[][] cells = new CellModel[3][3];
	private GameViewSWT myGameView;

	public GameController() {
		mySettingsController = new SettingsController();
		cells[0][0] = new CellModel(true);
		cells[0][1] = new CellModel(true);
		cells[0][2] = new CellModel(true);
		cells[1][0] = new CellModel(true);
		cells[1][1] = new CellModel(true);
		cells[1][2] = new CellModel(true);
		cells[2][0] = new CellModel(true);
		cells[2][1] = new CellModel(true);
		cells[2][2] = new CellModel(true);	
		myMode = new GameOfLifeModeModel();
		myGame = new WallOfDeathGameModel(myMode, cells);
		myGameView = new GameViewSWT(myGame.getWidth());
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
			cells[0][0] = new CellModel(true);
			cells[0][1] = new CellModel(true);
			cells[0][2] = new CellModel(true);
			cells[1][0] = new CellModel(true);
			cells[1][1] = new CellModel(true);
			cells[1][2] = new CellModel(true);
			cells[2][0] = new CellModel(true);
			cells[2][1] = new CellModel(true);
			cells[2][2] = new CellModel(true);	
			myMode = new GameOfLifeModeModel();
			myGame = new WallOfDeathGameModel(myMode, cells);
			myGameView.updateView(convertCellArray());
			
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
