package net.datenwerke.rs.terminal.client.terminal.dto.decorator;

import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultExtensionDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultModifierDto;

/**
 * Dto Decorator for {@link CommandResultDto}
 *
 */
public class CommandResultDtoDec extends CommandResultDto {


	private static final long serialVersionUID = 1L;

	public CommandResultDtoDec() {
		super();
	}

	public boolean hasModifier(Class<? extends CommandResultModifierDto> type){
		for(CommandResultModifierDto modifier : getModifiers()){
			boolean found = testModifier(modifier.getClass(), type);
			if(found)
				return true;
		}
		return false;
	}

	private boolean testModifier(Class<?> toCheck, Class<? extends CommandResultModifierDto> toFind) {
		if(null == toCheck)
			return false;
		if(toCheck.equals(toFind))
			return true;
		
		return testModifier(toCheck.getSuperclass(), toFind);
		
	}

	public <E extends CommandResultExtensionDto> E getExtension(Class<E> type) {
		if(null == type)
			return null;
		if(null == getExtensions())
			return null;
		for(CommandResultExtensionDto ext : getExtensions())
			if(type.getClass().equals(ext.getClass()))
				return (E) ext;
		return null;
	}
}
