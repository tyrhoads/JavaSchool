package edu.odu.cs.cs330.items;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.hamcrest.core.IsNull;

import java.util.Arrays;
import java.util.List;
import java.util.Iterator;
import java.util.Scanner;


/**
 * 1 - Does this piece of code perform the operations
 *     it was designed to perform?
 *
 * 2 - Does this piece of code do something it was not
 *     designed to perform?
 *
 * 1 Test per mutator
 *
 * This is technically an Integration Test.
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestInventory
{
    private static final Inventory EMPTY_INVENTORY = new Inventory.Builder()
        .withDefaultCapacity()
        .build()
        .second;

    private Item[] testItems;

    @BeforeEach
    public void setUp()
    {
        Armour boots = new Armour();
        boots.read(
            new Scanner("Boots Diamond 100 10 FeatherFalling 4 lightning")
        );

        Consumable tomato = new Consumable();
        tomato.read(
            new Scanner("Tomato Hunger-10 2")
        );


        testItems = new Item[] {
            boots,
            tomato
        };
    }

    @Test
    public void testDefaultConstructor()
    {
        assertThat(EMPTY_INVENTORY.utilizedSlots(), equalTo(0));
        assertThat(EMPTY_INVENTORY.emptySlots(), equalTo(10));
        assertThat(EMPTY_INVENTORY.totalSlots(), equalTo(10));
        assertFalse(EMPTY_INVENTORY.isFull());
    }

    @Test
    public void testConstructorSizeN()
    {
        Inventory invWith8Slots = new Inventory.Builder()
            .withCapacity(8)
            .build()
            .second;

        assertThat(invWith8Slots.utilizedSlots(), equalTo(0));
        assertThat(invWith8Slots.emptySlots(), equalTo(8));
        assertThat(invWith8Slots.totalSlots(), equalTo(8));
        assertFalse(invWith8Slots.isFull());
    }


    /**
     * Add ItemStacks to an Inventory without filling the Inventory or attempting
     * to add duplicate Items
     */
    @Test
    public void testAddItemStackNoCheck()
    {
        List<ItemStack> stacksToAdd = Arrays.asList(
            new ItemStack(testItems[0]),
            new ItemStack(testItems[1])
        );

        Inventory aBag = new Inventory.Builder()
            .withCapacity(4)
            .withItems(stacksToAdd.get(0))
            .withItems(stacksToAdd.get(1))
            .build()
            .second;

        assertFalse(aBag.isFull());
        assertThat(aBag.utilizedSlots(), equalTo(2));
        assertThat(aBag.emptySlots(), equalTo(2));
        assertThat(aBag.totalSlots(), equalTo(4));

        // Retrieve each of the items and check that they were added
        Iterator<ItemStack> it = aBag.iterator();

        assertThat(it.next(), equalTo(stacksToAdd.get(0)));
        assertThat(it.next(), equalTo(stacksToAdd.get(1)));

        // Check that there are no more ItemStacks to retrieve
        assertFalse(it.hasNext());
    }

    /**
     * Add ItemStacks to an Inventory without filling the Inventory, but attempting
     * to add duplicate Items
     */
    @Test
    public void testAddItemWithDuplicateItems()
    {
        List<ItemStack> stacksToAdd = Arrays.asList(
            new ItemStack(testItems[0]),
            new ItemStack(testItems[1]),
            new ItemStack(testItems[1])
        );

        Inventory aBag = new Inventory.Builder()
            .withCapacity(4)
            .withItemStacks(stacksToAdd)
            .build()
            .second;

        assertFalse(aBag.isFull());
        assertThat(aBag.utilizedSlots(), equalTo(2));
        assertThat(aBag.emptySlots(), equalTo(2));
        assertThat(aBag.totalSlots(), equalTo(4));

        // Retrieve each of the items and check that they were added
        Iterator<ItemStack> it = aBag.iterator();

        assertThat(it.next(), equalTo(stacksToAdd.get(0)));

        // Expect the merged stack to be returned.
        ItemStack mergedStack = new ItemStack(testItems[1]);
        mergedStack.addItems(1);

        final ItemStack retrieved = it.next();

        assertThat(retrieved, equalTo(mergedStack));
        assertThat(retrieved.size(), equalTo(2));

        // Check that there are no more ItemStacks to retrieve
        assertThat(it.hasNext(), is(false));
    }

    /**
     * Add ItemStacks to an Inventory and fill it.
     * Then try to add one more ItemStack that is stackable.
     */
    @Test
    public void testAddItemAfterFullWithNonStackable()
    {
        List<ItemStack> stacksToAdd = Arrays.asList(
            new ItemStack(testItems[0]),
            new ItemStack(testItems[1]),
            new ItemStack(testItems[0])
        );

        Inventory aBag = new Inventory.Builder()
            .withCapacity(2)
            .withItems(stacksToAdd.get(0))
            .withItems(stacksToAdd.get(1))
            .withItems(stacksToAdd.get(2))
            .build()
            .second;

        assertThat(aBag.isFull(), is(true));
        assertThat(aBag.utilizedSlots(), equalTo(2));
        assertThat(aBag.emptySlots(), equalTo(0));
        assertThat(aBag.totalSlots(), equalTo(2));

        // Retrieve each of the items and check that they were added
        Iterator<ItemStack> it = aBag.iterator();

        assertThat(it.next(), equalTo(stacksToAdd.get(0)));
        assertThat(it.next(), equalTo(stacksToAdd.get(1)));

        // Check that there are no more ItemStacks to retrieve
        assertThat(it.hasNext(), is(false));
    }

    /**
     * Add ItemStacks to an Inventory and fill it.
     * Then try to add one more ItemStack that is **not** stackable.
     */
    @Test
    public void testAddItemAfterFullWithStackable()
    {
        List<ItemStack> stacksToAdd = Arrays.asList(
            new ItemStack(testItems[0]),
            new ItemStack(testItems[1])
        );

        Inventory aBag = new Inventory.Builder()
            .withCapacity(2)
            .withItems(stacksToAdd.get(0))
            .withItems(stacksToAdd.get(1))
            .withItems(stacksToAdd.get(0))
            .build()
            .second;

        assertThat(aBag.isFull(), is(true));
        assertThat(aBag.utilizedSlots(), equalTo(2));
        assertThat(aBag.emptySlots(), equalTo(0));
        assertThat(aBag.totalSlots(), equalTo(2));

        // Retrieve each of the items and check that they were added
        Iterator<ItemStack> it = aBag.iterator();

        assertThat(it.next(), equalTo(stacksToAdd.get(0)));
        assertThat(it.next(), equalTo(stacksToAdd.get(1)));

        // Check that there are no more ItemStacks to retrieve
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void testToString()
    {
        List<ItemStack> stacksToAdd = Arrays.asList(
            new ItemStack(testItems[0]),
            new ItemStack(testItems[1]),
            new ItemStack(testItems[0])
        );

        Inventory aBag = new Inventory.Builder()
            .withCapacity(4)
            .withItemStacks(stacksToAdd)
            .build()
            .second;

        List<String> itemsAsStrings = stacksToAdd.stream()
            .map(ItemStack::toString)
            .collect(java.util.stream.Collectors.toList());

        String aBagAsStr = aBag.toString();
        assertThat(aBagAsStr, stringContainsInOrder(Arrays.asList("75%", "of", "4", "slots")));
        assertThat(aBagAsStr, stringContainsInOrder(itemsAsStrings));
    }
}

