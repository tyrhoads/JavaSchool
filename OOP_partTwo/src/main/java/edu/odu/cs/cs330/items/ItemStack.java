package edu.odu.cs.cs330.items;

/**
 * A Homogeneous--i.e., uniform--stack of Items.
 */
@SuppressWarnings({
    "PMD.CloneThrowsCloneNotSupportedException",
    "PMD.ProperCloneImplementation",
    "PMD.OnlyOneReturn",
    "PMD.LawOfDemeter",
    "PMD.BeanMembersShouldSerialize",
    "PMD.NullAssignment",
})
public class ItemStack implements Cloneable {
    /**
     * The specific type of item out of which this stack is built.
     */
    private Item item;

    /**
     * Represents the number of items in this stack.
     */
    private int quantity;

    /**
     * Create an empty stack composed of Air.
     */
    private ItemStack()
    {
        this.item     = null;
        this.quantity = 0;
    }

    /**
     * Create a stack of the desired type.
     *
     * @param base Item out of which the stack is composed
     */
    public ItemStack(final Item base)
    {
        this(base, 1);
    }

    /**
     * Create a stack of the desired type.
     *
     * @param base Item out of which the stack is composed
     * @param count number of items in this stack
     */
    public ItemStack(final Item base, final int count)
    {
        this.item     = base.clone();
        this.quantity = count;
    }

    /**
     * Retrieve the Item out of which the stack is composed.
     *
     * @return the item that serves as the base
     */
    public Item getItem()
    {
        return this.item;
    }

    /**
     * Retrieve the size of the stack.
     *
     * @return the current number of items
     */
    public int size()
    {
        return this.quantity;
    }

    /**
     * Add items if stacking is permitted otherwise, silently discard items.
     *
     * @param qty number of items to add
     */
    public void addItems(final int qty)
    {
        if (!this.permitsStacking()) {
            return;
        }

        this.quantity += qty;
    }

    /**
     * Does the Item contained in this stack permit stacking?
     * <p>
     * This can be less formally phrased, is this a stackable ItemStack?
     *
     * @return true if the addition of items is permitted
     */
    public boolean permitsStacking()
    {
        return item.isStackable();
    }

    /**
     * Consider two stacks to be the same if
     * they contain the same type of Item.
     */
    @Override
    public boolean equals(final Object rhs)
    {
        if (!(rhs instanceof ItemStack)) {
            return false;
        }

        final ItemStack rhsStack = (ItemStack) rhs;

        return this.item.equals(rhsStack.item);
    }

    /**
     * Generate a hash code based on item.
     */
    @Override
    public int hashCode()
    {
        return this.item.hashCode();
    }

    /**
     * Create a deep copy of this ItemStack.
     */
    @Override
    public ItemStack clone()
    {
        return new ItemStack(this.item, this.quantity);
    }

    /**
     * Print the ItemStack directly.
     */
    @Override
    public String toString()
    {
        if (!this.permitsStacking()) {
            return this.item.toString();
        }

        return String.join(
            "",
            this.item.toString(),
            String.format("  Qty: %d%n", this.quantity)
        );
    }
}
