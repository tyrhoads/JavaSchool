package edu.odu.cs.cs330.items;

import java.util.Map;
import java.util.HashMap;

/**
 * This class handles all Item creation and lookup logic.
 */
@SuppressWarnings({
    "PMD.ClassNamingConventions",
    "PMD.DoubleBraceInitialization",
    "PMD.OnlyOneReturn",
    "PMD.LawOfDemeter"
})
public final class ItemFactory {
    /**
     * ItemFactory is a collection of static functions. There is no reason to
     * instatiate an ItemFactory object.
     */
    private ItemFactory()
    {
        // do not allow ItemFactory to be instantiated.
    }

    /**
     * This Lookup table contains a listing of all known keywords and the Item
     * sub-classes to which the correspond.
     */
    private static final Map<String, Item> KNOWN_ITEMS = new HashMap<>() {{
        put("Armour", new Armour());
        put("Armor", new Armour());
        put("Tool", new Tool());
        put("Food", new Consumable());
        put("Potion", new Consumable());
        put("Disposable", new Consumable());
    }};

    /**
     * Create an Item.
     *
     * @param type the item to be created
     *
     * @return An item of the specified type, or null if the type is unknown
     */
    public static Item createItem(final String type)
    {
        if (isNotKnown(type)) {
            return null;
        }

        return KNOWN_ITEMS.get(type).clone();
    }

    /**
     * Create an Item.
     *
     * @param type the item to be created
     *
     * @return An item of the specified type, or null if the type is unknown
     */
    public static Item createItemFromTokens(final String type, final String... tokens)
    {
        final Item item = createItem(type);
        item.fromTokens(tokens);

        return item;
    }

    /**
     * Determine whether a given item is known.
     *
     * @param type the item for which to query
     *
     * @return true if the type can be created and false otherwise
     */
    public static boolean isKnown(final String type)
    {
        return KNOWN_ITEMS.containsKey(type);
    }

    /**
     * Determine whether a given item is **not** known.
     *
     * @param type the item for which to query
     *
     * @return true if the type can be created and false otherwise
     */
    public static boolean isNotKnown(final String type)
    {
        return !KNOWN_ITEMS.containsKey(type);
    }

    /**
     * Determine how many "tokens" are required to create a given item.
     *
     * @param type the item for which to query
     *
     * @return number of required tokens if the type is known and -1 otherwise
     */
    public static int getNumberOfRequiredValues(final String type)
    {
        if (isNotKnown(type)) {
            return -1;
        }

        return KNOWN_ITEMS.get(type).requiredNumberOfValues();
    }
}



