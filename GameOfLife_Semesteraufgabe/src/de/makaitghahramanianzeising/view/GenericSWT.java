package de.makaitghahramanianzeising.view;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Generic implementation of a view that can center the shell on the display,
 * start the view, return and dispose the shell. It can also return if the shell is disposed.
 */

public abstract class GenericSWT {

    protected Shell shell;
    protected Display display;

    public void start() {
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        shell.dispose();
    }

    public void dispose() {
        shell.dispose();
    }

    public boolean isDisposed() {
        return shell.isDisposed();
    }

    public Shell getShell() {
        return shell;
    }

    protected void centerOnClientArea() {
        Rectangle clientArea = shell.getDisplay().getPrimaryMonitor().getClientArea();
        Point shellSize = shell.getSize();
        int xPos = clientArea.x + (clientArea.width - shellSize.x) / 2;
        int yPos = clientArea.y + (clientArea.height - shellSize.y) / 2;
        if (xPos >= 0 && yPos >= 0) {
            shell.setLocation(xPos, yPos);
        } else {
            shell.setLocation(0, 0);
        }
    }
}
