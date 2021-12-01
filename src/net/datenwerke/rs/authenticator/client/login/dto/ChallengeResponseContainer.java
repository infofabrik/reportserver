package net.datenwerke.rs.authenticator.client.login.dto;

import java.io.Serializable;



public class ChallengeResponseContainer implements Serializable{

	private static final long serialVersionUID = -8438464227531800394L;
	
	private long id;
	private byte[] challenge;
	private byte[] response;

	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public byte[] getChallenge() {
		return challenge;
	}

	public void setChallenge(byte[] challenge) {
		this.challenge = challenge;
	}
	
	public byte[] getResponse() {
		return response;
	}
	
	public void setResponse(byte[] response) {
		this.response = response;
	}

}
