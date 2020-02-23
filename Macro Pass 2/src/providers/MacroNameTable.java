package providers;

public class MacroNameTable {
	private String macroName;
	private int positionalParameter, keywordParameter, macroDefinationTablePointer, keywordParameterTablePointer;
	
	public MacroNameTable() {
		
	}
	
	public MacroNameTable(String macroName, int positionalParameter,int keywordParameter, int macroDefinationTablePointer,int keywordParameterTablePointer) {
		this.macroName = macroName;
		this.positionalParameter = positionalParameter;
		this.keywordParameter = keywordParameter;
		this.macroDefinationTablePointer = macroDefinationTablePointer;
		this.keywordParameterTablePointer = keywordParameterTablePointer;
	}

	public String getMacroName() {
		return macroName;
	}

	public void setMacroName(String macroName) {
		this.macroName = macroName;
	}

	public int getPositionalParameter() {
		return positionalParameter;
	}

	public void setPositionalParameter(int positionalParameter) {
		this.positionalParameter = positionalParameter;
	}

	public int getKeywordParameter() {
		return keywordParameter;
	}

	public void setKeywordParameter(int keywordParameter) {
		this.keywordParameter = keywordParameter;
	}

	public int getMacroDefinationTablePointer() {
		return macroDefinationTablePointer;
	}

	public void setMacroDefinationTablePointer(int macroDefinationTablePointer) {
		this.macroDefinationTablePointer = macroDefinationTablePointer;
	}

	public int getKeywordParameterTablePointer() {
		return keywordParameterTablePointer;
	}

	public void setKeywordParameterTablePointer(int keywordParameterTablePointer) {
		this.keywordParameterTablePointer = keywordParameterTablePointer;
	}
	
}
