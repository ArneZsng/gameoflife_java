package de.MakaitGhahramanianZeising.model;

public class GameOfLifeGameModel extends AbstractGameModel {
	
	public GameOfLifeGameModel() {
		board = new WallOfDeathBoardModel();
		new GameOfLifeGameModel(board);
	}
	
	public GameOfLifeGameModel(BoardModel board) {
		this.board = board;
	}
	
	public void cellSurvives(int x, int y) {
		int livingNeighbors = board.numberOfLivingNeighbors(x, y);

		if (livingNeighbors == 2 || livingNeighbors == 3) {
			board.cellWillBeAlive(x, y);
		} else {
			board.cellWillBeDead(x, y);
		}
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
