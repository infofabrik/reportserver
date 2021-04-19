package net.datenwerke.rs.passwordpolicy.service.passwordgenerator;

import net.datenwerke.rs.passwordpolicy.service.CharacterClass;
import net.datenwerke.rs.passwordpolicy.service.CharacterClassBasedPasswordComplexitySpecification;
import net.datenwerke.rs.passwordpolicy.service.CharacterSelectionSpecification;
import net.datenwerke.rs.passwordpolicy.service.PasswordGenerator;

public class DefaultPasswordGenerator implements PasswordGenerator {

	@Override
	public String newPassword() {
		CharacterClassBasedPasswordComplexitySpecification specification = new CharacterClassBasedPasswordComplexitySpecification(8);
		
		CharacterClass cClass = new CharacterClass("abcdefghkmnopqrstuvwxyzABCDEFGHJKLMNPQRSTUFVXYZ123456789");
		
		specification.addSelectionSpecification(new CharacterSelectionSpecification(cClass, 8));
		
		return specification.getPasswordGenerator().newPassword();
	}

}
