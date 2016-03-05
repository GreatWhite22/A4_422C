package assignment4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.*;

public class Assign4Driver
{
    public static void main(String[] args)
    {
        // Create a word ladder solver object
        Assignment4Interface wordLadderSolver = new WordLadderSolver();
        List<String> dictionary = processLinesInFile(args[0]);
        List<String> wordLadder = processLinesInFile(args[1]);
        try 
        {
            List<String> result = wordLadderSolver.computeLadder("money", "honey");
            boolean correct = wordLadderSolver.validateResult("money", "honey", result);
        } 
        catch (NoSuchLadderException e) 
        {
            e.printStackTrace();
        }
    }

/******************************************************************************
* Method Name: processLinesInFile                                             *
* Purpose: Opens the file specified in String filename, reads each line in it *
*          Invokes translate () on each line in the file, and prints out the  *
*          translated piglatin string.                                        *
* Returns: None                                                               *
******************************************************************************/
public static List<String> processLinesInFile (String filename) 
{ 

	List<String> myList = new ArrayList<String>();	
	try 
	{
		FileReader freader = new FileReader(filename);
		BufferedReader reader = new BufferedReader(freader);
		
		for (String s = reader.readLine(); s != null; s = reader.readLine()){
			if(!(s.charAt(0) == '*')){
				myList.add(s.substring(0,5));
			}
		}
		return myList;
	} 
	catch (FileNotFoundException e) 
	{
		System.err.println ("Error: File not found. Exiting...");
		e.printStackTrace();
		System.exit(-1);
	} catch (IOException e) 
	{
		System.err.println ("Error: IO exception. Exiting...");
		e.printStackTrace();
		System.exit(-1);
	}
	return myList;
}
}
