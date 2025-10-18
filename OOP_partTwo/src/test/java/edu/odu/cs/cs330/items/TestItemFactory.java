package edu.odu.cs.cs330.items;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.hamcrest.core.IsNull;

import java.io.StringReader;
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
public class TestItemFactory
{
    Consumable foodItem;

    @BeforeEach
    public void setUp()
    {
        foodItem = new Consumable();
        foodItem.setName("Green-Tea");
        foodItem.setEffect("Wake-Up");
        foodItem.setNumberOfUses(5);
    }

    @Test
    public void testCreateItem()
    {
        // Test a known valid type keyword
        Item item = ItemFactory.createItem("Food");

        assertThat(item, not(nullValue()));
        assertThat(item, instanceOf(Consumable.class));

        // Test an known invalid Item type keyword
        item = ItemFactory.createItem("This Is Not a valid Item Type");
        assertThat(item, is(nullValue()));

    }

    @Test
    public void testIsKnown()
    {
        assertTrue(ItemFactory.isKnown("Food"));
        assertTrue(ItemFactory.isKnown("Armor"));
        assertFalse(ItemFactory.isKnown("PHP is an okay language. FALSE!"));
    }

    @Test
    public void testParseItemLineSuccess()
    {
        String inputStr = "Food Green-Tea Wake-Up 5";

        Item item = ItemFactory.parseItemLine(inputStr);

        assertThat(item, equalTo(foodItem));
        assertThat(item.toString(), equalTo(foodItem.toString()));
    }

    @Test
    public void testParseItemLineFailure()
    {
        String inputStr = "NOTACTUALLY-Food Green-Tea Wake-Up 5";

        Item item = ItemFactory.parseItemLine(inputStr);
        assertThat(item, is(nullValue()));
    }
}

