// Four Run Length Encoding methods: Encode with zero and no wrap-around
import java.io.*;
import java.util.Scanner;
public class main{
	public static class RunLength{
		private int numRows, numCols, minVal, maxVal;
    public RunLength(Scanner in, PrintWriter pw){
			numRows = in.nextInt();
			numCols = in.nextInt();
			minVal = in.nextInt();
			maxVal = in.nextInt();
      pw.println(numRows+ " " + numCols + " " + minVal + " " + maxVal + " : Header");
    	  int r = 0;
    	  while( in.hasNext() ){// 8: repeat Step 1 - 7 until eof
    	  	  int c = 0; // step 1
    	    int count = 0; // keeps track of pixelvalue
    	    int currVal = in.nextInt();// read the next pixel (integer) from inFile 
    	    
    	    pw.print( r + " " + c + " " + currVal );// 2: print to outFile, count ++
    	    count++;
    	    
    	    // 6: repeat step 3 - 5 until end of text line
    	    while( in.hasNext() ){ 
    	    	  c++; // 3
    	    	  if( c == numCols ){ break; }
    	    	  
    	      int nextVal = in.nextInt(); // 4: nextVal <-- read the next pixel (integer) from inFile
	    	    if( nextVal == currVal ){ count++; } // 5 same value/ increase count
	        else{
	          	pw.print( " " + count + "\n"); // output count to outFile
	          currVal = nextVal;
	          count = 1;
	   	      pw.print( r + " " + c + " " + currVal );
	        }
    	    }
    	    pw.print( " " + count + "\n"); // count of last column value before incrementing rows
        r++; // 7
    	  }
    	  
    	  // 9: close all files
    	  in.close();
    	  pw.close();
    }
    
	}
	public static void main( String []args ) throws Exception{
		if( args.length != 2 ){
			System.out.println("Format: <Inputfile> <Outputfile> ");
			return;
		}
		try{ Scanner inFile = new Scanner(new FileReader(args[0]));
			   PrintWriter outFile = new PrintWriter(new FileWriter(args[1]));
		     RunLength rl = new RunLength(inFile,outFile);	} // constructor to call steps
		catch(FileNotFoundException e) { System.out.println(e + " : Files Not Found"); }
		catch(IOException e) { System.out.println(e + " : Files Not Found"); }
  }
}