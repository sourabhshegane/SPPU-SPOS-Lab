package macroprocessor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;

import providers.MacroNameTable;

public class MacroProcessor {
	private BufferedReader bufferedReader = null ;	
	private HashMap<Integer, String> ALTAB = null;
	private String contentToWrite = "";
	public void performMacroPassTwo() throws Exception {
		String line = "DEFAULT";
		bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("OUTPUT.txt")));
		while((line = bufferedReader.readLine()) != null) {
			ALTAB = new HashMap<Integer, String>();
			int ALTAB_POINTER = 1;
			int actualParameterCount = 0;
			if(line.equals("START")) {
				contentToWrite += line + "\n";
				continue;
			}
			else {
				String []arr = line.split("\t");
				//System.out.println(arr[0]);
				if(getMacroDefinationTableEntry(arr[0]) != null) {
					MacroNameTable macroNameTable = getMacroDefinationTableEntry(arr[0]);
					
					//Create PNTAB of size PP + KP
					int numberOfParameters = macroNameTable.getPositionalParameter() + macroNameTable.getKeywordParameter();
				
					for(int i = 1 ; i < arr.length ; i++) {
						System.out.println("**********"+arr[i].replace(",",""));
						String parameter = arr[i].replace(",", "");
						parameter = parameter.substring(parameter.lastIndexOf("=") + 1);
						ALTAB.put(ALTAB_POINTER,parameter);
						ALTAB_POINTER ++;
						actualParameterCount++;
					}
					
					if(actualParameterCount < numberOfParameters) {
						String remainingParameter = getKeywordParameter(macroNameTable.getKeywordParameterTablePointer());
						ALTAB.put(ALTAB_POINTER, remainingParameter);
						ALTAB_POINTER ++;
					}
					
					readMacroDefinationTable(macroNameTable.getMacroDefinationTablePointer());
					
					
				}else {
					//System.out.println("ERROR : MACRO NOT FOUND" );
				}
			}
			System.out.println(Arrays.asList(ALTAB));
		}
		
		System.out.println(contentToWrite);
		writeToFile();
	}
	
	private MacroNameTable getMacroDefinationTableEntry(String macroName) throws Exception {
		BufferedReader bufferedReaderMNT = new BufferedReader(new InputStreamReader(new FileInputStream("MNT.txt")));
		String mntLine = "DEFAULT";
		MacroNameTable macroNameTable = null;
		while((mntLine = bufferedReaderMNT.readLine()) != null) {
			String mntArray[] = mntLine.split("\t");
			if(mntArray[0].equals(macroName)) {
				macroNameTable = new MacroNameTable();
				macroNameTable.setMacroName(macroName);
				macroNameTable.setPositionalParameter(Integer.parseInt(mntArray[1]));
				macroNameTable.setKeywordParameter(Integer.parseInt(mntArray[2]));
				macroNameTable.setMacroDefinationTablePointer(Integer.parseInt(mntArray[3]));
				macroNameTable.setKeywordParameterTablePointer(Integer.parseInt(mntArray[4]));
			}
		}
		
		return macroNameTable;
	}
	
	private void readMacroDefinationTable(int lineNumber) throws Exception {
		BufferedReader bufferedReaderMDT = new BufferedReader(new InputStreamReader(new FileInputStream("MDTAB.txt")));
		String mdtLine = "DEFAULT";
		int currentLineNumber = 1;
		while((mdtLine = bufferedReaderMDT.readLine()) != null) {
			if(mdtLine.contains("MEND") && currentLineNumber >= lineNumber) 
				break;
			
			String mdtArray[] = mdtLine.split("\t");
			
			if(currentLineNumber >= lineNumber) {
				for(int i = 1 ; i < mdtArray.length ; i++) {
					if(mdtArray[i].contains("P")) {
						System.out.println(mdtArray[i].replaceAll("[^0-9]", ""));
						contentToWrite +="	"+ ALTAB.get(Integer.parseInt(mdtArray[i].replaceAll("[^0-9]", "")));
					}else {
						contentToWrite += mdtArray[i];
					}
				}
				contentToWrite += "\n";
			}
			currentLineNumber ++;
		}
	}
	
	private String getKeywordParameter(int lineNumber) throws Exception {
		BufferedReader bufferedReaderKPDTAB = new BufferedReader(new InputStreamReader(new FileInputStream("KPDTB.txt")));
		String kpdtLine = "DEFAULT";
		int currentLineNumber = 1;
		
		while((kpdtLine = bufferedReaderKPDTAB.readLine()) != null) {
			if(currentLineNumber == lineNumber) {
				String kpdtabEntries[] = kpdtLine.split("\t");
				return kpdtabEntries[2];
			}
			
			currentLineNumber++;
		}
		
		return "NOT_FOUND";
	}
	
	private void writeToFile() throws IOException {
        Files.writeString(Paths.get("OUTPUT.txt"), contentToWrite);
	}
}
