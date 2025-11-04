package edu.odu.cs.cs330.items;

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
    }

    /**
     * Retrieve name.
     *
     * @return current name
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
     * Can this item be placed in a stack?
     *
     * @return true if this item can be part of a stacks larger than 1
     */
    public abstract boolean isStackable();

    /**
     * Duplicate this item.
     */
    @Override
    public abstract Item clone();

    /**
     * Specify the number of values required to initialize this item.
     *
     * @return number of required values
     */
    public abstract int requiredNumberOfValues();

    /**
     * Initialize this item from a collection of Strings.
     *
     * @param tokens string values to use
     */
    public abstract void fromTokens(String[] tokens);
}


