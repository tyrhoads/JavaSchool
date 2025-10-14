package edu.odu.cs.cs330.items.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

import edu.odu.cs.cs330.items.Item;
import edu.odu.cs.cs330.items.ItemFactory;

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
        return ins
            .lines()
            .map((String line) -> ItemFactory.parseItemLine(line))
            .filter(Objects::nonNull) // remove all nulls
            .collect(java.util.stream.Collectors.toList());
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
