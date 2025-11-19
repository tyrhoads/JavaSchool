package edu.odu.cs.cs330.items.creation;

import edu.odu.cs.cs330.items.Item;

public interface ItemCreationStrategy
{
    /**
     * Create an item using deafult values.
     */
    Item fromDefaults();

    /**
     * Specify the number of required tokens to create an Item.
     */
    int requiredNumberOfValues();

    /**
     * Create an Item from a given set of tokens,
     */
    Item fromTokens(final String... tokens);

    /**
     * Create an identical copy of an existing Item.
     */
    Item fromExisting(final Item original);
}


