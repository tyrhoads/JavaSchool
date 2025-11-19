package edu.odu.cs.cs330.items;

/**
 * Item represents an individual Item in an inventory.
 * This includes items such as potions, building materials, and food.
 *
 * Only one of each item can exist--i.e., no two items share the
 * same numeric id.
 */
@SuppressWarnings({
    "PMD.CloneThrowsCloneNotSupportedException",
    "PMD.ShortClassName"
})
public interface Item extends Cloneable {
    /**
     * Retrieve name.
     *
     * @return current name
     */
    String getName();

    /**
     * Update name.
     *
     * @param nme replacement name
     */
    void setName(final String nme);

    /**
     * Can this item be placed in a stack?
     *
     * @return true if this item can be part of a stacks larger than 1
     */
    default boolean isStackable()
    {
        return false;
    }

    /**
     * Include clone to deal with... Java's Cloneable interface shenanigans
     */
    Item clone();
}


