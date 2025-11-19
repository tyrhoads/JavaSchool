package edu.odu.cs.cs330.items;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

import edu.odu.cs.cs330.items.creation.Event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * An Inventory is composed of n slots. Each slot may store only
 * one type of item--specified by *slots*.
 * <p>
 * Once all slots are filled, no additional Item types may be
 * stored. Individual slots may contain any number of the same
 * Item--if the Item is stackable.
 */
@SuppressWarnings({
    "PMD.BeanMembersShouldSerialize",
    "PMD.ClassWithOnlyPrivateConstructorsShouldBeFinal",
    "PMD.LawOfDemeter",
    "PMD.OnlyOneReturn",
})
public class Inventory implements Iterable<ItemStack>
{
    /**
     * This is the Default Inventory size.
     */
    public static final int DEFAULT_SIZE = 10;

    /**
     * Individual item slots--each ItemStack occupies one slot.
     */
    private final List<ItemStack> slots;

    /**
     * Total number of distinct Item types that can be stored.
     */
    private final int capacity;

    /**
     * Create an inventory with n slots.
     *
     * @param desiredCapacity size of the new Inventory
     */
    private Inventory(final Builder builder)
    {
        this.slots    = builder.items;
        this.capacity = builder.capacity;
    }

    /**
     * Get the total number of slots (inventory size).
     *
     * @return maximum size
     */
    public int totalSlots()
    {
        return this.capacity;
    }

    /**
     * Get the number of currently filled (used) slots.
     *
     * @return number of slots in use
     */
    public int utilizedSlots()
    {
        return this.slots.size();
    }

    /**
     * Get the number of empty (unused) slots.
     *
     * @return number of available slots
     */
    public int emptySlots()
    {
        return this.totalSlots() - this.utilizedSlots();
    }

    /**
     * Determine if all slots are in use.
     *
     * @return true if all slots contain an ItemStack and false otherwise
     */
    public boolean isFull()
    {
        return this.emptySlots() == 0;
    }

    /**
     * Return the percent filled rounded to the nearest whole number (integer).
     */
    public int percentFilled()
    {
        return (int) Math.round(100.0 * this.utilizedSlots() / capacity);
    }

    @Override
    public Iterator<ItemStack> iterator()
    {
        return this.slots.iterator();
    }

    private String usageSummary()
    {
        return String.format(" -Used %3d%% of %d slots", percentFilled(), capacity);
    }

    /**
     * Generate a Summary of the Inventory and all Items contained within.
     */
    @Override
    public String toString()
    {
        return Stream.concat(
            Stream.of(this.usageSummary()),
            this.slots
                .stream()
                .map(Object::toString)
        )
        .collect(
            joining(System.lineSeparator(), "", "")
        );
    }

    public static class Builder
    {
        /**
         * Item stacks to store.
         */
        private final List<ItemStack> items;

        /**
         * Total number of distinct Item types that can be stored.
         */
        private int capacity;

        /**
         * Construct a new builder.
         */
        public Builder()
        {
            this.items = new ArrayList<>();
            this.capacity = 0;
        }

        /**
         * Select the default Inventory size
         */
        public Builder withDefaultCapacity()
        {
            return this.withCapacity(Inventory.DEFAULT_SIZE);
        }

        /**
         * Specify an inventory size.
         *
         * @param size desired size
         */
        public Builder withCapacity(final int size)
        {
            this.capacity = size;
            return this;
        }

        /**
         * Add one item to the inventory list.
         *
         * @param stack new stack of items to add
         *
         * @return true if *stack* was added and false otherwise
         */
        public Builder withItem(final Item oneItem)
        {
            final ItemStack stack = new ItemStack(oneItem);
            return this.withItems(stack);
        }

        /**
         * Add one or more items to the inventory list.
         *
         * @param stack new stack of items to add
         *
         * @return true if *stack* was added and false otherwise
         */
        public Builder withItems(final ItemStack stack)
        {
            Logger logger = LoggerFactory.getLogger(this.getClass());

            final Optional<ItemStack> match = this.items.stream()
                .filter(entry -> stack.equals(entry))
                .findFirst();

            if (match.isPresent()) {
                final ItemStack matchingStack = match.get();
                if (matchingStack.permitsStacking()) {
                    matchingStack.addItems(stack.size());

                    logger.info("({}) {}", Event.MERGED, stack.getItem().getName());
                    return this;
                }
            }

            if (this.items.size() == this.capacity) {
                logger.info("({}) {}", Event.DISCARDED, stack.getItem().getName());
                return this;
            }

            this.items.add(stack);
            logger.info("({}) {}", Event.STORED, stack.getItem().getName());

            return this;
        }

        public Builder withItems(final Iterable<Item> itemsToStore)
        {
            for (final Item item : itemsToStore) {
                this.withItem(item);
            }

            return this;
        }

        public Builder withItemStacks(final Iterable<ItemStack> itemsToStore)
        {
            for (final ItemStack stack : itemsToStore) {
                this.withItems(stack);
            }

            return this;
        }

        public Inventory build()
        {
            Logger logger = LoggerFactory.getLogger(this.getClass());
            logger.info("Constructing Inventory");

            return new Inventory(this);
        }
    }
}
