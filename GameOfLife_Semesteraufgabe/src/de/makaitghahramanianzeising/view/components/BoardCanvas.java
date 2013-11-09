package de.makaitghahramanianzeising.view.components;

import de.makaitghahramanianzeising.model.Game;
import de.makaitghahramanianzeising.utils.CellSizeCalculator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Shell;

/**
 * Renders the board canvas for displaying living
 * and death cells in the game view. 
 */

public class BoardCanvas extends Canvas {

    private final int cellSize;
    private final Game game;

    public BoardCanvas(Shell shell, Game game) {
        super(shell, SWT.NONE);
        this.game = game;
        int boardWidth = game.getBoardWidth();
        int boardHeight = game.getBoardHeight();
        cellSize = new CellSizeCalculator(shell).getPixelSize(boardWidth, boardHeight);
        GC gc = new GC(this);

        setLayoutData(initCanvasGridData(boardWidth, boardHeight));
        setBackground(getDisplay().getSystemColor(SWT.COLOR_WHITE));
        drawBackground(gc, 0, 0, boardWidth * cellSize, boardHeight * cellSize);
        paintCells();
    }

    private GridData initCanvasGridData(int boardWidth, int boardHeight) {
        GridData gridData = new GridData();
        gridData.widthHint = boardWidth * cellSize;
        gridData.heightHint = boardHeight * cellSize;
        gridData.horizontalAlignment = GridData.CENTER;
        gridData.verticalAlignment = GridData.CENTER;
        gridData.grabExcessHorizontalSpace = false;
        gridData.grabExcessVerticalSpace = false;
        return gridData;
    }

    private void paintCells() {
        addPaintListener(new PaintBoardListener());
    }

    class PaintBoardListener implements PaintListener {
        public void paintControl(PaintEvent event) {
            event.gc.setBackground(getDisplay().getSystemColor(SWT.COLOR_BLACK));
            paintBoard(event);
        }

        private void paintBoard(PaintEvent event) {
            for (int xCoordinate = 0; xCoordinate < game.getBoardWidth(); xCoordinate++) {
                for (int yCoordinate = 0; yCoordinate < game.getBoardHeight(); yCoordinate++) {
                    paintCell(event, xCoordinate, yCoordinate);
                }
            }
        }

        private void paintCell(PaintEvent event, int xCoordinate, int yCoordinate) {
            if (game.cellAlive(xCoordinate, yCoordinate)) {
                event.gc.fillRectangle(xCoordinate * cellSize, yCoordinate * cellSize, cellSize, cellSize);
            }
        }
    }
}
