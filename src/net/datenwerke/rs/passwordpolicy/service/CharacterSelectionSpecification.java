package net.datenwerke.rs.passwordpolicy.service;

/**
 * Combines a class of characters with the number of characters to be chosen from this class
 * 
 *
 */
public class CharacterSelectionSpecification {
	
	private CharacterClass characterClass;
	private int minOccurrences;

	
	public CharacterSelectionSpecification(CharacterClass characterClass, int minOccurrences) {
		this.characterClass = characterClass;
		this.minOccurrences = minOccurrences;
	}


	public CharacterClass getCharacterClass() {
		return characterClass;
	}


	public int getMinOccurrences() {
		return minOccurrences;
	}
	
}
