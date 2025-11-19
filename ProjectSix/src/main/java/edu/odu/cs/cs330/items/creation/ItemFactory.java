package edu.odu.cs.cs330.items.creation;

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
    private static final Map<String, ItemCreationStrategy> KNOWN_ITEMS = new HashMap<>() {{
        put("Armour", ArmourCreation.construct());
        put("Armor", ArmourCreation.construct());
        put("Tool", ToolCreation.construct());
        put("Food", ConsumableCreation.construct());
        put("Potion", ConsumableCreation.construct());
        put("Disposable", ConsumableCreation.construct());
    }};

    /**
     * Get an item creation strategy
     *
     * @param type the item to be created
     *
     * @return A creation strategy for an item of the specified type, or null
     *     if the type is unknown
     */
    public static ItemCreationStrategy create(final String type)
    {
        if (isNotKnown(type)) {
            return null;
        }

        return KNOWN_ITEMS.get(type);
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



