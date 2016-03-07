//Lewis, Connor and Bargas,Alec
//csl735  and apb973
//EE 422C - Assignment 4 -
package assignment4;

import java.util.ArrayList;
import java.util.List;

// do not change class name or interface it implements
public class WordLadderSolver implements Assignment4Interface
{
    // delcare class members here.
	List<String> dictionary;
	List<String> solutionList;
	List<String> checkedList;
    // add a constructor for this object. HINT: it would be a good idea to set up the dictionary there
	public WordLadderSolver()
	{
		dictionary = null;
	}
	
	public WordLadderSolver(String dict){
		Assign4Driver Driver = new Assign4Driver();
		dictionary = Driver.processLinesInDict(dict);
	}
	
	public WordLadderSolver(List<String> dict)
	{
		dictionary = dict;
	}

    // do not change signature of the method implemented from the interface
    @Override
    public List<String> computeLadder(String startWord, String endWord) throws NoSuchLadderException 
    {
    	checkedList = new ArrayList<String>();						//empty local lists
    	solutionList = new ArrayList<String>();
    	if((startWord.length() != 5) || (endWord.length() != 5))
    	{															//make sure words are right length
    		return null;
    	}
    	else if(makeLadder(startWord, endWord, -1))
    	{
            return solutionList;									//begin actual execution
    	}
    	else
    	{
    		throw new NoSuchLadderException("");					//if no ladder is found, throw exception
    	}

    }
    
