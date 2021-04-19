package net.datenwerke.rs.passwordpolicy.service;

import java.util.List;

public interface PasswordComplexitySpecification {
	
	public int getPasswordMinLength();

	public PasswordGenerator getPasswordGenerator();
	
	public boolean isSatisfiedBy(String password);

	public List<String> getErrorCause(String password);

}
