package edu.odu.cs.cs330;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.List;

import edu.odu.cs.cs330.items.Item;
import edu.odu.cs.cs330.items.Inventory;
import edu.odu.cs.cs330.items.Inventory.Builder.Result;
import edu.odu.cs.cs330.items.io.ItemParser;


/**
 * This is the Storage Driver. It contains the main function, supporting
 * functions, and all argument parsing.
 */
@SuppressWarnings({
    "PMD.LawOfDemeter",
    "PMD.LongVariable",
    "PMD.UseVarargs",
    "PMD.SystemPrintln",
    "PMD.DoNotCallSystemExit",
    "PMD.ClassNamingConventions"
})
public final class Storage {
    /**
     * This is a utility class (all static functions). No instances should ever
     * be instantiated.
     */
    private Storage()
    {

    }

    /**
     * Parse the inventory size from the command line arguments. If no size was
     * provided or the provided size is not valid use
     * {@link edu.odu.cs.cs330.items.Inventory#DEFAULT_SIZE}
     *
     * @param args command line arguments
     */
    @SuppressWarnings({
        "PMD.AvoidLiteralsInIfCondition",
        "PMD.DataflowAnomalyAnalysis",
        "PMD.UnusedAssignment",
    })
    public static int getInventorySize(final String... args)
    {
        int size = 0;

        try {
            size = Integer.parseInt(args[1]);
            if (size < 1) {
                size = Inventory.DEFAULT_SIZE;
            }
        }
        catch (IndexOutOfBoundsException | NumberFormatException e) {
            size = Inventory.DEFAULT_SIZE;
        }

        return size;
    }

    /**
     * This is the Item Storage Assignment in Java.
     *
     * @param argv user supplied item filename and (optional) inventory size.
     */
    @SuppressWarnings({
        "PMD.DataflowAnomalyAnalysis",
        "PMD.AvoidLiteralsInIfCondition",
        "PMD.LocalVariableNamingConventions"
    })
    public static void main(final String... argv)
    {
        if (argv.length < 1) {
            System.err.println("Usage: java -jar Storage.jar items-file");
            System.exit(1);
        }

        List<Item> itemsToStore = null;
        try {
            itemsToStore = ItemParser.readItemsFromFile(argv[0]);
        }
        catch (IOException e) {
            System.err.printf("Error: %s could not be opened or read%n", argv[0]);
            System.exit(3);
        }

        final int invSize = getInventorySize(argv);

        final Result buildResult = new Inventory.Builder()
            .withCapacity(invSize)
            .withItems(itemsToStore)
            .build();

        try (BufferedWriter standardOut = new BufferedWriter(new OutputStreamWriter(System.out))) {
            writeSummary(buildResult, standardOut);
            standardOut.flush();
        }
        catch (IOException _exc) {
            System.err.println("A failure occured during output");
        }
    }

    /**
     * Output a summary of which items were stored, discarded, or merged and
     * the final Inventory
     */
    @SuppressWarnings({
        "PMD.AvoidPrefixingMethodParameters",
        "PMD.DataflowAnomalyAnalysis",
    })
    public static void writeSummary(
        final Result buildResult,
        final BufferedWriter outBuffer
    )
        throws IOException
    {
        final String report = String.join(
            System.lineSeparator(),
            "Processing Log:",
            buildResult.eventLog().renderRecords(
                (event, description) -> String.format(" (%s) %s", event.asLetter(), description)
            ),
            "Player Storage Summary:",
            buildResult.object().toString(),
            ""
        );
        outBuffer.write(report);
    }
}
