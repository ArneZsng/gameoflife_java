package de.MakaitGhahramanianZeising.model;

public abstract class AbstractGameModel implements GameModel {

	protected BoardModel board;
	
	protected abstract void cellSurvives(int x, int y);
	protected abstract void cellRevives(int x, int y);

	@Override
	public void prepareNextRound() {
		determineStatusForNextRound();
	}

	@Override
	public void playNextRound() {
		board.updateStatus();
	}

	@Override
	public boolean getStatus(int x, int y) {
		return board.isAlive(x, y);
	}
	
	private void determineStatusForNextRound() {
		for (int i = 0; i < board.getWidth(); i++) {
			for (int j = 0; j < board.getHeight(); j++) {
				determineStatusForNextRound(i, j);
			}
		}
	}

	private void determineStatusForNextRound(int x, int y) {
		if (getStatus(x, y)) {
			cellSurvives(x, y);
		} else {
			cellRevives(x, y);
		}
	};
	
	
}
