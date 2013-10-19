package de.MakaitGhahramanianZeising.model;

public class LifeWithoutDeathGameModel extends AbstractGameModel {

	public LifeWithoutDeathGameModel() {
		board = new WallOfDeathBoardModel();
		new GameOfLifeGameModel(board);
	}
	
	public LifeWithoutDeathGameModel(BoardModel board) {
		this.board = board;
	}
	
	public void cellSurvives(int x, int y) {
		board.cellWillBeAlive(x, y);
	}

	public void cellRevives(int x, int y) {
		int livingNeighbors = board.numberOfLivingNeighbors(x, y);

		if (livingNeighbors == 3) {
			board.cellWillBeAlive(x, y);
		} else {
			board.cellWillBeDead(x, y);
		}
	}

}
