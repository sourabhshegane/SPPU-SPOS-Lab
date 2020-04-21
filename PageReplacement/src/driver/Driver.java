package driver;
import java.util.Scanner;
import page_replacement_algorithms.FCFS;
import page_replacement_algorithms.LRU;
import page_replacement_algorithms.Optimal;

public class Driver {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter number of frames: ");
		int numberOfFrames = scanner.nextInt();
		System.out.println("Enter number of pages: ");
		int numberOfPages = scanner.nextInt();
		int pages[] = new int[numberOfPages];
		System.out.println("Enter the reference String");
		for(int i = 0; i < numberOfPages ; i++) {
			pages[i] = scanner.nextInt();
		}
		int FIFOPages[] = pages;
		int LRUPages[] = pages;
		int OptimalPages[] = pages;

		
		System.out.println("FIFO Page Replacement");
		FCFS fcfs=new FCFS();
		fcfs.performFIFOReplacement(numberOfFrames, FIFOPages);	
	
		System.out.println("*********************************************************");
		System.out.println("LRU Page Replacement");
		LRU lru=new LRU();
		lru.performLRUReplacement(numberOfFrames, LRUPages);	
		
		System.out.println("*********************************************************");
		System.out.println("Optimal Page Replacement");
		Optimal optimal=new Optimal();
		optimal.performOptimalReplacement(numberOfFrames, OptimalPages);	
	}

}
