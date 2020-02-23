package providers;

public class KeywordParameterNameTable {

	private int index;
	private String parameterName, defaultValue;
	
	public KeywordParameterNameTable() {
	}

	public KeywordParameterNameTable(int index, String parameterName, String defaultValue) {
		this.index = index;
		this.parameterName = parameterName;
		this.defaultValue = defaultValue;
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

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
}
