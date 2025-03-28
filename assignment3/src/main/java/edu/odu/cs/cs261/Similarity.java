package edu.odu.cs.cs261;

import java.util.HashSet;
import java.util.Set;


public class Similarity {

    private Document doc1;
    private Document doc2;
    private Set<String> indexTerms;
    
    public Similarity(Document doc1, Document doc2) {
        this.doc1 = doc1;
        this.doc2 = doc2;
        indexTerms = null;
    }
    
    /**
     * The index terms for a similarity measure is the set of all words
     * that occur t or more times in one or both documents, where t is 
     * the threshold supplied in an earlier getSimilarity call.
     * 
     * If no such call has occurred, a value of t=1 is used.
     * 
     * @return collection of sufficiently common words in the two documents 
     */
    public Set<String> getIndexTerms()
    {
        if (indexTerms == null)
            computeIndexTerms(1);
        return indexTerms;
    }

    /**
     * Compute the set of index terms: the collection of words that occur
     * threshold or more times in one or both documents.
     * 
     * @param threshold
     */
    private void computeIndexTerms(int threshold) {
        // TODO
    }

    /**
     * Computes the similarity between two documents. The similarity is computed by
     *    1) Ignoring all words that occur less than threshold times in both documents
     *    2) Counting the number of words occurring at least threshold times in both documents
     *    3) Dividing that count by the number of the number of words occurring at least threshold
     *         times in either document
     * For example, if document 1 is ("A penny saved is a penny earned.") and document 2 is 
     * "What is that? It is a penny.", the similarity at threshold 2 would be 0.0 (the index terms are
     *  "a", "penny", and "is".  At threshold 1, the similarity is 3/8 = 0.375.
     *   
     * @param threshold
     * @return similarity measure, a number between 0.0 and 1.0, or 0.0 if neither document
     *     contains any words occurring more than threshold times.
     */
    public double getSimilarity(int threshold) {
        computeIndexTerms(threshold);
        int matchCount = 0;
        int totalCount = indexTerms.size();
        
        for (String word: indexTerms) {
            int c1 = doc1.getWordCount(word);
            int c2 = doc2.getWordCount(word);
            if (c1 >= threshold && c2 >= threshold) {
                ++matchCount;
            }
        }
        if (totalCount > 0)
            return ((double)matchCount) / ((double)totalCount);
        else
            return 0.0;
    }

}
