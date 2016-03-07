package assignment4;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.Before;
import org.junit.Test;


public class WordLadderTestSuite {
	List<String> wordList = new ArrayList<String>();
	Assignment4Interface jUnitTest = null;
	String startWord = "";
	String endWord = "";
	
	@Before
	public void setUp() {
		jUnitTest = new WordLadderSolver("A4words.dat");
	}

	@Test
	public void testValidateResultGood() throws NoSuchLadderException {
		wordList.clear();
		wordList.add("fonts");
		wordList.add("fonds");
		wordList.add("foids");
		wordList.add("foins");
		wordList.add("fains");
		wordList.add("faint");
		assertTrue(jUnitTest.validateResult(wordList.get(0), wordList.get(wordList.size() - 1), wordList));
	}
	
	@Test
	public void testValidateResultBad() throws NoSuchLadderException{
		wordList.clear();
		wordList.add("plays");
		wordList.add("plans");
		wordList.add("plane");
		assertFalse(jUnitTest.validateResult(wordList.get(0), wordList.get(wordList.size() - 1), wordList));
	}
	
	@Test
	public void testComputeLadderGood1() throws NoSuchLadderException {
		startWord = "fonts";
		endWord = "faint";
		wordList = jUnitTest.computeLadder(startWord, endWord);
		assertTrue(jUnitTest.validateResult(startWord, endWord, wordList));		
	}
	
	@Test
	public void testComputeLadderGood2() throws NoSuchLadderException {
		startWord = "faint";
		endWord = "fonts";
		wordList = jUnitTest.computeLadder(startWord, endWord);
		assertTrue(jUnitTest.validateResult(startWord, endWord, wordList));
	}
	
	@Test
	public void testComputeLadderBad1() throws NoSuchLadderException{
		startWord = "smile";
		endWord = "okay";
		wordList = jUnitTest.computeLadder(startWord, endWord);
		assertFalse(jUnitTest.validateResult(startWord, endWord, wordList));
	}
	
	@Test
	public void testComputeLadderBad2() throws NoSuchLadderException{
		startWord = "atlas";
		endWord = "zebra";
		wordList = jUnitTest.computeLadder(startWord, endWord);
		assertFalse(jUnitTest.validateResult(startWord, endWord, wordList));	
	}
}
