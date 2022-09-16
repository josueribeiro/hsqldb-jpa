package exception;

import java.util.HashSet;
import java.util.Set;

public class ValiationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Set<String> errors = new HashSet<>();

	public void addError(String error) {
		errors.add(error);
	}

}
