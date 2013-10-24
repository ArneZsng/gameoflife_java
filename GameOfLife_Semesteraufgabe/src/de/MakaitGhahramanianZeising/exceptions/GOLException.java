package de.MakaitGhahramanianZeising.exceptions;

public class GOLException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2402416882699940169L;

	public GOLException() {}

    public GOLException(String message) {
    	super(message);
    }

	public GOLException(Throwable cause) {
		super(cause);
	}

	public GOLException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
