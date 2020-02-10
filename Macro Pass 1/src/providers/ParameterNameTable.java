package providers;

public class ParameterNameTable {
	private int index;
	private String parameterName;
	
	public ParameterNameTable() {
		index = 0;
	}
	
	public ParameterNameTable(int index, String parameterName) {
		super();
		this.index = index;
		this.parameterName = parameterName;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	
}
