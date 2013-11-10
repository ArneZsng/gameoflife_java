package de.makaitghahramanianzeising.utils;

import java.awt.Dimension;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Shell;

import de.makaitghahramanianzeising.view.components.GameControls;
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
        int pixelWidth = maxCanvas.width / boardWidth;
        int pixelHeight = maxCanvas.height / boardHeight;
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
        GridLayout defaultGrid = new GridLayout();
        int defaultMarginHeight = defaultGrid.marginHeight;
        int defaultMarginWidth = defaultGrid.marginWidth;
        Rectangle clientArea = shell.getDisplay().getPrimaryMonitor().getClientArea();
        Dimension shellFrameSize = getShellFrameSize();
        int maxCanvasHeight = clientArea.height - shellFrameSize.height - GameControls.CONTROLS_HEIGHT - 3 * defaultMarginHeight;
        int maxCanvasWidth = clientArea.width - shellFrameSize.width - 2 * defaultMarginWidth;
        return new Dimension(maxCanvasWidth, maxCanvasHeight);
    }

}
