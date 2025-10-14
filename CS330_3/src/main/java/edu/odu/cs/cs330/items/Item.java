package edu.odu.cs.cs330.items;

import java.util.Scanner;

/**
 * Item represents an individual Item in an inventory.
 * This includes items such as potions, building materials, and food.
 *
 * Only one of each item can exist--i.e., no two items share the
 * same numeric id.
 */
@SuppressWarnings({
    "PMD.AbstractNaming",
    "PMD.ShortClassName",
})
public abstract class Item implements Cloneable {
    /**
     * Short title--e.g., HP Potion.
     */
    protected String name;

    /**
     * Indicates whether this Item can placed in an ItemStack larger than 1.
     */
    protected boolean stackable;


    /**
     * Create an Item with name = Air and stackable = true.
     */
    private Item()
    {
        this("Air");
    }

    /**
     * Create an Item with a specified and name.
     *
     * @param nme desired name
     */
    public Item(final String nme)
    {
        this.name  = nme;
        this.stackable = true;
    }

    /**
     * Create an Item with a specified id and name
     *
     * @param name desired name
     * @param stackable flag that indicates whether duplicates
     *      of this item can be stacked
     *
     * @pre
     *  - all items that share a name are of the same type
     *  - differences in capitalization denote different items
     */
    public Item(String name, boolean stackable)
    {
        this.name      = name;
        this.stackable = stackable;
    }

    /**
     * Retrieve name.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Update name.
     *
     * @param nme replacement name
     */
    public void setName(final String nme)
    {
        this.name = nme;
    }

    /**
     * Check for logical equivalence--based on name.
     */
    @Override
    public boolean equals(Object rhs)
    {
        Item r = (Item) rhs;

        return this.name.equals(r.name);
    }

    /**
     * Generate a hash code based on name.
     */
    @Override
    public int hashCode()
    {
        return this.name.hashCode();
    }

    /**
     * Can this item be placed in a stack?
     *
     * @return true if this item can be part of a stacks larger than 1
     */
    public boolean isStackable()
    {
        return this.stackable;
    }

    /**
     * Read an item from an input buffer.
     *
     * @param snr source from which to read
     */
    public abstract void read(Scanner snr);

    /**
     * Duplicate this item.
     */
    @Override
    public abstract Item clone();

    /**
     * *Print* an Item.
     */
    @Override
    public String toString()
    {
        return String.format(" %s", this.name);
    }
}


