package providers;

public class MacroDefinationTable {
	private int index;
	private String instruction;
	
	public MacroDefinationTable() {
		index = 1;
	}
	public MacroDefinationTable(int index, String instruction) {
		this.index = index;
		this.instruction = instruction;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	
	public int incrementMacroDefinationTablePointer() {
		return index++;
	}
}
