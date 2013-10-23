package de.MakaitGhahramanianZeising.exceptions;

public class FileException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2402416882699940169L;

	public FileException() {}

    public FileException(String message) {
    	super(message);
    }

	public FileException(Throwable cause) {
		super(cause);
	}

	public FileException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
