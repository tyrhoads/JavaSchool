package edu.odu.cs.cs330;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.function.Consumer;

import edu.odu.cs.cs330.items.io.ItemParser;
import edu.odu.cs.cs330.items.Item;
import edu.odu.cs.cs330.items.Inventory;

import static edu.odu.cs.cs330.items.Inventory.DEFAULT_SIZE;

/**
 * 1 - Does this piece of code perform the operations
 *     it was designed to perform?
 *
 * 2 - Does this piece of code do something it was not
 *     designed to perform?
 *
 * 1 Test per mutator
 *
 * This is technically an Integration Test.
 */
@SuppressWarnings({
    "PMD.AtLeastOneConstructor",
    "PMD.AvoidDuplicateLiterals",
    "PMD.BeanMembersShouldSerialize",
    "PMD.JUnitAssertionsShouldIncludeMessage",
    "PMD.JUnitTestContainsTooManyAsserts",
    "PMD.LocalVariableCouldBeFinal",
    "PMD.MethodArgumentCouldBeFinal",
    "PMD.LawOfDemeter"
})
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestStorage
{
    @ParameterizedTest(name = "{index} => size=''{0}''")
    @ValueSource(strings = {"11", "12", "1", "10", "7", "5", "100"})
    public void testParseValid(String intAsString)
    {
        assertThat(
            Storage.getInventorySize("file.txt", intAsString),
            equalTo(Integer.parseInt(intAsString))
        );
    }

    @Test
    public void testParseInvalidTooFewArgs()
    {
        assertThat(Storage.getInventorySize("file.txt"), equalTo(DEFAULT_SIZE));
    }

    @Test
    public void testParseInvalidNotAnInt()
    {
        assertThat(
            Storage.getInventorySize("file.txt", "The Number Ten"),
            equalTo(DEFAULT_SIZE)
        );
    }

    @ParameterizedTest(name = "{index} => size=''{0}''")
    @ValueSource(strings = {"0", "-1", "-100", "-10", "-5", "-32"})
    public void testParseInvalidZeroOrLess(String intAsString)
    {
        assertThat(Storage.getInventorySize("file.txt", intAsString), equalTo(DEFAULT_SIZE));
    }

    @SuppressWarnings({
        "PMD.AvoidFinalLocalVariable",
        "PMD.EmptyCatchBlock",
    })
    @ParameterizedTest(name = "{index} => size=''{0}''")
    @ValueSource(ints = {1, 2, 4, 8, 9})
    public void testAllOutput(int size)
        throws IOException
    {
        //----------------------------------------------------------------------
        // Build the Inventory
        //----------------------------------------------------------------------
        final String inputFilename = "src/main/resources/items-01.txt";
        List<Item> itemsToStore = ItemParser.readItemsFromFile(inputFilename);

        final Inventory.Builder.Result buildResult = new Inventory.Builder()
            .withCapacity(size)
            .withItems(itemsToStore)
            .build();

        //----------------------------------------------------------------------
        // Generate the final summary report
        //----------------------------------------------------------------------
        final String actualOutput = gatherBufferedOutput(
            buffer -> {
                try {
                    Storage.writeSummary(buildResult, buffer);
                    buffer.close();
                }
                catch (IOException _exc) {
                    // Do nothing
                }
            }
        );

        //----------------------------------------------------------------------
        // Read in reference output and compare
        //----------------------------------------------------------------------
        final String referenceOutput = readFileIntoString(
            String.format("src/test/resources/reference-output-01-size-%d.txt", size)
        );

        assertThat(actualOutput, is(equalTo(referenceOutput)));
    }

    /**
     * Collect the results of a output written to a BufferedWriter into a String.
     */
    public String gatherBufferedOutput(Consumer<BufferedWriter> wrappedFunction)
    {
        StringWriter outputBuffer = new StringWriter();

        wrappedFunction.accept(new BufferedWriter(outputBuffer));

        return outputBuffer.toString();
    }

    /**
     * Read a text file into a string. Combiner lines using '\n' or '\r\n'
     * depending on OS.
     */
    public String readFileIntoString(String filename)
        throws FileNotFoundException, IOException
    {
        return Files.newBufferedReader(Paths.get(filename))
            .lines()
            .collect(
                Collectors.joining(
                    System.lineSeparator(),
                    "",
                    System.lineSeparator()
                )
            );
    }
}

