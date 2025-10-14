package edu.odu.cs.cs330.items;

import java.util.Scanner;
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
     * Create the appropriate Item class--e.g., Tool, Armour or Consumable.
     * <p>
     * How is **inheritance** used?
     *
     * @param scanner input from which to read in the Item
     *
     * @return an initialized Item object, or null
     */
    public static Item parseItemLine(final String line)
    {
        final Scanner scanner = new Scanner(line);
        final String keyword = scanner.next();

        if (!isKnown(keyword)) {
            return null;
        }

        final Item item = createItem(keyword);
        item.read(scanner);

        return item;
    }

}



