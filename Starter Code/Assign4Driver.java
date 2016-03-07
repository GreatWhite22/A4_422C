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
        
        //List<String> dictionary = processLinesInDict(args[0]);
        //List<String> wordLadder = processLinesInInput(args[1]);
    	List<String> dictionary = processLinesInDict("dictionary.txt");
        List<String> wordLadder = processLinesInInput("inputs.txt");
        Assignment4Interface wordLadderSolver = new WordLadderSolver(dictionary);
        for(int i = 0; i < wordLadder.size(); i++)
        {
          	String[] inputs = wordLadder.get(i).split(" ");
            try 
            {
                List<String> result = wordLadderSolver.computeLadder(inputs[0],inputs[1]);
                boolean correct = wordLadderSolver.validateResult(inputs[0],inputs[1], result);
                System.out.print("For the input words " + inputs[0] + " and " + inputs[1]);
                if(correct == false)
                {
                	System.err.println("An invalid word ladder was found.");
                }
                else
                {
                	if(result.size() == 0)
                	{
                		System.err.println("At least one of the words " + inputs[0] + " and " + inputs[1] + " are not legitimate 5-letter words from the dictionary");
                	}
                	else
                	{
                		System.out.println(" the following word ladder was found");
                		for(int j = 0; j < result.size(); j++)
                    	{
                    		System.out.println(result.get(j));
                    	}
                	}
                }
            } 
            catch (NoSuchLadderException e) 
            {
                System.err.println("\nThere is no word ladder between " + inputs[0] + " and " + inputs[1] + "!");
            }
            finally
            {
            	System.out.println("**********");
            }
        }

    }

/******************************************************************************
* Method Name: processLinesInFile                                             *
* Purpose: Opens the file specified in String filename, reads each line in it *
*          Invokes translate () on each line in the file, and prints out the  *
*          translated piglatin string.                                        *
* Returns: None                                                               *
******************************************************************************/
public static List<String> processLinesInDict (String filename) 
{ 
	List<String> myList = new ArrayList<String>();	
	try 
	{
		FileReader freader = new FileReader(filename);
		BufferedReader reader = new BufferedReader(freader);
		
		for (String s = reader.readLine(); s != null; s = reader.readLine()){
			if(!(s.charAt(0) == '*')){
				if(s.length() < 5)
				{
					System.err.println("Not valid dictionary entry.");
				}
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

public static List<String> processLinesInInput (String filename) 
{ 

	List<String> myList = new ArrayList<String>();	
	try 
	{
		FileReader freader = new FileReader(filename);
		BufferedReader reader = new BufferedReader(freader);
		
		for (String s = reader.readLine(); s != null; s = reader.readLine()){
			String[] temp = s.split(" ");
			int index = 0;
			boolean foundError = false;
			if(index == temp.length)
			{
				System.err.println("Not valid input pair.");
				continue;
			}
			while(temp[index].equals(""))
			{
				index++;
				if(index >= temp.length)
				{
					foundError = true;
					break;
				}
			}
			if(foundError)
			{
				System.err.println("Not valid input pair.");
				continue;
			}
			if(temp[index].length() != 5)
			{
				System.err.println("Not valid input pair.");
				continue;
			}
			for(int i = 0; i < 5; i++){
				if((temp[index].charAt(i) < 'a') || (temp[index].charAt(i) > 'z'))
				{
					foundError = true;
					break;
				}
			}
			if(foundError)
			{
				System.err.println("Not valid input pair.");
				continue;
			}	
			int wordLoc1 = index;
			index++;
			if(index >= temp.length)
			{
				System.err.println("Not valid input pair.");
				continue;
			}
			while(temp[index].equals(""))
			{
				index++;
				if(index >= temp.length)
				{
					foundError = true;
					break;
				}
			}
			if(foundError)
			{
				System.err.println("Not valid input pair.");
				continue;
			}
			if(temp[index].length() != 5)
			{
				System.err.println("Not valid input pair.");
				continue;
			}
			for(int i = 0; i < 5; i++){
				if((temp[index].charAt(i) < 'a') || (temp[index].charAt(i) > 'z'))
				{
					foundError = true;
					break;
				}
			}
			if(foundError)
			{
				System.err.println("Not valid input pair");
				continue;
			}
			int wordLoc2 = index;		
			boolean finished = false;
			do
			{
				index++;
				if(index >= temp.length)
				{
					myList.add(temp[wordLoc1] + "  " + temp[wordLoc2]);
					finished = true;
					break;
				}
			}while(temp[index].equals(""));
			if(finished)
			{
				continue;
			}
			System.err.println("Not valid input pair.");
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
