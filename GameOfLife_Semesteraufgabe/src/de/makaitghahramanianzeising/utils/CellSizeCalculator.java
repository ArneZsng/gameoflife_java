package de.makaitghahramanianzeising.utils;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Shell;

/**
 * Calculates the size of the representation of a cell
 * in pixels for the view. 
 */

public class CellSizeCalculator {

    private final Shell shell;

    public CellSizeCalculator(Shell shell) {
        this.shell = shell;
    }

    public int getPixelSize(int boardWidth, int boardHeight) {
        Dimension maxCanvas = getMaxCanvas();
        int pixelWidth = maxCanvas.getWidth() / boardWidth;
        int pixelHeight = maxCanvas.getHeight() / boardHeight;
        int pixelSize;
        if (pixelWidth <= pixelHeight) {
            pixelSize = pixelWidth;
        } else {
            pixelSize = pixelHeight;
        }
        return pixelSize;
    }
    
    private Dimension getShellFrameSize() {
        Rectangle shellArea = shell.getBounds();
        Rectangle shellClientArea = shell.getClientArea();
        int shellFrameHeight = shellArea.height - shellClientArea.height;
        int shellFrameWidth = shellArea.width - shellClientArea.width;
        return new Dimension(shellFrameWidth, shellFrameHeight);
    }
    
    private Dimension getMaxCanvas() {
        //TODO controlsHeight!
        int defaultMargin = 5;
        Rectangle clientArea = shell.getDisplay().getPrimaryMonitor().getClientArea();
        Dimension shellFrameSize = getShellFrameSize();
        int maxCanvasHeight = clientArea.height - shellFrameSize.getHeight() - 50 - 3 * defaultMargin;
        int maxCanvasWidth = clientArea.width - shellFrameSize.getWidth() - 2 * defaultMargin;
        return new Dimension(maxCanvasWidth, maxCanvasHeight);
    }

    private class Dimension {
        private final int width;
        private final int height;
        
        public Dimension(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }
}
