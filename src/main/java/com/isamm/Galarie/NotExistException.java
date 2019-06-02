package com.isamm.Galarie;

public class NotExistException extends Exception {

	@Override
	public String getMessage() {
		return "NotExistException!\nIl n'ya pas un oeuvre avec le nom ";

	}
}
