package page_replacement_algorithms;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FIFO {

	public void performFIFOReplacement(int numberOfFrames, int pages[])
	{
		 int numPages=pages.length;
		 
		 HashSet<Integer> frames=new HashSet<>(numberOfFrames);
		 Queue<Integer> index=new LinkedList<>();
		 
		 int pageFaults=0;
		 int hits=0;
		 
		 for(int i=0;i<numPages;i++)
		 {
			 if(frames.size()<numberOfFrames)
			 {
				 if(!frames.contains(pages[i])) 
				 {
					 frames.add(pages[i]);
					 index.add(pages[i]);
					pageFaults++;
				 }
				 else
				 {
					 hits++;
				 }
			 }
			 else
			 {
				 if(!frames.contains(pages[i]))
				 {
					 int val=index.peek();
					 index.poll();
					 frames.remove(val);
					 
					 frames.add(pages[i]);
					 index.add(pages[i]);
					pageFaults++;
				 }
				 else
				 {
					 hits++;
				 }
			 }
		 }
		 
		 System.out.println("Number of Page Faults : "+pageFaults);
		 System.out.println("Hits:\t"+hits);
		 System.out.println("Hit Percentage: "+((double)hits/(double)pages.length)*100 + "%");
	}
}
