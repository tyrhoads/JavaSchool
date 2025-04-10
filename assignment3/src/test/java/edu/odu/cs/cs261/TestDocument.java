package edu.odu.cs.cs261;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


//import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 * 
 */

/**
 * @author zeil
 *
 */
public class TestDocument {
	
	Document doc1;
	Document doc2;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		doc1 = new Document("doc1", "A fool and his money\nare soon parted.");
		doc2 = new Document("d/oc2", "How much wood could a   \n    WOODCHUCK chuck if a woodchuck would !chuck! 999wood?");
	}

	

	/**
	 * Test method for {@link Document#getDocumentName()}.
	 */
	@Test
	public void testGetDocumentName() {
		assertEquals ("doc1", doc1.getDocumentName());
		assertEquals ("d/oc2", doc2.getDocumentName());	
	}

	/**
	 * Test method for {@link Document#getWordCount(java.lang.String)}.
	 */
	@Test
	public void testGetWordCount() {
		assertEquals (1, doc1.getWordCount("fool"));
		assertEquals (0, doc2.getWordCount("fool"));
		assertEquals (1, doc1.getWordCount("money"));
		assertEquals (0, doc2.getWordCount("money"));
		assertEquals (1, doc1.getWordCount("soon"));
		assertEquals (0, doc2.getWordCount("soon"));
		assertEquals (1, doc1.getWordCount("parted"));
		assertEquals (0, doc2.getWordCount("parted"));
		
		assertEquals (0, doc2.getWordCount("How"));
		assertEquals (1, doc2.getWordCount("how"));
		assertEquals (1, doc2.getWordCount("much"));
		assertEquals (2, doc2.getWordCount("wood"));
		assertEquals (2, doc2.getWordCount("woodchuck"));
		assertEquals (2, doc2.getWordCount("chuck"));
	}

	/**
	 * Test method for {@link Document#getWordCount(java.lang.String)}.
	 */
	@Test
	public void testStopList() {
		assertEquals (0, doc1.getWordCount("A"));
		assertEquals (0, doc1.getWordCount("a"));
		assertEquals (0, doc1.getWordCount("and"));
		assertEquals (0, doc1.getWordCount("are"));
		assertEquals (0, doc2.getWordCount("a"));
		assertEquals (0, doc2.getWordCount("if"));
	}
	/**
	 * Test method for {@link Document#iterator()}.
	 */
	@Test
	public void testIterator() {
		String[] expected = {"how", "much", "wood", "could", "woodchuck", "chuck", "would"};
		Set<String> expectedWords = new HashSet<String>(Arrays.asList(expected));
		Set<String> actual = new HashSet<String>();
		for (String word: doc2) {
			actual.add(word);
		}
		assertEquals (expectedWords, actual);
	}

}
