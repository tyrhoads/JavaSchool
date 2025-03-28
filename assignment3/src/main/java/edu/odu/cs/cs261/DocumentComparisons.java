package edu.odu.cs.cs261;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 */

/**
 * @author zeil
 *
 */
public class DocumentComparisons {

    /**
     * Takes a list of document names on the command line. The first document so named
     * is compared against the others, and the closest matching document (with its similarity score)
     * is printed.
     * 
     * @param args
     */
    public static void main(String[] args) {
        ArrayList<Document> allDocs = new ArrayList<Document>();
        for (int i = 0; i < args.length; ++i) {
            File docName = new File(args[i]);
            String fullDocText = readDoc(docName);
            allDocs.add(new Document(args[i], fullDocText));
        }
        new DocumentComparisons().selectBestMatch (allDocs);
    }

    private static String readDoc(File docName) {
        StringBuffer buf = new StringBuffer();
        try (BufferedReader fileIn = new BufferedReader(new FileReader(docName))) {
            String line;
            while ((line = fileIn.readLine()) != null) {
                buf.append (line);
                buf.append("\n");
            }
        } catch (FileNotFoundException ex) {
            //
        }
        catch (IOException ex) {
            //
        }
        return buf.toString();
    }

    static final int Threshold = 4;
    
    private void selectBestMatch(ArrayList<Document> allDocs) {
        double bestScore = -1.0;
        String bestMatchingDoc = "no document found";
        for (int i = 1; i < allDocs.size(); ++i) {
            Similarity dComp = new Similarity(allDocs.get(0), allDocs.get(i));
            double score = dComp.getSimilarity(Threshold);
            if (score > bestScore) {
                bestScore = score;
                bestMatchingDoc = allDocs.get(i).getDocumentName();
            }
        }
        System.out.print ("Best match is " + bestMatchingDoc + " with a similarity of ");
        System.out.format("%.2f%n", bestScore);
    }

}
