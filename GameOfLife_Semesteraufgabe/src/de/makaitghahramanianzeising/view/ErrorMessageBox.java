package de.makaitghahramanianzeising.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

/**
 * Renders a popup with an error message, if the
 * user's settings where not valid or the game
 * cannot be displayed on the user's viewport.
 */

public class ErrorMessageBox {

    public ErrorMessageBox(Shell shell, String message) {
        final MessageBox messageBox = new MessageBox(shell, SWT.OK);
        messageBox.setMessage(message);
        messageBox.setText("Fehler beim Spielstart");
        messageBox.open();
    }

}
