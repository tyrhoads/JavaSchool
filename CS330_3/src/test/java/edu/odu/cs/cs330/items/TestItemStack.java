package edu.odu.cs.cs330.items;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.StringReader;
import java.util.Arrays;
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
@SuppressWarnings({
    "PMD.AtLeastOneConstructor",
    "PMD.BeanMembersShouldSerialize",
    "PMD.JUnitAssertionsShouldIncludeMessage",
    "PMD.JUnitTestContainsTooManyAsserts",
    "PMD.LocalVariableCouldBeFinal",
    "PMD.MethodArgumentCouldBeFinal",
    "PMD.LawOfDemeter"
})
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestItemStack
{
    private Consumable tomato;
    private Armour shield;

    @BeforeEach
    public void setUp()
    {
        tomato = new Consumable();
        tomato.read(new Scanner("Tomato Hunger-10 2"));

        shield = new Armour();
        shield.read(new Scanner("Shield Gold 20 3 Unbreaking 2 Lightning"));
    }

    /*
    @Test
    public void testDefaultConstructor()
    {
        ItemStack generic = new ItemStack();

        Item theItem = generic.getItem();

        assertThat(theItem, is(nullValue()));
        assertThat(generic.size(), equalTo(0));

        assertThrows(NullPointerException.class,
            () -> {
                generic.permitsStacking();
            }
        );
    }
    */

    @Test
    public void testSecondConstructor()
    {
        ItemStack aStack = new ItemStack(tomato);

        Item theRetrievedItem = aStack.getItem();

        assertThat(theRetrievedItem, equalTo(tomato));

        assertThat(aStack.size(), equalTo(1));
        assertThat(aStack.permitsStacking(), is(true));
    }

    @Test
    public void testClone()
    {
        ItemStack originalStack = new ItemStack(tomato);
        originalStack.addItems(11);

        ItemStack aCopy = (ItemStack) originalStack.clone();

        assertThat(aCopy, is(not(sameInstance(originalStack))));

        assertThat(aCopy.getItem(), equalTo(tomato));
        assertThat(originalStack.getItem(), equalTo(aCopy.getItem()));
        assertThat(originalStack.size(), equalTo(12));
        assertThat(originalStack.permitsStacking(), is(true));
        assertThat(originalStack, equalTo(aCopy));
        assertThat(originalStack.hashCode(), equalTo(aCopy.hashCode()));
    }

    @Test
    public void testAddItemsStackable()
    {
        ItemStack originalStack = new ItemStack(tomato);
        originalStack.addItems(11);

        assertThat(originalStack.getItem(), equalTo(tomato));
        assertThat(originalStack.size(), equalTo(12));
        assertThat(originalStack.permitsStacking(), is(true));

        ItemStack anotherStack = new ItemStack(tomato);
        assertThat(originalStack, is(equalTo(anotherStack)));
        assertThat(originalStack.hashCode(), equalTo(anotherStack.hashCode()));
    }

    @Test
    public void testAddItemsNotStackable()
    {
        ItemStack originalStack = new ItemStack(shield);
        originalStack.addItems(2);

        assertThat(originalStack.getItem(), equalTo(shield));
        assertThat(originalStack.size(), equalTo(1));
        assertThat(originalStack.permitsStacking(), is(false));

        ItemStack anotherStack = new ItemStack(shield);
        assertThat(originalStack, is(equalTo(anotherStack)));
        assertThat(originalStack.hashCode(), equalTo(anotherStack.hashCode()));
    }

    @Test
    public void testEqualsWithDifferentClass()
    {
        ItemStack aStack = new ItemStack(shield);
        String aString = "This is not an ItemStack";

        assertThat(aStack, not(equalTo(aString)));
    }

    @Test
    public void testToString()
    {
        ItemStack aStack = new ItemStack(shield);

        assertThat(aStack.toString(), containsString(shield.toString()));
        assertThat(aStack.toString(), not(containsString("Qty")));

        aStack = new ItemStack(tomato);

        assertThat(aStack.toString(), containsString(tomato.toString()));
        assertThat(aStack.toString(), stringContainsInOrder(Arrays.asList("Qty", "1")));
    }
}
