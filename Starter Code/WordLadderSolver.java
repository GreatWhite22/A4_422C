/*
    ADD YOUR HEADER HERE
 */

package assignment4;

import java.util.List;

// do not change class name or interface it implements
public class WordLadderSolver implements Assignment4Interface
{
    // delcare class members here.

    // add a constructor for this object. HINT: it would be a good idea to set up the dictionary there

    // do not change signature of the method implemented from the interface
    @Override
    public List<String> computeLadder(String startWord, String endWord) throws NoSuchLadderException 
    {
        // implement this method
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public boolean validateResult(String startWord, String endWord, List<String> wordLadder) 
    {
    	if(!(startWord.equals(endWord))){
    		return false;
    	}
    	for(int i = 0; i < wordLadder.size() - 1; i++){
    		int differenceCount = 0;
    		String prevWord = wordLadder.get(i);
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

    // add additional methods here
}
