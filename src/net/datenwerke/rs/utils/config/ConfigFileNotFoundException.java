package net.datenwerke.rs.utils.config;

public class ConfigFileNotFoundException extends IllegalArgumentException {
	
	private static final long serialVersionUID = -899937845460851945L;

	public ConfigFileNotFoundException() {
		super();
	}

	public ConfigFileNotFoundException(String msg, Throwable t) {
		super(msg, t);
	}

	public ConfigFileNotFoundException(String msg) {
		super(msg);
	}

	public ConfigFileNotFoundException(Throwable msg) {
		super(msg);
	}

	

}
