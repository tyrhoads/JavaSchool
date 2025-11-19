package edu.odu.cs.cs330.items.creation;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import edu.odu.cs.cs330.items.Consumable;


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
@SuppressWarnings({
    "PMD.AtLeastOneConstructor",
    "PMD.BeanMembersShouldSerialize",
    "PMD.JUnitAssertionsShouldIncludeMessage",
    "PMD.JUnitTestContainsTooManyAsserts",
    "PMD.LocalVariableCouldBeFinal",
    "PMD.MethodArgumentCouldBeFinal",
    "PMD.LawOfDemeter",
    "PMD.SingularField"
})
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestItemFactory
{
    private Consumable foodItem;

    @BeforeEach
    public void setUp()
    {
        foodItem = new Consumable();
        foodItem.setName("Green-Tea");
        foodItem.setEffect("Wake-Up");
        foodItem.setNumberOfUses(5);
    }

    @Test
    public void testCreateItemKeywordKnown()
    {
        ItemCreationStrategy creationStrategy = ItemFactory.create("Food");

        assertThat(creationStrategy, not(nullValue()));
        assertThat(creationStrategy, instanceOf(ConsumableCreation.class));
    }

    @Test
    public void testCreateItemKeyworkInvalid()
    {
        ItemCreationStrategy creationStrategy = ItemFactory.create("This Is Not a valid Item Type");
        assertThat(creationStrategy, is(nullValue()));
    }

    @Test
    public void testIsKnown()
    {
        assertTrue(ItemFactory.isKnown("Food"));
        assertTrue(ItemFactory.isKnown("Armor"));
        assertFalse(ItemFactory.isKnown("PHP is an okay language. FALSE!"));
    }

    @Test
    public void testIsNotKnown()
    {
        assertFalse(ItemFactory.isNotKnown("Food"));
        assertFalse(ItemFactory.isNotKnown("Armor"));
        assertTrue(ItemFactory.isNotKnown("PHP is an okay language. FALSE!"));
    }
}

