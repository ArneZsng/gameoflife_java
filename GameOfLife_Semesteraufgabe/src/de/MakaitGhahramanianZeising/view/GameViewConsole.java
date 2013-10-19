package de.MakaitGhahramanianZeising.view;

public class GameViewConsole implements GameView {

	@Override
	public void updateView(boolean[][] cells) {
		System.out
				.println("---------------------------------------------------");
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				if (cells[i][j]) {
					System.out.print("o ");
				} else {
					System.out.print("  ");
				}
			}
			System.out.print("\n");
		}
		System.out
				.println("---------------------------------------------------\n");
	}
	
}
