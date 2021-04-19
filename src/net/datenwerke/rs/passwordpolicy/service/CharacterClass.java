package net.datenwerke.rs.passwordpolicy.service;


public class CharacterClass {
	
	private char[] characters;
	
	public CharacterClass(String characters) {
		this.characters = characters.toCharArray();
	}
	
	public char[] getCharacters() {
		return characters;
	}
	
}
