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

import edu.odu.cs.cs330.items.Item;
import edu.odu.cs.cs330.items.ItemFactory;
import edu.odu.cs.cs330.util.Pair;

@SuppressWarnings({
    "PMD.LawOfDemeter",
    "PMD.LongVariable",
    "PMD.UseVarargs",
    "PMD.ClassNamingConventions"
})
public final class ItemParser
{
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
                    return ItemFactory.isKnown(keyword);
                }
            )
            .filter(
                (Pair<String, String[]> tokenPair) -> {
                    final String keyword = tokenPair.first;
                    final int numTokensNeeded = ItemFactory.getNumberOfRequiredValues(keyword);
                    final int numTokensAfterName = tokenPair.second.length;

                    return numTokensAfterName == numTokensNeeded;
                }
            )
            .map(
                (Pair<String, String[]> tokenPair) -> {
                    final String keyword = tokenPair.first;
                    final String tokens[] = tokenPair.second;

                    return ItemFactory.createItemFromTokens(keyword, tokens);
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
    @SuppressWarnings(
        "PMD.CloseResource"
    )
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
