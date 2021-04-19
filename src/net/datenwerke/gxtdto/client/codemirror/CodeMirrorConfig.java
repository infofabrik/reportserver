package net.datenwerke.gxtdto.client.codemirror;

public class CodeMirrorConfig {
	
	private String mode = "javascript";
	private boolean lineNumbersVisible;
	private boolean matchBrackets;
	
	public CodeMirrorConfig() {

	}

	public CodeMirrorConfig(String mode){
		this(mode, false);
	}

	public CodeMirrorConfig(String mode, boolean lineNumbersVisible) {
		this.mode = mode;
		this.lineNumbersVisible = lineNumbersVisible;
		init(mode);
	}

	protected void init(String mode) {
		if("text/x-groovy".equals(mode) ||
		   "text/x-sql".equals(mode)){
			setMatchBrackets(true);
		}
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public boolean isLineNumbersVisible() {
		return lineNumbersVisible;
	}
	
	public void setLineNumbersVisible(boolean lineNumbersVisible) {
		this.lineNumbersVisible = lineNumbersVisible;
	}
	
	public boolean isMatchBrackets() {
		return matchBrackets;
	}
	
	public void setMatchBrackets(boolean matchBrackets) {
		this.matchBrackets = matchBrackets;
	}
	
}
