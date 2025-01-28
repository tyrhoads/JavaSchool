

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author zeil
 *
 */
public class SATScores {

    private String[] names;
    private double[] scores;
    private final int MaxScores = 1000;
    private int numScores;

    private double average;
    private double stdDev;
    private boolean statsHaveBeenComputed;

    public SATScores()
    {
        names = new String[MaxScores];
        scores = new double[MaxScores];
        numScores = 0;
        statsHaveBeenComputed = false;
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 0) {
            System.err.println ("Usage: java SATScores inputFileName");
            System.exit(1);
        }
        BufferedReader input = new BufferedReader (
                new FileReader("test000.dat"));
        SATScores s = new SATScores();
        s.processScores(input);
    }


    public void processScores(BufferedReader input) {
        readScores(input);
        computeStats();
        writeReport();
    }

    /**
     * Add a score to the collection.
     */
    public void addScore(String name, double score) {
        names[numScores] = name;
        scores[numScores] = score;
        ++numScores;
        statsHaveBeenComputed = false;
    }

    /**
     * How many scores have been recorded?
     */
    public int getNumScores() {
        return numScores;
    }

    /**
     * What is the average?
     */
    public double getAverage() {
        computeStats();
        return average;
    }

    /**
     * What is the standard deviation?
     */
    public double getStdDev() {
        computeStats();
        return stdDev;
    }

    /**
     * Computes the cumulative normal function of z, where z is a normalized
     * value. 
     * 
     * @param z
     * @return cumulative normal of z
     */
    public double cumulativeNormal (double z)
    {
        if (z >= 0.0) {
            double sum = 0.0;
            double c = 1.0 / Math.sqrt(2.0 * Math.PI);
            for (int i = 0; i < 1000; ++i) {
                double x = ((double)i) * z / 1000.0;
                double y = Math.exp(-0.5 * x*x);
                sum += y;
            }
            sum /= 1000.0;
            sum *= c * z;
            return 0.5 + sum;
        }
        else
            return 1.0 - cumulativeNormal(-z);
    }
    

    /**
     * Compute the average and standard deviation for the
     * accumulated scores.
     */
    private void computeStats() {
        if (statsHaveBeenComputed)
            return;
        //TODO  Compute the average and standard deviation
       int i = 0;
       while (i < numScores) {
        average += scores[i]; 
        i++;
       }
       average = average/numScores;
       // System.out.println(average);
        stdDev = 10.0 ;
        statsHaveBeenComputed = true;
    }

    /**
     * Read the names and raw scores into the arrays
     * 
     * @param input  input stream from which to read
     */
    private void readScores(BufferedReader input) {
        Scanner scan = new Scanner(input);

        while (scan.hasNext()) {
            StringBuffer name = new StringBuffer();
            while (!scan.hasNext("\\d{3}")) {
                if (name.length() > 0)
                    name.append (' ');
                name.append (scan.next());
            }
            names[numScores] = name.toString();
            double s = scan.nextDouble();
            scores[numScores] = s;

            ++numScores;
        }
        scan.close();
    }

   /**
    * Print the report of normalized scores and percentiles for each student. 
    */
    private void writeReport()
    {
        System.out.printf ("Avg: %6.2f     Std Dev: %6.2f%n", average, stdDev);
        for (int i = 0; i < numScores; ++i) {
            double s = scores[i];
            //TODO Compute the z score and percentile
            double z = 234.242 ;
            double percentile = 10.0 ; 
            String name = names[i];
            printReportLine(name, s, z, percentile);
            System.out.println();
        }
    }

    private void printReportLine(String name, double score, double z, double percentile) {
        if (name.length() > 24)
            name = name.substring(0, 24);
        System.out.printf("%24s %3.0f %7.3f %5.1f%%", name, score, z, percentile);
    }
}