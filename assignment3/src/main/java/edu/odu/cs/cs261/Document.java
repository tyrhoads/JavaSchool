package edu.odu.cs.cs261;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 
 */

/**
 * This document class tracks the words in a document, counting how often they occur,
 * but does not preserve or record the order in which the words occur. Extremely
 * common words (in the stoplist) are ignored and not counted.
 * 
 * @author zeil
 *
 */
public class Document implements Iterable<String> {
    
    private HashMap<String, Integer> wordCounts;
    private String docName;
    
    private static String[] stopListWords = {
        "i",
        "a",
        "an",
        "and",
        "are",
        "as",
        "at",
        "be",
        "by",
        "for",
        "from",
        "he",
        "her",
        "his",
        "if",
        "in",
        "is",
        "it",
        "of",
        "on",
        "or",
        "she",
        "that",
        "the",
        "this",
        "to",
        "was",
        "what",
        "when",
        "where",
        "who",
        "will",
        "with"
    }; 
    
    
    private static HashSet<String> stoplist = new HashSet<String>(Arrays.asList(stopListWords));
    

    public Document(String documentName, String documentText) {
        docName = documentName;
        wordCounts = new HashMap<String, Integer>();
        Scanner scanner = new Scanner(documentText);
        while (scanner.hasNext()) {
            String word = scanner.next(); 
            word = trimWord(word);
            if (word.length() > 0)
                countThisWord (word);
        }
    }
    
    /**
     * If this word is not extremely common, add it to the document.
     * 
     * @param word
     */
    private void countThisWord(String word) {
        // TODO
    }

    // 
    /**
     * Removes any leading and trailing non-alphabetics and converts the
     * remainder to lower case.
     * 
     * @param word
     * @return
     */
    private String trimWord(String word) {
        String trimmed1 = word.replaceAll("^[^A-Za-z]*", "");
        String trimmed2 = trimmed1.replaceAll("[^A-Za-z]*$", "");
        return trimmed2.toLowerCase();
    }

    /**
     * Returns the document name.
     * 
     * @return the document name
     */
    public String getDocumentName() {
        return docName;
    }
    
    /**
     * Returns the number of times this word occurs in
     * the document (0 if the word does not occur in the
     * document at all.)
     * 
     * @param word
     * @return count
     */
    public int getWordCount (String word)
    {
        return 0; // TODO
    }
    
    

    @Override
    /**
     * Provides access to all of the words found in the document.
     * 
     * @return
     */
    public Iterator<String> iterator() {
        return wordCounts.keySet().iterator();
    }
    

}
