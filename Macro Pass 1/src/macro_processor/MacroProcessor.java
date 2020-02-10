package macro_processor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import providers.KeywordParameterNameTable;
import providers.MacroDefinationTable;
import providers.MacroNameTable;

public class MacroProcessor {
	private BufferedReader bufferedReader = null;
	private ArrayList<MacroNameTable> macroNameTableList = new ArrayList<MacroNameTable>();
	private ArrayList<KeywordParameterNameTable> keywordParameterTable = new ArrayList<KeywordParameterNameTable>();
	private ArrayList<MacroDefinationTable> macroDefinationTableList  =new ArrayList<MacroDefinationTable>();
	private HashMap<String, Integer> parameterNameTable = null;
	private KeywordParameterNameTable keywordParameterNameTable = null;
	private MacroDefinationTable macroDefinationTable = null;
	private MacroNameTable macroNameTable = null;

	public void performMacroPassOne(String fileName){
		try {
			String line = "DEFAULT";
			parameterNameTable = new HashMap<String, Integer>();
			int lineCounter = 0, macroDefinationTablePointer = 1, macroNameTablePointer = 1, keywordParameterNameTablePointer = 0, positionalParameterCounter = 0, keywordParametersCounter = 0;
			bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			while((line = bufferedReader.readLine()) != null) {
				String []arr = line.split("\t");
				if(line.contains("START")){
					removeMacroDefinations();
					break;
				}
				
				if(line.contains("MACRO")){
					lineCounter = 0;
					macroNameTablePointer++;
					keywordParametersCounter = 0;
					parameterNameTable.clear();
				}
				
				if(lineCounter == 1){
					macroNameTable = new MacroNameTable();
					positionalParameterCounter = 0;
					for(int i = 1 ; i < arr.length ; i++) {
						if(arr[i].contains("&")) {
							if(arr[i].contains("=")) {
								  keywordParametersCounter++;
								  keywordParameterNameTablePointer++;
								  keywordParameterNameTable = new KeywordParameterNameTable();
								  keywordParameterNameTable.setIndex(keywordParameterNameTablePointer);
								  keywordParameterNameTable.setParameterName(arr[i].substring(0, arr[i].indexOf('=')));
								  
								  if(arr[i].substring(arr[i].indexOf('=')) == null)
									  keywordParameterNameTable.setDefaultValue("");
								  else
									  keywordParameterNameTable.setDefaultValue(arr[i].substring(arr[i].indexOf('=') + 1));
								  
								  keywordParameterTable.add(keywordParameterNameTable);
								  arr[i] = arr[i].substring(0, arr[i].indexOf('='));
							}else
								positionalParameterCounter++;
							
							if(arr[i].contains(","))
								parameterNameTable.put(arr[i].substring(0, arr[i].length() - 1), i);
							else
								parameterNameTable.put(arr[i], i);
						}
						
					}
				
					macroNameTable.setMacroName(arr[0]);	
					macroNameTable.setPositionalParameter(positionalParameterCounter);
					macroNameTable.setKeywordParameter(keywordParametersCounter);
					macroNameTable.setMacroDefinationTablePointer(macroDefinationTablePointer);
					macroNameTable.setKeywordParameterTablePointer(keywordParameterNameTablePointer);
					macroNameTableList.add(macroNameTable);
					
				}else{
					 if(!line.contains("MACRO")) {
						 if(line.contains("&")) {
							 String currentLine = arr[0];
							 for(int i = 1 ; i < arr.length ; i++) {
								 if(arr[i].contains("&")) {
									 if(arr[i].contains(",")) {
										 currentLine += " (P, " + parameterNameTable.get(arr[i].substring(0, arr[i].length() - 1)) + ")";
									 }else {
										 currentLine += " (P, " + parameterNameTable.get(arr[i]) + ")";
									 }
								 }
							 }
							 MacroDefinationTable definationTable = new MacroDefinationTable();
							 definationTable.setIndex(macroDefinationTablePointer);
							 definationTable.setInstruction(currentLine);		
							 macroDefinationTableList.add(definationTable);
							 macroDefinationTablePointer ++;
						 }else {
							 MacroDefinationTable definationTable = new MacroDefinationTable();
							 definationTable.setIndex(macroDefinationTablePointer);
							 definationTable.setInstruction(line);		
							 macroDefinationTableList.add(definationTable);
							 macroDefinationTablePointer ++;
						 }
					 }
				}
				
				lineCounter++;
			}
			
			System.out.println("Done Reading the file.");
			System.out.println("MNTAB");
			printMacroNameTable();
			System.out.println("PNTAB");
			printParameterNameTable();
			System.out.println("KPDTAB");
			printkeyWordParameterNameTable();
			System.out.println("MDTAB");
			printMacroDefinationTable();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void printMacroNameTable() throws FileNotFoundException {

		PrintWriter pw = new PrintWriter(new FileOutputStream("MNT.txt"));
		
			for(MacroNameTable m : macroNameTableList) {
				pw.println(m.getMacroName()+" "+ m.getPositionalParameter() + " " + m.getKeywordParameter() + " " + m.getMacroDefinationTablePointer() + " " + m.getKeywordParameterTablePointer());
			}
			pw.close();
	}
	
	private void printParameterNameTable(){
		System.out.println(Arrays.asList(parameterNameTable));
	}
	
	private void printkeyWordParameterNameTable() throws FileNotFoundException{
		 PrintWriter pw = new PrintWriter(new FileOutputStream("KPDTB.txt"));
		 
		 for(KeywordParameterNameTable k : keywordParameterTable){
			pw.println(k.getIndex() + " " + k.getParameterName() + " " + k.getDefaultValue());
		 }	        	
		 
		 pw.close();
	}
	
	private void printMacroDefinationTable() throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new FileOutputStream("MDTAB.txt"));
		
		for(MacroDefinationTable m : macroDefinationTableList) {
			pw.println(m.getIndex()+" "+ m.getInstruction());
		}
		
		pw.close();
	}
	
	private void removeMacroDefinations() throws FileNotFoundException{
		try {
			FileReader fr = new FileReader("INPUT.txt");
			BufferedReader br = new BufferedReader(fr);
			FileWriter fw = new FileWriter("OUTPUT.txt", true);
			String s;
			boolean flag = false;
			while ((s = br.readLine()) != null) { 
				if(s.contains("START")){
					flag = true;
				}
				if(flag){
					fw.write(s + "\n"); // write to output file
					fw.flush();
				}
			}
			br.close();
			fw.close();
                        System.out.println("file copied");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
