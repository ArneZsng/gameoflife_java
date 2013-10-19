package de.MakaitGhahramanianZeising.model;

public class ThreeFourGameModel extends AbstractGameModel {
	
	public ThreeFourGameModel() {
		board = new WallOfDeathBoardModel();
		new GameOfLifeGameModel(board);
	}
	
	public ThreeFourGameModel(BoardModel board) {
		this.board = board;
	}
	
	public void cellSurvives(int x, int y) {
		int livingNeighbors = board.numberOfLivingNeighbors(x, y);

		if (livingNeighbors == 3 || livingNeighbors == 4) {
			board.cellWillBeAlive(x, y);
		} else {
			board.cellWillBeDead(x, y);
		}
	}

	public void cellRevives(int x, int y) {
		int livingNeighbors = board.numberOfLivingNeighbors(x, y);

		if (livingNeighbors == 3 || livingNeighbors == 4) {
			board.cellWillBeAlive(x, y);
		} else {
			board.cellWillBeDead(x, y);
		}
	}

}
