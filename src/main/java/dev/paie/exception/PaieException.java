package dev.paie.exception;

import java.util.List;

@SuppressWarnings("serial")
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

	/**
	 * @param messagesErreurs the messagesErreurs to set
	 */
	public void setMessagesErreurs(List<String> messagesErreurs) {
		this.messagesErreurs = messagesErreurs;
	}

}
