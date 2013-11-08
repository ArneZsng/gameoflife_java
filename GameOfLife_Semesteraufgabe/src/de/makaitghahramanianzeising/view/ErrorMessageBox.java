package de.makaitghahramanianzeising.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class ErrorMessageBox {
	
	public ErrorMessageBox(Shell shell, String message) {
		final MessageBox messageBox = new MessageBox(shell, SWT.OK);
		messageBox.setMessage(message);
		messageBox.setText("Fehler beim Spielstart");
		messageBox.open();
	}

}
