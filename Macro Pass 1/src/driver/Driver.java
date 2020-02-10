package driver;

import macro_processor.MacroProcessor;

public class Driver {
		public static void main(String args[]){
			new MacroProcessor().performMacroPassOne("INPUT.txt");
		}
}
