package dev.paie.exception;

import java.util.List;

public class PaieException extends RuntimeException {

	private List<String> messagesErreurs;

	/**
	 * @param messagesErreurs
	 */
	public PaieException(List<String> messagesErreurs) {
		this.messagesErreurs = messagesErreurs;
	}

	/**
	 * @return the messagesErreurs
	 */
	public List<String> getMessagesErreurs() {
		return messagesErreurs;
	}

}
