package edu.odu.cs.cs330.items.io;

import java.io.BufferedReader;
import java.io.StringReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.odu.cs.cs330.items.Item;
import edu.odu.cs.cs330.items.creation.ItemFactory;

import edu.odu.cs.cs330.util.Pair;

@SuppressWarnings({
    "PMD.LawOfDemeter",
    "PMD.LongVariable",
    "PMD.UseVarargs",
    "PMD.ClassNamingConventions"
})
public final class ItemParser
{
    private static Logger logger = LoggerFactory.getLogger(ItemParser.class);

    private ItemParser()
    {
    }

    /**
     * Read an input stream and generate a collection of Items.
     *
     * @param ins source from which to read Items
     *
     * @return initialized list of Items
     *
     * @throws IOException if an input error occurs
     */
    public static List<Item> readItems(final BufferedReader ins)
        throws IOException
    {
        return ItemParser.streamItems(ins)
            .collect(java.util.stream.Collectors.toList());
    }

    /**
     * Read an input stream and transform it into a stream of Shapes.
     *
     * @param ins source from which to read Items
     *
     * @return initialized list of Items
     *
     * @throws IOException if an input error occurs
     */
    public static Stream<Item> streamItems(final BufferedReader ins)
        throws IOException
    {
        return ins
            .lines()
            .map(line -> line.strip().split(" "))
            .map(
                (String[] tokens) -> {
                    final String keyword = tokens[0].strip();
                    final String remainingTokens[] = Arrays.stream(tokens)
                        .skip(1)
                        .toArray(String[]::new);

                    return new Pair<String, String[]>(keyword, remainingTokens);
                }
            )
            .filter(
                (Pair<String, String[]> tokenPair) -> {
                    final String keyword = tokenPair.first;


                    if (!ItemFactory.isKnown(keyword)) {
                        logger.warn("Skipping unknown item type '{}'", keyword);
                        return false;
                    }
                    return true;
                }
            )
            .filter(
                (Pair<String, String[]> tokenPair) -> {
                    final String keyword = tokenPair.first;
                    final int numTokensNeeded = ItemFactory.getNumberOfRequiredValues(keyword);
                    final int numTokensAfterName = tokenPair.second.length;

                    if (numTokensAfterName != numTokensNeeded) {
                        logger.warn("Skipping '{}'... {} tokens required, but {} were found", keyword, numTokensNeeded, numTokensAfterName);
                        return false;
                    }

                    return true;
                }
            )
            .map(
                (Pair<String, String[]> tokenPair) -> {
                    final String keyword = tokenPair.first;
                    final String tokens[] = tokenPair.second;

                    return ItemFactory.create(keyword).fromTokens(tokens);
                }
            )
            .filter(Objects::nonNull);
    }

    /**
     * Take a string and transform it into a stream of Shapes.
     *
     * @param ins source from which to read Items
     *
     * @return initialized list of Items
     *
     * @throws IOException if an input error occurs
     */
    @SuppressWarnings({
        "PMD.CloseResource"
    })
    public static Stream<Item> streamItems(final String str)
        throws IOException
    {
        final BufferedReader buffer = new BufferedReader(new StringReader(str));

        return ItemParser.streamItems(buffer);
    }

    /**
     * Read an input stream and generate a collection of Items.
     *
     * @param filename source from which to read Items
     *
     * @return initialized list of Items
     *
     * @throws IOException if an input error occurs
     */
    public static List<Item> readItemsFromFile(final String filename)
        throws IOException
    {
        try (BufferedReader buffer = Files.newBufferedReader(Paths.get(filename)) ) {
            return ItemParser.readItems(buffer);
        }
    }
}
