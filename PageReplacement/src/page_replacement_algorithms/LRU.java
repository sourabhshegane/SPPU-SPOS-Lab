package page_replacement_algorithms;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class LRU {
	public void performLRUReplacement(int numberOfFrames, int pages[])
	{	
		 HashSet<Integer> frames=new HashSet<>(numberOfFrames);
		 HashMap<Integer,Integer>  index=new HashMap<>();
		 int pageFaults=0;
		 int hits=0;
		 for(int i=0;i<pages.length;i++)
		 {
			 if(frames.size()<numberOfFrames)
			 {
				 if(!frames.contains(pages[i])) 
				 {
					 frames.add(pages[i]);
					 index.put(pages[i],i);
					 pageFaults++;
				 }
				 else
				 {
					 hits++;
					 index.put(pages[i],i); 
				 }
			 }
			 else
			 {
				 if(!frames.contains(pages[i]))
				 {
					 int lru=Integer.MAX_VALUE;
					 int val=Integer.MIN_VALUE;
					
					 Iterator<Integer> itr=frames.iterator();
					 while(itr.hasNext())
					 {
						 int temp=itr.next();
						 if(index.get(temp)<lru)
						 {
							 lru=index.get(temp);
							 val=temp;
						 }
					 }
					 
					 frames.remove(val);
					 frames.add(pages[i]);
					 pageFaults++;
					 index.put(pages[i], i);
				 }
				 else
				 {
					 hits++;
					 index.put(pages[i],i); 
				 }
			 }
		 }
		 
		 System.out.println("Number of Page Faults : "+pageFaults);
		 System.out.println("Hits:\t"+hits);
		 System.out.println("Hit Percentage: "+((double)hits/(double)pages.length)*100 + "%");

	}
}

