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
			return true;
		}
    	index = (index+1)%5;
    	ArrayList<String> possibleWords = new ArrayList<String>();
        for(int i = 0; i < dictionary.size(); i++)
        {
        	if(dictionary.get(i).charAt(index) == endWord.charAt(index))
        	{
        		if((dictionary.get(i).charAt((index+1)%5) != startWord.charAt((index+1)%5)) || (dictionary.get(i).charAt((index+2)%5) != startWord.charAt((index+2)%5)) || (dictionary.get(i).charAt((index+3)%5) != startWord.charAt((index+3)%5)) || (dictionary.get(i).charAt((index+4)%5) != startWord.charAt((index+4)%5))) 
        		{
        			continue;
        		}
        		if(!(solutionList.contains(dictionary.get(i))))
        		{
        			int differentLetters = 0;
        			for(int j = 0; j < 5; j++)
        			{
        				if(dictionary.get(i).charAt(j) != endWord.charAt(j))
        				{
        					differentLetters++;
        				}
        			}
        			if(differentLetters <= 1)
        			{
        				solutionList.add(dictionary.get(i));
        				return true;
        			}
        			possibleWords = addSort(differentLetters + dictionary.get(i), possibleWords);
        		}	
        	}
        }
        checkedList.add(startWord);
        for(int k = 0; k < possibleWords.size(); k++)
        {
        	if(checkedList.contains(possibleWords.get(k)))
    		{
    			continue;
    		}
        	if(makeLadder(possibleWords.get(k).substring(1, 6),endWord,index))
        	{
        		return true;
        	}
        }
        
        for(int f = 0; f < dictionary.size(); f++)
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
        		}
        		if(makeLadder(dictionary.get(f),endWord, (index) % 5))
        		{
        			return true;
        		}
        	}
        	if(dictionary.get(f).charAt((index + 2) % 5) == endWord.charAt((index + 2) % 5))
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
        	if(dictionary.get(f).charAt((index + 3) % 5) == endWord.charAt((index + 3) % 5))
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
        		if(makeLadder(dictionary.get(f),endWord, (index + 3) % 5))
        		{
        			return true;
        		}
        	}
        }
        for(int f = 0; f < dictionary.size(); f++)
        {
        	if(checkedList.contains(dictionary.get(f)))
    		{
    			continue;
    		}
        		if((dictionary.get(f).charAt((index+2)%5) == startWord.charAt((index+2)%5)) && (dictionary.get(f).charAt((index+5)%5) == startWord.charAt((index+5)%5)) && (dictionary.get(f).charAt((index+3)%5) == startWord.charAt((index+3)%5)) && (dictionary.get(f).charAt((index+4)%5) == startWord.charAt((index+4)%5))) 
        		{
        			if(makeLadder(dictionary.get(f),endWord, (index) % 5))
            		{
            			return true;
            		}
        		}
        		

        		if((dictionary.get(f).charAt((index+1)%5) == startWord.charAt((index+1)%5)) && (dictionary.get(f).charAt((index+5)%5) == startWord.charAt((index+5)%5)) && (dictionary.get(f).charAt((index+3)%5) == startWord.charAt((index+3)%5)) && (dictionary.get(f).charAt((index+4)%5) == startWord.charAt((index+4)%5))) 
        		{
        			if(makeLadder(dictionary.get(f),endWord, (index + 1) % 5))
            		{
            			return true;
            		}
        		}       		
        		
        	
     
        		if((dictionary.get(f).charAt((index+1)%5) == startWord.charAt((index+1)%5)) && (dictionary.get(f).charAt((index+2)%5) == startWord.charAt((index+2)%5)) && (dictionary.get(f).charAt((index+4)%5) == startWord.charAt((index+4)%5)) && (dictionary.get(f).charAt((index+5)%5) == startWord.charAt((index+5)%5))) 
        		{
        			if(makeLadder(dictionary.get(f),endWord, (index + 2) % 5))
            		{
            			return true;
            		}
        		}
        		if((dictionary.get(f).charAt((index+1)%5) == startWord.charAt((index+1)%5)) && (dictionary.get(f).charAt((index+2)%5) == startWord.charAt((index+2)%5)) && (dictionary.get(f).charAt((index+3)%5) == startWord.charAt((index+3)%5)) && (dictionary.get(f).charAt((index+5)%5) == startWord.charAt((index+5)%5))) 
        		{
        			if(makeLadder(dictionary.get(f),endWord, (index + 3) % 5))
            		{
            			return true;
            		}
        		}	
        }
        solutionList.remove(startWord);
        return false;
    }

    @Override
    public boolean validateResult(String startWord, String endWord, List<String> wordLadder) 
    {
    	if(!(startWord.equals(endWord))){
    		return false;
    	}
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