    public boolean makeLadder(String startWord, String endWord, int index) throws NoSuchLadderException 
    {
    	solutionList.add(startWord);
		if(startWord.equals(endWord))
		{
			return true;					//see if you have found the word ladder
		}
    	index = (index+1)%5;
    	ArrayList<String> possibleWords = new ArrayList<String>();
        for(int i = 0; i < dictionary.size(); i++)
        {
        	if(dictionary.get(i).charAt(index) == endWord.charAt(index))		//check to see if a word in the dictionary is one letter off of the end word at the desired location
        	{
        		if((dictionary.get(i).charAt((index+1)%5) != startWord.charAt((index+1)%5)) || (dictionary.get(i).charAt((index+2)%5) != startWord.charAt((index+2)%5)) || (dictionary.get(i).charAt((index+3)%5) != startWord.charAt((index+3)%5)) || (dictionary.get(i).charAt((index+4)%5) != startWord.charAt((index+4)%5))) 
        		{
        			continue;								//make sure any word we validate matches the starting word
        		}
        		if(!(solutionList.contains(dictionary.get(i))))			//make sure the word has not already been checked
        		{
        			int differentLetters = 0;
        			for(int j = 0; j < 5; j++)
        			{
        				if(dictionary.get(i).charAt(j) != endWord.charAt(j))
        				{														//see how far off the word in the dictionary is from the final word
        					differentLetters++;
        				}
        			}
        			if(differentLetters <= 1)
        			{
        				solutionList.add(dictionary.get(i));
        				return true;										//if only one letter is different from the end, you have found it
        			}
        			possibleWords = addSort(differentLetters + dictionary.get(i), possibleWords);		//add the word to the list of possibilities if all conditions have been met, giving priority to those that are closest to the final word
        		}	
        	}
        }
        checkedList.add(startWord);							//show that we have handled the case when we consider the starting word
        for(int k = 0; k < possibleWords.size(); k++)
        {
        	if(checkedList.contains(possibleWords.get(k)))
    		{
    			continue;															//check each likely word
    		}
        	if(makeLadder(possibleWords.get(k).substring(1, 6),endWord,index))
        	{
        		return true;
        	}
        }
        
        for(int f = 0; f < dictionary.size(); f++)					//check the slightly less likely words
        {
        	if(checkedList.contains(dictionary.get(f)))
    		{
    			continue;
    		}
        	if(dictionary.get(f).charAt((index + 1) % 5) == endWord.charAt((index + 1) % 5))
        	{
        		if((dictionary.get(f).charAt((index+2)%5) != startWord.charAt((index+2)%5)) || (dictionary.get(f).charAt((index+5)%5) != startWord.charAt((index+5)%5)) || (dictionary.get(f).charAt((index+3)%5) != startWord.charAt((index+3)%5)) || (dictionary.get(f).charAt((index+4)%5) != startWord.charAt((index+4)%5))) 
        		{
        			continue;
        		}																//if there is a word where the index+1 char is different from the current word, which is the same as the char at the spot in the end word, but when the rest are the same
        		if(makeLadder(dictionary.get(f),endWord, (index) % 5))
        		{
        			return true;
        		}
        	}
        	if(dictionary.get(f).charAt((index + 2) % 5) == endWord.charAt((index + 2) % 5))			//repeat above for index+2
        	{
        		if((dictionary.get(f).charAt((index+1)%5) != startWord.charAt((index+1)%5)) || (dictionary.get(f).charAt((index+5)%5) != startWord.charAt((index+5)%5)) || (dictionary.get(f).charAt((index+3)%5) != startWord.charAt((index+3)%5)) || (dictionary.get(f).charAt((index+4)%5) != startWord.charAt((index+4)%5))) 
        		{
        			continue;
        		}       		
        		if(makeLadder(dictionary.get(f),endWord, (index + 1) % 5))
        		{
        			return true;
        		}
        	}
        	if(dictionary.get(f).charAt((index + 3) % 5) == endWord.charAt((index + 3) % 5))		//repeat above for index+3
        	{
        		if((dictionary.get(f).charAt((index+1)%5) != startWord.charAt((index+1)%5)) || (dictionary.get(f).charAt((index+2)%5) != startWord.charAt((index+2)%5)) || (dictionary.get(f).charAt((index+4)%5) != startWord.charAt((index+4)%5)) || (dictionary.get(f).charAt((index+5)%5) != startWord.charAt((index+5)%5))) 
        		{
        			continue;
        		}
        		if(makeLadder(dictionary.get(f),endWord, (index + 2) % 5))
        		{
        			return true;
        		}
        	}
        	if(dictionary.get(f).charAt((index + 4) % 5) == endWord.charAt((index + 4) % 5))
        	{
        		if((dictionary.get(f).charAt((index+1)%5) != startWord.charAt((index+1)%5)) || (dictionary.get(f).charAt((index+2)%5) != startWord.charAt((index+2)%5)) || (dictionary.get(f).charAt((index+3)%5) != startWord.charAt((index+3)%5)) || (dictionary.get(f).charAt((index+5)%5) != startWord.charAt((index+5)%5))) 
        		{
        			continue;
        		}
        		if(makeLadder(dictionary.get(f),endWord, (index + 3) % 5))			//repeat above for index+4
        		{
        			return true;
        		}
        	}
        }
        for(int f = 0; f < dictionary.size(); f++)		//since we have nothing better to do, maybe going sideways will get us forward
        {
        	if(checkedList.contains(dictionary.get(f)))
    		{
    			continue;
    		}
        		if((dictionary.get(f).charAt((index+2)%5) == startWord.charAt((index+2)%5)) && (dictionary.get(f).charAt((index+5)%5) == startWord.charAt((index+5)%5)) && (dictionary.get(f).charAt((index+3)%5) == startWord.charAt((index+3)%5)) && (dictionary.get(f).charAt((index+4)%5) == startWord.charAt((index+4)%5))) 
        		{
        			if(makeLadder(dictionary.get(f),endWord, (index) % 5))
            		{															//just see if there is another word that we haven't already checked that is in the dictionary that is one letter off of the current word in the index + 1 location
            			return true;
            		}
        		}
        		

        		if((dictionary.get(f).charAt((index+1)%5) == startWord.charAt((index+1)%5)) && (dictionary.get(f).charAt((index+5)%5) == startWord.charAt((index+5)%5)) && (dictionary.get(f).charAt((index+3)%5) == startWord.charAt((index+3)%5)) && (dictionary.get(f).charAt((index+4)%5) == startWord.charAt((index+4)%5))) 
        		{
        			if(makeLadder(dictionary.get(f),endWord, (index + 1) % 5))
            		{																	//repeat for index+2
            			return true;
            		}
        		}       		
        		
        	
     
        		if((dictionary.get(f).charAt((index+1)%5) == startWord.charAt((index+1)%5)) && (dictionary.get(f).charAt((index+2)%5) == startWord.charAt((index+2)%5)) && (dictionary.get(f).charAt((index+4)%5) == startWord.charAt((index+4)%5)) && (dictionary.get(f).charAt((index+5)%5) == startWord.charAt((index+5)%5))) 
        		{
        			if(makeLadder(dictionary.get(f),endWord, (index + 2) % 5))
            		{
            			return true;												//repeat for index+3
            		}
        		}
        		if((dictionary.get(f).charAt((index+1)%5) == startWord.charAt((index+1)%5)) && (dictionary.get(f).charAt((index+2)%5) == startWord.charAt((index+2)%5)) && (dictionary.get(f).charAt((index+3)%5) == startWord.charAt((index+3)%5)) && (dictionary.get(f).charAt((index+5)%5) == startWord.charAt((index+5)%5))) 
        		{
        			if(makeLadder(dictionary.get(f),endWord, (index + 3) % 5))
            		{																			//repeat for index+4
            			return true;
            		}
        		}	
        }
        solutionList.remove(startWord);
        return false;								//this was an unsuccessful attempt to find a word ladder
    }

    @Override
    public boolean validateResult(String startWord, String endWord, List<String> wordLadder) 
    {
    	for(int i = 0; i < wordLadder.size() - 1; i++){
    		int differenceCount = 0;
    		String prevWord = wordLadder.get(i);						//make sure that only one letter is different between each rung
    		String nextWord = wordLadder.get(i + 1);
    		for(int j = 0; j < 5; j++){
    			if(!(prevWord.charAt(j) == nextWord.charAt(j))){
    				differenceCount++;
    			}
    			if(differenceCount > 1){
    				return false;
    			}
    		}
    	}
    	return true;
    }

    private ArrayList<String> addSort(String toAdd, ArrayList<String> list)
    {
    	for(int i = 0; i < list.size(); i++)
    	{
    		if(list.get(i).compareTo(toAdd) >= 0)
    		{										//sort by Unicode values before adding each element
    			list.add(i,toAdd);
    			return list;
    		}
    	}
    	list.add(toAdd);
    	return list;
    }
    // add additional methods here
}
