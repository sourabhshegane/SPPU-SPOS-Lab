package driver;

import macroprocessor.MacroProcessor;

public class Driver {
	public static void main(String args[]) throws Exception {
		new MacroProcessor().performMacroPassTwo();
	}
}
