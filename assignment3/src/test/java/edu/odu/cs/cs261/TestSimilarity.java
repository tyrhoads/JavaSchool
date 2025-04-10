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
public class TestSimilarity {
	
	final String text1 = "A fool and his money\nare soon parted.\n";
	final String text2 = "How much wood could a   \n    WOODCHUCK chuck if a woodchuck would !chuck! 999wood?\n";
	final String text3 = "A penny saved is money in the bank.\n";

	Document doc1;
	Document doc1a;
	Document doc2;
	Document doc3;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		doc1 = new Document("doc1", text1 + text1);
		doc1a = new Document("doc1a", text1 + text1 + text1);
		doc2 = new Document ("doc2", text2);
		doc3 = new Document ("doc3", text3 + text3);
	}

	/**
	 * Test method for {@link Similarity#getIndexTerms()}.
	 */
	@Test
	public void testGetIndexTerms1() {
		Similarity sim = new Similarity(doc1, doc1a);
		String[] expected = {"fool", "money", "soon", "parted"};
		Set<String> expectedWords = new HashSet<String>(Arrays.asList(expected));
		assertEquals (expectedWords, sim.getIndexTerms());
		sim.getSimilarity(2);
		assertEquals (expectedWords, sim.getIndexTerms());
		sim.getSimilarity(3);
		assertEquals (expectedWords, sim.getIndexTerms());
		sim.getSimilarity(4);
		assertEquals (new HashSet<String>(), sim.getIndexTerms());
	}

	/**
	 * Test method for {@link Similarity#getIndexTerms()}.
	 */
	@Test
	public void testGetIndexTerms2() {
		Similarity sim = new Similarity(doc1a, doc2);
		String[] expected = {"fool", "money", "soon", "parted", "how", "much", "wood", "could", "woodchuck", "chuck", "would"};
		Set<String> expectedWords = new HashSet<String>(Arrays.asList(expected));
		String[] expected2 = {"fool", "money", "soon", "parted", "wood", "woodchuck", "chuck"};
		Set<String> expectedWords2 = new HashSet<String>(Arrays.asList(expected2));
		String[] expected3 = {"fool", "money", "soon", "parted"};
		Set<String> expectedWords3 = new HashSet<String>(Arrays.asList(expected3));
		assertEquals (expectedWords, sim.getIndexTerms());
		sim.getSimilarity(2);
		assertEquals (expectedWords2, sim.getIndexTerms());
		sim.getSimilarity(3);
		assertEquals (expectedWords3, sim.getIndexTerms());
		sim.getSimilarity(4);
		assertEquals (new HashSet<String>(), sim.getIndexTerms());
	}

	/**
	 * Test method for {@link Similarity#getSimilarity(int)}.
	 */
	@Test
	public void testGetSimiliarity() {
		assertEquals (1.0, new Similarity(doc1, doc1).getSimilarity(1), 0.001);
		assertEquals (1.0, new Similarity(doc1, doc1).getSimilarity(2), 0.001);
		assertEquals (0.0, new Similarity(doc1, doc1).getSimilarity(3), 0.001);

		assertEquals (1.0, new Similarity(doc1, doc1a).getSimilarity(1), 0.001);
		assertEquals (1.0, new Similarity(doc1, doc1a).getSimilarity(2), 0.001);
		assertEquals (0.0, new Similarity(doc1, doc1a).getSimilarity(3), 0.001);
		
		assertEquals (0.0, new Similarity(doc1, doc2).getSimilarity(1), 0.001);

		assertEquals (1.0/7.0, new Similarity(doc1, doc3).getSimilarity(1), 0.001);
	}

}
